package com.report.server.model.service.message;

import lombok.Data;

/**
 * @Description TODO
 * @Author huguangyin
 * @Date 2019/6/28-14:21
 *  
 */
@Data
public class HeartBeatMessage extends Message {

    private String ip;
    private String clientId;
    private String imei;
}
