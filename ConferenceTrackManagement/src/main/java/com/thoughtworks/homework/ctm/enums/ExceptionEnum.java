package com.thoughtworks.homework.ctm.enums;

public enum ExceptionEnum {
    ANALYFILEEXCEPTION("analy file error", "10001"), //read data from file error
    CREATFILEEXCEPTION("creat file error", "10002"), //write date to file error
    MATCHFAILEDEXCEPTION("match failed, do not have enough talks to arrange", "10003"); //match failed, do not have enough talks to arrange
    private String errorMessage;
    private String erroeCode;

    ExceptionEnum(String errorMessage, String erroeCode) {
        this.errorMessage = errorMessage;
        this.erroeCode = erroeCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErroeCode() {
        return erroeCode;
    }
}
