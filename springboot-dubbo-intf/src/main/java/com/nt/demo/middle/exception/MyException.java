package com.nt.demo.middle.exception;

/**
 * Create by TaoTaoNing
 * 2019-11-19
 **/

public class MyException extends RuntimeException {

    private String errorCode;

    public MyException(String errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
