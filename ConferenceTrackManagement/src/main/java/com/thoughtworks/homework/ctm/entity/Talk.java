package com.thoughtworks.homework.ctm.entity;

import java.io.Serializable;
import java.util.Objects;

public class Talk implements Serializable,Comparable {
    private static final long serialVersionUID = 8152724742838424398L;

    private String name;
    private String startTime;
    private String endTime;
    private Integer costMin;
    private Boolean isUsed=false;
    private String costTime;

    public Talk() {
    }

    public Talk(String name, String startTime, String endTime, Integer costMin, Boolean isUsed, String costTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.costMin = costMin;
        this.isUsed = isUsed;
        this.costTime = costTime;
    }

    @Override
    public String toString() {
        return this.getStartTime()+" "+this.name+" "+this.costTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Talk)) {
            return false;
        }
        Talk talk = (Talk) o;
        return Objects.equals(getName(), talk.getName()) &&
                Objects.equals(getStartTime(), talk.getStartTime()) &&
                Objects.equals(getEndTime(), talk.getEndTime()) &&
                Objects.equals(getCostMin(), talk.getCostMin()) &&
                Objects.equals(isUsed, talk.isUsed) &&
                Objects.equals(getCostTime(), talk.getCostTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getStartTime(), getEndTime(), getCostMin(), isUsed, getCostTime());
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getCostMin() {
        return costMin;
    }

    public void setCostMin(Integer costMin) {
        this.costMin = costMin;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    public String getCostTime() {
        return costTime;
    }

    public void setCostTime(String costTime) {
        this.costTime = costTime;
    }


    @Override
    public int compareTo(Object o) {
        Talk talk= (Talk) o;
        if(this.getCostMin()>talk.getCostMin()){
            return -1;
        }else {
            return 1;
        }
    }
}
