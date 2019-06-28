package com.report.server.service.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 81046 on 2018-08-30
 */
@Configuration
public class RedisConfig {
    private final static Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Value("${spring.redis.single.nodes}")
    private String singleNodes;

    @Bean
    public Jedis getJedisClient(){
        //分割集群节点
        String[] hostPort = singleNodes.split(":");
        //创建set集合对象
        Set<HostAndPort> nodes =new HashSet<>();
        HostAndPort hostAndPort = new HostAndPort(hostPort[0],Integer.parseInt(hostPort[1]));
        Jedis jedis = new Jedis(hostAndPort);
        return jedis;
    }
}