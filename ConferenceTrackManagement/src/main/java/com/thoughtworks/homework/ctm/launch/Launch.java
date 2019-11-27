package com.thoughtworks.homework.ctm.launch;

import com.thoughtworks.homework.ctm.common.CommonConstant;
import com.thoughtworks.homework.ctm.entity.Talk;
import com.thoughtworks.homework.ctm.entity.Track;
import com.thoughtworks.homework.ctm.exception.CommonException;
import com.thoughtworks.homework.ctm.service.FileService;
import com.thoughtworks.homework.ctm.service.TimeService;

import java.io.File;
import java.util.List;

/**
 * @author Adam
 */
public class Launch {

    public static void main(String[] args) throws CommonException {
        //load file create TalkList
        File inputFile=new File("input.txt");
        List<Talk> talks = FileService.analyzeFile(inputFile);
        //sort of the talkList
        talks.sort(Talk::compareTo);
        //counting times
        Integer sumMin=talks.stream().map(Talk::getCostMin).reduce(Integer::sum).get();
        int trackCnt=sumMin%CommonConstant.TOTALMINONEDAY==0? sumMin/CommonConstant.TOTALMINONEDAY:sumMin/CommonConstant.TOTALMINONEDAY+1;
        //arrange the talks
        List<Track> tracks= TimeService.groupTalks(talks,trackCnt);
        //deail the time detail
        TimeService.manageTime(tracks);

        //output to the file
        File outPutFile=new File("output.txt");
        FileService.gainFile(tracks,outPutFile);
    }
}
