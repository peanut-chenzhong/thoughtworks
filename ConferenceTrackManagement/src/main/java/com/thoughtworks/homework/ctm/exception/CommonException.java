package com.thoughtworks.homework.ctm.exception;

public class CommonException extends Exception {
    private static final long serialVersionUID = 1283482481728413693L;

    protected String errorCode;

    protected String errorMessage;

    public CommonException(String errorCode,String errorMessage,Throwable e){
        super(errorMessage,e);
        this.errorCode = errorCode ;
        this.errorMessage = errorMessage ;
    }

    public CommonException(String errorCode,String errorMessage){
        this.errorCode = errorCode ;
        this.errorMessage = errorMessage ;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String errorMessage() {
        return errorMessage;
    }
}
