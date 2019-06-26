package com.report.server.web.redis;

import com.report.server.service.utils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;

import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;


public class ClusterRedisClient extends BaseRedisClient {
    private final static Logger log = LoggerFactory.getLogger(ClusterRedisClient.class);

    private JedisCluster jedis = null;

    public ClusterRedisClient(JedisCluster jedis) {
        this.jedis = jedis;
    }


    @Override
    public boolean exists(byte[] keyBytes) {

        return jedis.exists(keyBytes);
    }

    @Override
    public void expire(byte[] key, int seconds) throws Exception {
        jedis.expire(key, seconds);

    }

    @Override
    public void expireAt(byte[] key, long unixTime) throws Exception {
        jedis.expireAt(key, unixTime);

    }

    @Override
    public Long del(byte[] key) throws Exception {
        return jedis.del(key);
    }

    @Override
    public void set(byte[] key, byte[] value) throws Exception {

        jedis.set(key, value);

    }

    @Override
    public boolean setnx(byte[] key, byte[] value) throws Exception {

        return jedis.setnx(key, value) > 0;
    }

    @Override
    public byte[] get(byte[] key) throws Exception {
        return jedis.get(key);
    }

    @Override
    public void hset(byte[] key, byte[] field, byte[] value) throws Exception {

        jedis.hset(key,field,value);
    }

    @Override
    public byte[] hget(byte[] key, byte[] field) throws Exception {
        return jedis.hget(key,field);
    }

    @Override
    public Collection<byte[]> hkeys(byte[] key) throws Exception {
        return jedis.hkeys(key);
    }

    @Override
    public void hmset(byte[] key, Map<byte[], byte[]> hash) throws Exception {
        jedis.hmset(key,hash);

    }

    @Override
    public List<byte[]> hmget(byte[] key, byte[][] hashs) throws Exception {
        return jedis.hmget(key,hashs);
    }

    @Override
    public Map<byte[], byte[]> hgetAll(byte[] key) throws Exception {
        return jedis.hgetAll(key);
    }

    @Override
    public long hincrBy(byte[] key, byte[] field, long increment) throws Exception {
        return jedis.hincrBy(key,field,increment);
    }

    @Override
    public void hdel(byte[] key, byte[] field) throws Exception {
        jedis.hdel(key,field);
    }

    @Override
    public Long hdel(byte[] key, byte[][] fields) throws Exception {
        return jedis.hdel(key,fields);
    }

    @Override
    public void sadd(byte[] key, byte[] member) throws Exception {
        jedis.sadd(key,member);

    }

    @Override
    public boolean sismember(byte[] key, byte[] member) throws Exception {
        return jedis.sismember(key,member);
    }

    @Override
    public void setValue(String key, String value) throws Exception {
        long start = TimeUtil.getCurrentTime();
        try {
            jedis.set(key,value);
        } catch (Exception e) {
            log.warn(String.format("Set Value Failed.(key,value),(%s,%s). Exception : %s",key,value,e.getMessage()),e);
            throw e;
        } finally {
            log.debug("Redis Set Key:[" + key + "], Cost:[" + TimeUtil.getInterval(start) + "]ms");
        }
    }

    @Override
    public String getValue(String key) throws Exception {
        long start = TimeUtil.getCurrentTime();
        String value = null;
        try {
            value = jedis.get(key);
        } catch (Exception e) {
            log.warn(String.format("Get Value Failed.(key),(%s). Exception : %s",key,e.getMessage()),e);
            throw e;
        } finally {
            log.debug("Redis Get Key:[" + key + "], Cost:[" + TimeUtil.getInterval(start) + "]ms");
        }
        return value;
    }

    @Override
    public Long incrby(String key, long incrment) {
        Long result = null;

        long start = TimeUtil.getCurrentTime();

        try {
            result = jedis.incrBy(key,incrment);
        } catch (Exception e) {
            log.warn(String.format("Incrby Failed.(key,increment),(%s,%s). Exception : %s",key,incrment,e.getMessage()),e);
            throw e;
        } finally {
            log.debug("Redis IncrBy Key:[" + key + "],increment:["+incrment+"] Cost:[" + TimeUtil.getInterval(start) + "]ms");
        }


        return result;
    }

    @Override
    public Long zadd(String key, double score, String member) throws Exception {
        Long result = 0L;

        long start = TimeUtil.getCurrentTime();
        try {
            result = jedis.zadd(key,score,member);
        }catch (Exception e) {
            log.warn("Exception : "+e.getMessage());
            throw e;
        } finally {
            log.debug("Key:["+key+"], Cost:["+TimeUtil.getInterval(start)+"]ms");
        }
        return result;
    }

