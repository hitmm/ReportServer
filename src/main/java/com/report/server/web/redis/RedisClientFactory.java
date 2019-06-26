package com.report.server.web.redis;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jinkai
 */
public class RedisClientFactory {
    private final Logger log = LoggerFactory.getLogger(RedisClientFactory.class);

    private RedisClientFactory() {
        initClusterRedis();
    }

    private static class SingletonHolder {
        private static RedisClientFactory singleton = new RedisClientFactory();
    }

    public static RedisClientFactory getInstance() {
        return SingletonHolder.singleton;
    }

    private static IRedisClient clusterRedisClient = null;

    private static Map<String, IRedisClient> clients = new ConcurrentHashMap<String, IRedisClient>();


    private void initClusterRedis(){
        try {
            log.info("Init ClusterRedisClient Start .");

            JedisPoolConfig jedisPoolConfig = ClusterRedisConfig.getJedisPoolConfig();
            Set<HostAndPort> jedisClusterNodes = ClusterRedisConfig.getJedisClusterNodes();

            log.info(String.format("Init ClusterRedis With Nodes : %s", JSONObject.toJSONString(jedisClusterNodes)));
            JedisCluster jedis = new JedisCluster(jedisClusterNodes, ClusterRedisConfig.ESTABLISHING_CONNECTION_TIMEOUT, ClusterRedisConfig.MAXIMUM_REDIRECTION_TIMES, jedisPoolConfig);

            clusterRedisClient  = new ClusterRedisClient(jedis);
            log.info("Init ClusterRedisClient Success.");
        } catch (Exception e) {
            log.warn(String.format("Init Cluster Redis Failed , Exception : %s",e.getMessage()),e);
        }finally{
            log.info("Init ClusterRedisClient End.");
        }
    }
    /**
     * 获取Redis集群版客户端
     * @return
     */
    public IRedisClient getClusterRedisClient(){

        if(clusterRedisClient == null){
            synchronized (this){
                if(clusterRedisClient != null){
                    return clusterRedisClient;
                }
                initClusterRedis();
            }
        }
        return clusterRedisClient;
    }
}
