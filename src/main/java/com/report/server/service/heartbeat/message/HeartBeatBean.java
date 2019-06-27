package com.report.server.service.heartbeat.message;

import lombok.Data;

@Data
public class HeartBeatBean {
    private String ident;
    private String ip;
    private String imei;
    private String message;
}
