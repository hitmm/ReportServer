package com.report.server.web.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

public class ClusterRedisConfig {

    /**
     * 最大连接数
     */
    final static int MAXIMUM_CONNECTION = 100;
    /**
     * 最大空闲连接数
     */
    final static int MAXIMUM_IDLE_CONNECTION = 100;
    /**
     * 最小空闲连接数
     */
    final static int MINIMUM_IDLE_CONNECTION = 100;
    /**
     * 最大等待时间
     */
    final static int MAXIMUM_WAITING_TIME = 6 * 1000;
    /**
     * 连接建立超时时间
     */
    final static int ESTABLISHING_CONNECTION_TIMEOUT = 2000;
    /**
     * 最大重定向次数
     */
    final static int MAXIMUM_REDIRECTION_TIMES = 5;

    private final static String REDIS_CONFIG_KEY = "rediscluster";
    private final static String HOST = "host";
    private final static String PORT = "port";


    public static JedisPoolConfig getJedisPoolConfig(){
        // 数据库连接池配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大连接数, 应用自己评估，不要超过Redis每个实例最大的连接数
        jedisPoolConfig.setMaxTotal(ClusterRedisConfig.MAXIMUM_CONNECTION);
        //最大空闲连接数, 应用自己评估，不要超过Redis每个实例最大的连接数
        jedisPoolConfig.setMaxIdle(ClusterRedisConfig.MAXIMUM_IDLE_CONNECTION);
        jedisPoolConfig.setMinIdle(ClusterRedisConfig.MINIMUM_IDLE_CONNECTION);
        jedisPoolConfig.setMaxWaitMillis(ClusterRedisConfig.MAXIMUM_WAITING_TIME);
        jedisPoolConfig.setTestOnBorrow(false);
        jedisPoolConfig.setTestOnReturn(false);
        return jedisPoolConfig;
    }


    public static Set<HostAndPort> getJedisClusterNodes() throws Exception{

//        HashSet<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
//        File redisFile = new File(IConstants.REDIS_HOST_PORT_FILEPATH);
//
//        if(!redisFile.exists()){
//            throw new NoSuchFileException(String.format("NO Redis Cluster Config:%s",IConstants.REDIS_HOST_PORT_FILEPATH));
//        }
//
//        Config config = ConfigFactory.parseFile(redisFile);
//
//        List<Config> configList = (List<Config>) config.getConfigList(REDIS_CONFIG_KEY);

//        for (Config nodeConfig : configList) {
//            String host = nodeConfig.getString(HOST);
//            Integer port = nodeConfig.getInt(PORT);
//            HostAndPort node = new HostAndPort(host, port);
//            jedisClusterNodes.add(node);
//        }

//        return jedisClusterNodes;
        return null;
    }

}
