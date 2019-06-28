package com.report.server.web.init;

import com.report.server.model.service.message.MessageType;
import com.report.server.model.service.message.PushMessage;
import com.report.server.service.heartbeat.IHeartBeatService;
import com.report.server.service.serverpush.IPushService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author huguangyin
 * @Date 2019/6/28-18:03
 *  
 */
@Component
public class ApplicationInit implements ApplicationContextInitializer {
    private final static Logger logger = LoggerFactory.getLogger(ApplicationInit.class);

    @Autowired
    private static IHeartBeatService hearBeatService;

    @Autowired
    private static IPushService pushService;

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        pushService = configurableApplicationContext.getBean("com.report.server.service.serverpush.PushService", IPushService.class);
        hearBeatService = configurableApplicationContext.getBean("com.report.server.service.serverpush.IHeartBeatService", IHeartBeatService.class);
        try {
            logger.info("##################################ReportServerApplication init Start ....");
            try {
                hearBeatService.start();
            } catch (Exception e) {
                logger.warn(String.format("Exception : %s.",e.getMessage()),e);
                hearBeatService.stop();
            }
            logger.info("##################################ReportServerApplication init End ....");
            while (true) {
                Thread.sleep(10 * 1000);
                PushMessage message = new PushMessage();
                message.setContent("hahah");
                message.setMessageIdent("AASDADA");
                message.setType(MessageType.MSG_PUSH);
                pushService.push(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
