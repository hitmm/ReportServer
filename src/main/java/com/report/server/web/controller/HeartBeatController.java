package com.report.server.web.controller;

import com.report.server.service.redis.RedisClient;
import com.report.server.service.utils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

/**
 * @Description TODO
 * @Author huguangyin
 * @Date 2019/6/26-19:06
 *  
 */
@RestController
public class HeartBeatController {
    private final static Logger logger = LoggerFactory.getLogger(HeartBeatController.class);
    @Autowired
    private RedisClient redisClient;

    @GetMapping("/heartBeat/{ip}")
    public String beating(@PathVariable(value = "ip")String ip){
        System.out.println(ip);
        Jedis jedisClient = redisClient.getJedisClient();
        String result = jedisClient.getSet(ip,""+ TimeUtil.getCurrentTime());
        logger.info(String.format("beating result : %s.",result));
        return result;
    }
}
