package com.report.server.web.redis;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IBinaryRedisClient {
    boolean exists(byte[] keyBytes);

    void expire(byte[] key, int seconds) throws Exception;

    void expireAt(byte[] key, long unixTime) throws Exception;

    Long del(byte[] key) throws Exception;

    void set(byte[] key, byte[] value) throws Exception;

    boolean setnx(byte[] key, byte[] value) throws Exception;

    byte[] get(byte[] key) throws Exception;

    void hset(byte[] key, byte[] field, byte[] value) throws Exception;

    byte[] hget(byte[] key, byte[] field) throws Exception;

    Collection<byte[]> hkeys(byte[] key) throws Exception;

    void hmset(byte[] key, Map<byte[], byte[]> hash) throws Exception;

    List<byte[]> hmget(byte[] key, byte[][] hashs) throws Exception;

    Map<byte[], byte[]> hgetAll(byte[] key) throws Exception;

    long hincrBy(byte[] key, byte[] field, long increment) throws Exception;

    void hdel(byte[] key, byte[] field) throws Exception;

    Long hdel(byte[] key, byte[][] fields) throws Exception;

    void sadd(byte[] key, byte[] member) throws Exception;

    boolean sismember(byte[] key, byte[] member) throws Exception;

}
