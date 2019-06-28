package com.report.server.service.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * @Description TODO
 * @Author huguangyin
 * @Date 2019/6/26-20:18
 *  
 */
@Service
public class RedisClient {
    private static final Logger log= LoggerFactory.getLogger(RedisClient.class);

    @Autowired
    private RedisConfig jedisClusterConfig;

    public Jedis getJedisClient(){
        return jedisClusterConfig.getJedisClient();
    }

    public boolean setToRedis(String key,Object value){
        try {
            String str= getJedisClient().set(key, String.valueOf(value));
            if("OK".equals(str)) {
                return true;
            }
        }catch (Exception ex){
            log.error("setToRedis:{Key:"+key+",value"+value+"}",ex);
        }
        return false;
    }

    public Object getRedis(String key){
        String str=null;
        try {
            str= getJedisClient().get(key);
        }catch (Exception ex){
            log.error("getRedis:{Key:"+key+"}",ex);
        }
        return str;
    }

}
