package com.report.server.model.service.message;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description 服务端推送信息
 * @Author huguangyin
 * @Date 2019/6/28-14:11
 *  
 */
@Data
public class Message implements Serializable {

    private String messageIdent;
    private MessageType type;
    /**
     * 推送时间
     */
    private long timestamp;
}
