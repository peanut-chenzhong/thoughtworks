package com.thoughtworks.homework.ctm.service;

import com.thoughtworks.homework.ctm.common.CommonConstant;
import com.thoughtworks.homework.ctm.entity.Session;
import com.thoughtworks.homework.ctm.entity.Talk;
import com.thoughtworks.homework.ctm.entity.Track;
import com.thoughtworks.homework.ctm.enums.ExceptionEnum;
import com.thoughtworks.homework.ctm.enums.SessionTypeEnum;
import com.thoughtworks.homework.ctm.exception.CommonException;

import java.util.ArrayList;
import java.util.List;

public class TimeService {
    public static final int INIT_ROUND=3;
    public static final int BASE_MORNING_TALK_CNT=2;
    public static final int MIN_lONG_NUM=10;
    public static List<Track>  groupTalks(List<Talk> talkList,Integer trackCnt) throws CommonException {
        List<Track> result=new ArrayList<Track>();
       int talkSize=talkList.size();
        int minIndex=talkSize-1;
        List<Session> sessionList=new ArrayList<>();
        //先给每个track早上排两个talk，分别取talk时长最大和最小的，如果加在一起超过3小时则取次大的
        for(int i=0;i<trackCnt;i++){
            Track track=new Track();
            track.setTrackNO(i+1);
            for(int j=0;j<minIndex;) {
                if ((talkList.get(minIndex).getCostMin() + talkList.get(j).getCostMin()<=track.getMorSession().getResidualTime())){
                    addTalkToSession(talkList.get(minIndex),track.getMorSession());
                    addTalkToSession(talkList.get(j),track.getMorSession());
                    minIndex--;
                    break;
                }else {
                    j++;
                }
                if(j==minIndex){
                    throw new CommonException(ExceptionEnum.MATCHFAILEDEXCEPTION.getErrorMessage(),ExceptionEnum.MATCHFAILEDEXCEPTION.getErroeCode());
                }
            }
            result.add(track);
            sessionList.add(track.getMorSession());
            sessionList.add(track.getAftSession());
        }
        //talk不够排，抛异常
        if(talkList.size()<trackCnt*INIT_ROUND){
            throw new CommonException(ExceptionEnum.MATCHFAILEDEXCEPTION.getErrorMessage(),ExceptionEnum.MATCHFAILEDEXCEPTION.getErroeCode());
        }
        //至此一共使用了2倍track数的talk
        int usedTalkCnt=trackCnt*BASE_MORNING_TALK_CNT;
        // 如果剩下talk数小于track数，则不满足分配原则，抛异常
        if((talkSize-usedTalkCnt)<trackCnt){
            throw new CommonException(ExceptionEnum.MATCHFAILEDEXCEPTION.getErrorMessage(),ExceptionEnum.MATCHFAILEDEXCEPTION.getErroeCode());
        }
        //从最后一个track开始往前给每个track的下午加一个talk，
        for(int i=0,index=trackCnt*BASE_MORNING_TALK_CNT-1;i<talkSize&&index>=0;i++){
            Talk talk=talkList.get(i);
            if(usedTalkCnt<trackCnt*INIT_ROUND&&!talk.getUsed()){
                if(talk.getCostMin()<=sessionList.get(index).getResidualTime()){
                    addTalkToSession(talk,sessionList.get(index));
                    usedTalkCnt++;
                    index-=BASE_MORNING_TALK_CNT;
                }
            }
        }
        //剩下的talk，从第一个track第一个session开始徘徊分配
        int direction=1;
        boolean flag=false;
        for(int i=0;i<talkSize;i++){
            Talk talk = talkList.get(i);
            if(talk.getUsed()){
               continue;
           }else {
                int testCnt=0;
               for(int index=0;index<trackCnt*BASE_MORNING_TALK_CNT;index+=direction){
                   if(index<0||index>trackCnt*BASE_MORNING_TALK_CNT-1){
                       direction*=-1;
                       index+=direction;
                   }
                   if(talk.getCostMin()<=sessionList.get(index).getResidualTime()){
                       addTalkToSession(talk,sessionList.get(index));
                       usedTalkCnt++;
                       break;
                   }
                   testCnt++;
                   //如果testCnt达到trackCnt*BASE_MORNING_TALK_CNT次，证明已经没有可以放的进去的session了
                   if(testCnt>trackCnt*BASE_MORNING_TALK_CNT){
                       flag=true;
                       break;
                   }
               }
               if(flag){
                   break;
               }
           }
        }
        //还有没放进去的，track数加一重新排
        if(flag){
            trackCnt++;
          return  groupTalks(talkList,trackCnt);
        }else {
            return result;
        }
    }

    private static void addTalkToSession(Talk talk, Session session) {
        talk.setUsed(true);
        session.setResidualTime(session.getResidualTime()-talk.getCostMin());
        session.getTalkList().add(talk);
    }

    public static void manageTime(List<Track> tracks) {
        for(Track track:tracks){
            Session morSession = track.getMorSession();
            Session aftSession = track.getAftSession();
            List<Talk> morTalks = morSession.getTalkList();
            List<Talk> aftTalks = aftSession.getTalkList();
            Integer startMin=CommonConstant.MORSTARTDATE;
            for(Talk talk:morTalks){
                talk.setStartTime(transDate(startMin, SessionTypeEnum.MORNING.getTypeCode()));
                startMin+=talk.getCostMin();
            }
            morTalks.add(CommonConstant.LUNCHTIME);
            startMin=CommonConstant.AFTSTARTDATE;
            for(Talk talk:aftTalks){
                talk.setStartTime(transDate(startMin, SessionTypeEnum.AFTERNOON.getTypeCode()));
                startMin+=talk.getCostMin();
            }
            aftTalks.add(CommonConstant.NETWORKING_EVENT);

        }
    }

    private static String transDate(Integer startMin, String typeCode) {
        StringBuilder result=new StringBuilder();
        int month=startMin/60;
        int day=startMin%60;
        if(month<MIN_lONG_NUM){
            result.append("0").append(month);
        }else {
            result.append(month);
        }
        result.append(":");
        if(day<MIN_lONG_NUM){
            result.append("0").append(day);
        }else {
            result.append(day);
        }
        result.append(typeCode);
        return result.toString();
    }
}
