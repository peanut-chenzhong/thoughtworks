package com.thoughtworks.homework.ctm.service;

import com.thoughtworks.homework.ctm.common.CommonConstant;
import com.thoughtworks.homework.ctm.entity.Session;
import com.thoughtworks.homework.ctm.entity.Talk;
import com.thoughtworks.homework.ctm.entity.Track;
import com.thoughtworks.homework.ctm.enums.ExceptionEnum;
import com.thoughtworks.homework.ctm.exception.CommonException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    private static int STARTINDEX=0;
    private static int UNITSIZE=3;

    public static List<Talk> analyzeFile(File file) throws CommonException {
        List<Talk> result=new ArrayList<Talk>();
        BufferedReader br=null;
        try {
            br=new BufferedReader(new FileReader(file));
            String str=null;
            int len=0;
            while ((str=br.readLine())!=null){
                str=str.trim();
                len=str.lastIndexOf(CommonConstant.BLANKSIGN);
                Talk talk=new Talk();
                talk.setName(str.substring(STARTINDEX,len));
                String costTime=str.substring(len,str.length()).trim();
                talk.setCostTime(costTime);
                if(CommonConstant.LIGHTNING.equals(costTime)){
                    talk.setCostMin(CommonConstant.LIGHTNINGMIN);
                }else {
                    talk.setCostMin(Integer.valueOf(str.substring(len, str.length() - UNITSIZE).trim()));
                }
                result.add(talk);
            }
        } catch (Exception e) {
            throw new CommonException(ExceptionEnum.ANALYFILEEXCEPTION.getErrorMessage()
                    ,ExceptionEnum.ANALYFILEEXCEPTION.getErroeCode()
                    ,e);
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new CommonException(ExceptionEnum.ANALYFILEEXCEPTION.getErrorMessage()
                        ,ExceptionEnum.ANALYFILEEXCEPTION.getErroeCode()
                        ,e);
            }
        }
        return result;
    }

    public static void gainFile(List<Track> trackList,File file) throws CommonException{
        BufferedWriter bw = null;
        try {
//            bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            bw=new BufferedWriter(new FileWriter(file));
            for(Track track:trackList) {
                bw.write(Track.TRACK_NAME+track.getTrackNO());
                bw.newLine();
                bw.flush();
                Session morSession=track.getMorSession();
                Session aftSession = track.getAftSession();
                List<Talk> talkList=new ArrayList<Talk>();
                talkList.addAll(morSession.getTalkList());
                talkList.addAll(aftSession.getTalkList());
                for(Talk talk:talkList){
                    bw.write(talk.toString());
                    bw.newLine();
                    bw.flush();
                }
            }
        } catch (Exception e) {
            throw new CommonException(ExceptionEnum.CREATFILEEXCEPTION.getErrorMessage()
                    ,ExceptionEnum.CREATFILEEXCEPTION.getErroeCode()
                    ,e);
        }finally {
            try {
                bw.close();
            } catch (IOException e) {
                throw new CommonException(ExceptionEnum.CREATFILEEXCEPTION.getErrorMessage()
                        ,ExceptionEnum.CREATFILEEXCEPTION.getErroeCode()
                        ,e);
            }
        }
    }

}