    @Override
    public Long zadd(String key, Map<String, Double> scoreMembers) throws Exception {
        Long result = 0L;

        if(scoreMembers == null || scoreMembers.size() <= 0){
            return result;
        }
        long start = TimeUtil.getCurrentTime();

        try {
            result = jedis.zadd(key,scoreMembers);
        }catch (Exception e) {
            log.warn("Exception : "+e.getMessage());
            throw e;
        } finally {
            log.debug("Key:["+key+"], Cost:["+TimeUtil.getInterval(start)+"]ms");
        }
        return result;
    }

    @Override
    public List<String> zrangeByScore(String key, String min, String max, int offset, int count) throws Exception {
        List<String> result = new ArrayList<String>();
        long start = TimeUtil.getCurrentTime();

        try {
            checkNotNull(key,EXCEPTION_KEY_NULL);

            Set<String> values = jedis.zrangeByScore(key,min,max,offset,count);

            if(values == null || values.size() <= 0){
                return result;
            }

            result.addAll(values);
        } catch (Exception e) {
            log.warn("Exception : "+e.getMessage());
            throw e;
        } finally {
            log.debug("Key:["+key+"], Cost:["+TimeUtil.getInterval(start)+"]ms");
        }

        return result;
    }

    @Override
    public void zrem(String key, String member) throws Exception {

        long start = TimeUtil.getCurrentTime();
        try {
            jedis.zrem(key,member);
        }catch (Exception e) {
            log.warn("Exception : "+e.getMessage());
            throw e;
        } finally {
            log.debug("Key:["+key+"], Cost:["+TimeUtil.getInterval(start)+"]ms");
        }
    }

    @Override
    public Long zcard(String key) throws Exception {

        Long count = 0L;
        long start = TimeUtil.getCurrentTime();
        try {
            count = jedis.zcard(key);
        }catch (Exception e) {
            log.warn("Exception : "+e.getMessage());
            throw e;
        } finally {
            log.debug("Key:["+key+"], Cost:["+TimeUtil.getInterval(start)+"]ms");
        }

        return count;
    }

    @Override
    public List<String> zrangeByScore(String key, String min, String max) throws Exception {
        List<String> list = new ArrayList<String>();

        long start = TimeUtil.getCurrentTime();
        try{

            Set<String> values = jedis.zrangeByScore(key,min,max);
            if(values == null || values.size() <= 0){
                return list;
            }
            list.addAll(values);
        }catch (Exception e){
            log.warn("Exception : "+e.getMessage());
            throw e;
        }finally{
            log.debug("Key:["+key+"],resultSize:["+list.size()+"] Cost:["+TimeUtil.getInterval(start)+"]ms");
        }

        return list;
    }

    @Override
    public List<String> zrange(String key, int start, int end) throws Exception {
        List<String> list = new ArrayList<String>();

        long currentTime = TimeUtil.getCurrentTime();
        try{

            Set<String> values = jedis.zrange(key, start, end);
            if(values == null || values.size() <= 0){
                return list;
            }
            list.addAll(values);
        }catch (Exception e){
            log.warn("Exception : "+e.getMessage());
            throw e;
        }finally{
            log.debug("Key:["+key+"],resultSize:["+list.size()+"] Cost:["+TimeUtil.getInterval(currentTime)+"]ms");
        }

        return list;
    }

    @Override
    public List<String> zrevrange(String key, int start, int end) throws Exception {
        List<String> list = new ArrayList<String>();

        long currentTime = TimeUtil.getCurrentTime();
        try{

            Set<String> values = jedis.zrevrange(key, start, end);
            if(values == null || values.size() <= 0){
                return list;
            }
            list.addAll(values);
        }catch (Exception e){
            log.warn("Exception : "+e.getMessage());
            throw e;
        }finally{
            log.debug("Key:["+key+"],resultSize:["+list.size()+"] Cost:["+TimeUtil.getInterval(currentTime)+"]ms");
        }

        return list;
    }

    @Override
    public Double zscore(String key, String member) throws Exception {
        Double res = 0d;

        long start = TimeUtil.getCurrentTime();
        try{
            res = jedis.zscore(key, member);
        }catch (Exception e){
            log.warn("Exception : "+e.getMessage());
            throw e;
        }finally{
            log.debug("Key:["+key+"], Cost:["+TimeUtil.getInterval(start)+"]ms");
        }

        return res;
    }

    @Override
    public Map<String, Double> zscan(String key) throws Exception {
        Map<String, Double> res = new HashMap<>();

        long start = TimeUtil.getCurrentTime();
        try{

            ScanResult<Tuple> values = jedis.zscan(key, "");
            if(values != null && values.getResult() != null && values.getResult().size() > 0) {
                for(Tuple resTuple:values.getResult()) {
                    res.put(resTuple.getElement(), resTuple.getScore());
                }
            }
        }catch (Exception e){
            log.warn("Exception : "+e.getMessage());
            throw e;
        }finally{
            log.debug("Key:["+key+"],resultSize:["+res.size()+"] Cost:["+TimeUtil.getInterval(start)+"]ms");
        }

        return res;
    }
}
