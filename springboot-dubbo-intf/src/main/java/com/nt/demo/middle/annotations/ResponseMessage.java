package com.nt.demo.middle.annotations;



import java.io.Serializable;

/**
 * Create by TaoTaoNing
 * 2019-11-19
 **/

public class ResponseMessage implements Serializable {

    private static final long serialVersionUID = -8798847476019648623L;

    private String resultCode;

    private String resultMsg;

    public ResponseMessage() {
    }

    public ResponseMessage(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
