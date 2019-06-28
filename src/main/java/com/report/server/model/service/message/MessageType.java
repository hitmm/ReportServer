package com.report.server.model.service.message;

/**
 * @Description TODO
 * @Author huguangyin
 * @Date 2019/6/28-14:12
 *  
 */
public enum MessageType {

    /**
     *
     */
    CONNECT_REQ((short)1,"CONNECT_REQ"), CONNECT_SUCCESS((short)2,"CONNECT_SUCCESS"), CONNECT_FAIL((short)3,"CONNECT_FAIL"),
    HEARTBEAT_REQ((short)4,"HEARTBEAT_REQ"), HEARTBEAT_RESP((short)5,"HEARTBEAT_RESP"), MSG_PUSH((short)6,"MSG_PUSH");

    private short code;
    private String message;

    private MessageType(short code,String message){
        this.code = code;
        this.message = message;
    }

    public short getCode() {
        return code;
    }

    public void setCode(short code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}

