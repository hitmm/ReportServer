package com.report.server.web;

import com.report.server.model.service.message.MessageType;
import com.report.server.model.service.message.PushMessage;
import com.report.server.service.heartbeat.HearBeatService;
import com.report.server.service.heartbeat.IHeartBeatService;
import com.report.server.service.serverpush.IPushService;
import com.report.server.service.serverpush.PushService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan(value = "com.report.server")
@RestController
public class ReportServerApplication {
    private final static Logger logger = LoggerFactory.getLogger(ReportServerApplication.class);

    private static IHeartBeatService hearBeatService = new HearBeatService();

    private static IPushService pushService = new PushService();

    public static void main(String[] args) throws Exception {
        logger.info("##################################ReportServerApplication init Start ....");
        SpringApplication.run(ReportServerApplication.class, args);
        try {
            hearBeatService.start();
        } catch (Exception e) {
            logger.warn(String.format("Exception : %s.",e.getMessage()),e);
            hearBeatService.stop();
        }
        logger.info("##################################ReportServerApplication init End ....");
       while (true){
           Thread.sleep(10*1000);
           PushMessage message = new PushMessage();
           message.setContent("hahah");
           message.setMessageIdent("AASDADA");
           message.setType(MessageType.MSG_PUSH);
           pushService.push(message);
       }
    }

}
