package com.report.server.service.serverpush;

import com.alibaba.fastjson.JSONObject;
import com.report.server.model.service.message.PushMessage;
import com.report.server.service.heartbeat.HeartBeatHolder;
import com.report.server.service.utils.TimeUtil;
import io.netty.channel.Channel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @Description TODO
 * @Author huguangyin
 * @Date 2019/6/28-14:29
 *  
 */
@Component
@Data
public class PushService implements IPushService{
    private final static Logger logger = LoggerFactory.getLogger(PushService.class);

    @Autowired
    private IPushFilter filter;

    @Override
    public void push(PushMessage message) throws Exception {
        long start = TimeUtil.getCurrentTime();
        try {
            logger.info(String.format("push start....PushMessage : %s.", JSONObject.toJSONString(message)));
            List<NioSocketChannel> livingClients = HeartBeatHolder.getLivingClient();
            logger.info(String.format("livingClients : %s.", JSONObject.toJSONString(livingClients)));
            if(livingClients.isEmpty()){
                return;
            }
            for (Channel channel : livingClients){
                InetSocketAddress insocket = (InetSocketAddress)channel.remoteAddress();
                String clientIP = insocket.getAddress().getHostAddress();
                boolean filter = this.filter.filter(clientIP);
                if(filter){
                    channel.writeAndFlush(message);
                }
            }
        } catch (Exception e) {
            logger.warn(String.format("Exception : %s.",e.getMessage()),e);
        }finally {
            logger.info(String.format("push END ,cost : %s.", TimeUtil.getInterval(start)));
        }
    }
}
