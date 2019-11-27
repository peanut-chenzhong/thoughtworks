package com.thoughtworks.homework.ctm.service;

import com.thoughtworks.homework.ctm.entity.Session;
import com.thoughtworks.homework.ctm.entity.Talk;
import com.thoughtworks.homework.ctm.entity.Track;
import com.thoughtworks.homework.ctm.exception.CommonException;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FileUtilTest {

    @Test
    public void analyzeFile() throws CommonException {
        File file=new File("input.txt");
        List<Talk> talks = FileService.analyzeFile(file);
        assertTrue(talks.size()>0);
    }

    @Test
    public void gainFile() throws CommonException {
        Track track1=new Track();
        track1.setTrackNO(1);
        Session morSession=track1.getMorSession();
        Session aftSession = track1.getAftSession();
        Talk talk1=new Talk("aaa","09:00AM","12:00PM",180,true,"180min");
        List<Talk> list1=new ArrayList<Talk>();
        list1.add(talk1);
        Talk talk2=new Talk("bbb","09:00AM","12:00PM",180,true,"180min");
        List<Talk> list2=new ArrayList<Talk>();
        list2.add(talk2);

        morSession.setTalkList(list1);
        aftSession.setTalkList(list2);
        Track track2=new Track();
        track2.setTrackNO(2);
        Session morSession1=track2.getMorSession();
        Session aftSession1 = track2.getAftSession();
        Talk talk3=new Talk("ccc","09:00AM","12:00PM",180,true,"180min");
        List<Talk> list3=new ArrayList<Talk>();
        list3.add(talk3);
        Talk talk4=new Talk("ddd","09:00AM","12:00PM",180,true,"180min");
        List<Talk> list4=new ArrayList<Talk>();
        list4.add(talk4);
        morSession1.setTalkList(list3);
        aftSession1.setTalkList(list4);

        List<Track> tracks=new ArrayList<Track>();
        tracks.add(track1);
        tracks.add(track2);
        File file = new File("output.txt");
        FileService.gainFile(tracks,file);

        assertTrue(file.exists());
    }
}