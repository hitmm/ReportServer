package com.report.server.web.redis;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IRedisClient {
    String EXCEPTION_KEY_NULL = "key is null";
    String EXCEPTION_KEYBYTE_NULL = "key bytes is null";
    String EXCEPTION_VALUEBYTE_NULL = "value bytes is null";

    boolean exists(String key);

    void expire(String key, int seconds) throws Exception;

    void expireAt(String key, long unixTime) throws Exception;

    Long del(String key) throws Exception;


    void setValue(String key, String value) throws Exception;

    String getValue(String key) throws Exception;

    Long incrby(String key, long incrment);

    void set(String key, Object value) throws Exception;

    boolean setnx(String key, Object value) throws Exception;

    Object get(String key) throws Exception;

    void hset(String key, String field, Object value) throws Exception;

    Object hget(String key, String field) throws Exception;

    Collection<String> hkeys(String key) throws Exception;

    void hmset(String key, Map<String, Object> hash) throws Exception;

    Map<String, Object> hmget(String key, List<String> hashs) throws Exception;

    Map<String, Object> hgetAll(String key) throws Exception;

    long hincrBy(String key, String field, long increment) throws Exception;

    void hdel(String key, String field) throws Exception;

    Long hdel(String key, List<String> fields) throws Exception;

    Long zadd(String key, double score, String member) throws Exception;

    Long zadd(String key, Map<String, Double> scoreMembers) throws Exception;

    List<String> zrangeByScore(String key, String min, String max, int offset, int count) throws Exception;

    void zrem(String key, String member) throws Exception;

    Long zcard(String key) throws Exception;

    void sadd(String key, Object member) throws Exception;

    boolean sismember(String key, Object member) throws Exception;

    List<String> zrangeByScore(String key, String min, String max) throws Exception;

    List<String> zrange(String key, int start, int end) throws Exception;

    List<String> zrevrange(String key, int start, int end) throws Exception;

    Double zscore(String key, String member) throws Exception;

    Map<String, Double> zscan(String key) throws Exception;
}
