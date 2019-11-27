package com.thoughtworks.homework.ctm.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Session implements Serializable {

    private static final long serialVersionUID = -1640144029361745243L;

    private String sessionType;
    private Integer totalTime;
    private Integer residualTime;
    private List<Talk> talkList=new ArrayList<>();

    public Session() {
    }

    public Session(String sessionType, Integer totalTime) {
        this.sessionType = sessionType;
        this.totalTime = totalTime;
        this.residualTime=totalTime;
    }

    @Override
    public String toString() {
        return "Session{" +
                "SessionType='" + sessionType + '\'' +
                ", totalTime=" + totalTime +
                ", residualTime=" + residualTime +
                ", talkList=" + talkList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Session)) {
            return false;
        }
        Session session = (Session) o;
        return Objects.equals(getSessionType(), session.getSessionType()) &&
                Objects.equals(getTotalTime(), session.getTotalTime()) &&
                Objects.equals(getResidualTime(), session.getResidualTime()) &&
                Objects.equals(getTalkList(), session.getTalkList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSessionType(), getTotalTime(), getResidualTime(), getTalkList());
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSessionType() {
        return sessionType;
    }

    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getResidualTime() {
        return residualTime;
    }

    public void setResidualTime(Integer residualTime) {
        this.residualTime = residualTime;
    }

    public List<Talk> getTalkList() {
        return talkList;
    }

    public void setTalkList(List<Talk> talkList) {
        this.talkList = talkList;
    }
}
