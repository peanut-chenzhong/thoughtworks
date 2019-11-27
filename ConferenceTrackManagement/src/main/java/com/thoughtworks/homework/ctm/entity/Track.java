package com.thoughtworks.homework.ctm.entity;

import com.thoughtworks.homework.ctm.enums.SessionTypeEnum;

import java.io.Serializable;
import java.util.Objects;

public class Track implements Serializable {
    private static final long serialVersionUID = 4128774682349644468L;

    public static final String TRACK_NAME="Track ";
    private Integer trackNO;
    private Session morSession;
    private Session aftSession;


    @Override
    public String toString() {
        return "Track{" +
                "trackNO=" + trackNO +
                ", morSession=" + morSession +
                ", aftSession=" + aftSession +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Track)) {
            return false;
        }
        Track track = (Track) o;
        return Objects.equals(getTrackNO(), track.getTrackNO()) &&
                Objects.equals(getMorSession(), track.getMorSession()) &&
                Objects.equals(getAftSession(), track.getAftSession());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTrackNO(), getMorSession(), getAftSession());
    }

    public Track() {
        this.morSession=new Session(SessionTypeEnum.MORNING.getTypeCode(),SessionTypeEnum.MORNING.getTypeTime());
        this.aftSession=new Session(SessionTypeEnum.AFTERNOON.getTypeCode(),SessionTypeEnum.AFTERNOON.getTypeTime());
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getTrackNO() {
        return trackNO;
    }

    public void setTrackNO(Integer trackNO) {
        this.trackNO = trackNO;
    }

    public Session getMorSession() {
        return morSession;
    }

    public void setMorSession(Session morSession) {
        this.morSession = morSession;
    }

    public Session getAftSession() {
        return aftSession;
    }

    public void setAftSession(Session aftSession) {
        this.aftSession = aftSession;
    }

}
