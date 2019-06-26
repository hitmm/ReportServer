package com.report.server.web.redis;

import com.report.server.service.utils.SerializeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.report.server.service.utils.TimeUtil.getCurrentTime;
import static com.report.server.service.utils.TimeUtil.getInterval;
import static lombok.Lombok.checkNotNull;

public abstract class BaseRedisClient implements IRedisClient, IBinaryRedisClient {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean exists(String key) {
        checkNotNull(key, EXCEPTION_KEY_NULL);

        boolean isExists = false;
        long start = getCurrentTime();
        try {
            byte[] keyBytes = SerializeUtil.serializeString(key);
            isExists = exists(keyBytes);
        } catch (Exception e) {
            log.warn(String.format("Exception : %s", e.getMessage()));
        } finally {
            log.debug("Redis exists Key:[" + key + "], Cost:[" + getInterval(start) + "]ms");
        }

        return isExists;
    }

    @Override
    public void expire(String key, int seconds) throws Exception {
        long start = getCurrentTime();
        try {
            checkNotNull(key, EXCEPTION_KEY_NULL);

            byte[] keyBytes = SerializeUtil.serializeString(key);

            expire(keyBytes, seconds);

        } catch (Exception e) {
            log.warn("Exception : " + e.getMessage());
            throw e;
        } finally {
            log.debug("Key:[" + key + "], Cost:[" + getInterval(start) + "]ms");
        }

    }

    @Override
    public void expireAt(String key, long unixTime) throws Exception {

        long start = getCurrentTime();
        try {
            checkNotNull(key, EXCEPTION_KEY_NULL);

            byte[] keyBytes = SerializeUtil.serializeString(key);
            expireAt(keyBytes, unixTime);

        } catch (Exception e) {
            log.warn("Exception : " + e.getMessage());
            throw e;
        } finally {
            log.debug("Key:[" + key + "], Cost:[" + getInterval(start) + "]ms");
        }

    }

    @Override
    public Long del(String key) throws Exception {
        Long result;
        long start = getCurrentTime();
        try {
            checkNotNull(key, EXCEPTION_KEY_NULL);

            byte[] keyBytes = SerializeUtil.serializeString(key);

            result = del(keyBytes);

        } catch (Exception e) {
            log.warn("Exception : " + e.getMessage());
            throw e;
        } finally {
            log.debug("Key:[" + key + "], Cost:[" + getInterval(start) + "]ms");
        }
        return result;
    }


    @Override
    public void set(String key, Object value) throws Exception {
        long start = getCurrentTime();
        try {
            checkNotNull(key, EXCEPTION_KEY_NULL);

            byte[] keyBytes = SerializeUtil.serializeString(key);
            byte[] valueBytes = SerializeUtil.serialize(value);

            checkNotNull(keyBytes, EXCEPTION_KEYBYTE_NULL);
            checkNotNull(valueBytes, EXCEPTION_VALUEBYTE_NULL);

            set(keyBytes, valueBytes);

        } catch (Exception e) {
            log.warn("Exception : " + e.getMessage());
            throw e;
        } finally {
            log.debug("Key:[" + key + "], Cost:[" + getInterval(start) + "]ms");
        }

    }

    @Override
    public boolean setnx(String key, Object value) throws Exception {
        boolean result;
        long start = getCurrentTime();

        try {
            checkNotNull(key, EXCEPTION_KEY_NULL);

            byte[] keyBytes = SerializeUtil.serializeString(key);

            byte[] valueBytes = SerializeUtil.serialize(value);

            checkNotNull(keyBytes, EXCEPTION_KEYBYTE_NULL);
            checkNotNull(valueBytes, EXCEPTION_VALUEBYTE_NULL);

            result = setnx(keyBytes, valueBytes);

        } catch (Exception e) {
            log.warn("Exception : " + e.getMessage());
            throw e;
        } finally {
            log.debug("Key:[" + key + "], Cost:[" + getInterval(start) + "]ms");
        }

        return result;
    }

    @Override
    public Object get(String key) throws Exception {
        Object result;
        long start = getCurrentTime();
        try {
            checkNotNull(key, EXCEPTION_KEY_NULL);

            byte[] keyBytes = SerializeUtil.serializeString(key);
            byte[] valueBytes = get(keyBytes);

            result = SerializeUtil.unserialize(valueBytes);

        } catch (Exception e) {
            log.warn("Exception : " + e.getMessage());
            throw e;
        } finally {
            log.debug("Key:[" + key + "], Cost:[" + getInterval(start) + "]ms");
        }
        return result;
    }

    @Override
    public void hset(String key, String field, Object value) throws Exception {
        long start = getCurrentTime();
        try {
            checkNotNull(key, EXCEPTION_KEY_NULL);

            byte[] keyBytes = SerializeUtil.serializeString(key);
            byte[] fieldBytes = SerializeUtil.serializeString(field);
            byte[] valueBytes = SerializeUtil.serialize(value);

            hset(keyBytes, fieldBytes, valueBytes);

        } catch (Exception e) {
            log.warn("Exception : " + e.getMessage());
            throw e;
        } finally {
            log.debug("Key:[" + key + "], Cost:[" + getInterval(start) + "]ms");
        }


    }

    @Override
    public Object hget(String key, String field) throws Exception {
        Object result;

        long start = getCurrentTime();
        try {
            checkNotNull(key, EXCEPTION_KEY_NULL);

            byte[] keyBytes = SerializeUtil.serializeString(key);
            byte[] fieldBytes = SerializeUtil.serializeString(field);
            byte[] valueBytes = hget(keyBytes, fieldBytes);

            result = SerializeUtil.unserialize(valueBytes);

        } catch (Exception e) {
            log.warn("Exception : " + e.getMessage());
            throw e;
        } finally {
            log.debug("Key:[" + key + "], Cost:[" + getInterval(start) + "]ms");
        }
        return result;
    }

    @Override
    public Collection<String> hkeys(String key) throws Exception {
        Collection<String> fields = new HashSet<String>();

        long start = getCurrentTime();
        try {
            byte[] keyBytes = SerializeUtil.serializeString(key);

            Collection<byte[]> values = hkeys(keyBytes);

            if (values == null || values.size() <= 0) {
                return fields;
            }

            String field;
            for (byte[] value : values) {
                field = SerializeUtil.unserializeString(value);
                fields.add(field);
            }
        } catch (Exception e) {
            log.warn("Exception : " + e.getMessage());
            throw e;
        } finally {
            log.debug("Key:[" + key + "], Cost:[" + getInterval(start) + "]ms");
        }

        return fields;
    }

    @Override
    public void hmset(String key, Map<String, Object> hash) throws Exception {

        long start = getCurrentTime();
        try {
            checkNotNull(key, EXCEPTION_KEY_NULL);

            byte[] keyBytes = SerializeUtil.serializeString(key);

            Map<byte[], byte[]> hashBytes = serializeMap(hash);

            hmset(keyBytes, hashBytes);

        } catch (Exception e) {
            log.warn("Exception : " + e.getMessage());
            throw e;
        } finally {
            log.debug("Key:[" + key + "], Cost:[" + getInterval(start) + "]ms");
        }

    }

    @Override
    public Map<String, Object> hmget(String key, List<String> fields) throws Exception {

        Map<String, Object> hashs = new HashMap<String, Object>();
        long start = getCurrentTime();
        try {

            if (fields == null || fields.size() <= 0) {
                return hashs;
            }
            byte[] keyBytes = SerializeUtil.serializeString(key);

            List<byte[]> byteFields = serializeStriArray(fields);

            byte[][] fieldBytes = byteFields.toArray(new byte[][]{});

            List<byte[]> values = hmget(keyBytes, fieldBytes);

            String field;
            byte[] valueByte;
            Object value;
            for (int i = 0; i < fields.size(); i++) {
                field = fields.get(i);
                valueByte = values.get(i);
                if (valueByte == null) {
                    continue;
                }
                value = SerializeUtil.unserialize(valueByte);

                if (value == null) {
                    continue;
                }
                hashs.put(field, value);

            }
        } catch (Exception e) {
            log.warn("Exception : " + e.getMessage());
            throw e;
        } finally {
            log.debug("Key:[" + key + "], Cost:[" + getInterval(start) + "]ms");
        }

        return hashs;
    }

    @Override
    public Map<String, Object> hgetAll(String key) throws Exception {
        Map<String, Object> result;

        long start = getCurrentTime();
        try {
            checkNotNull(key, EXCEPTION_KEY_NULL);

            byte[] keyBytes = SerializeUtil.serializeString(key);

            Map<byte[], byte[]> hash = hgetAll(keyBytes);

            result = unserizlizeMap(hash);

        } catch (Exception e) {
            log.warn("Exception : " + e.getMessage());
            throw e;
        } finally {
            log.debug("Key:[" + key + "], Cost:[" + getInterval(start) + "]ms");
        }

        return result;
    }

    @Override
    public long hincrBy(String key, String field, long increment) throws Exception {

        long result = -1;
        long start = getCurrentTime();
        try {
            byte[] keyBytes = SerializeUtil.serializeString(key);
            byte[] fieldByte = SerializeUtil.serializeString(field);

            result = hincrBy(keyBytes, fieldByte, increment);
        } catch (Exception e) {
            log.warn("Exception : " + e.getMessage());
            throw e;
        } finally {
            log.debug("Key:[" + key + "], Cost:[" + getInterval(start) + "]ms");
        }

        return result;

    }

    @Override
    public void hdel(String key, String field) throws Exception {
        long start = getCurrentTime();
        try {
            byte[] keyBytes = SerializeUtil.serializeString(key);
            byte[] fieldsBytes = SerializeUtil.serializeString(field);

            hdel(keyBytes, fieldsBytes);
        } catch (Exception e) {
            log.warn("Exception : " + e.getMessage());
            throw e;
        } finally {
            log.debug("Key:[" + key + "], Cost:[" + getInterval(start) + "]ms");
        }
    }

    @Override
    public Long hdel(String key, List<String> fields) throws Exception {
        Long result = 0L;

        long start = getCurrentTime();
        try {
            byte[] keyBytes = SerializeUtil.serializeString(key);
            List<byte[]> byteFields = serializeStriArray(fields);
            byte[][] fieldBytes = byteFields.toArray(new byte[][]{});

            result = hdel(keyBytes, fieldBytes);
        } catch (Exception e) {
            log.warn("Exception : " + e.getMessage());
            throw e;
        } finally {
            log.debug("Key:[" + key + "], Cost:[" + getInterval(start) + "]ms");
        }

        return result;
    }


    @Override
    public void sadd(String key, Object member) throws Exception {
        long start = getCurrentTime();
        try {
            byte[] keyBytes = SerializeUtil.serializeString(key);
            byte[] memberBytes = SerializeUtil.serialize(member);

            sadd(keyBytes, memberBytes);
        } catch (Exception e) {
            log.warn("Exception : " + e.getMessage());
            throw e;
        } finally {
            log.debug("Key:[" + key + "], Cost:[" + getInterval(start) + "]ms");
        }
    }

    @Override
    public boolean sismember(String key, Object member) throws Exception {
        boolean result = false;
        long start = getCurrentTime();
        try {
            byte[] keyBytes = SerializeUtil.serializeString(key);
            byte[] memberBytes = SerializeUtil.serialize(member);
            result = sismember(keyBytes, memberBytes);
        } catch (Exception e) {
            log.warn("Exception : " + e.getMessage());
            throw e;
        } finally {
            log.debug("Key:[" + key + "], Cost:[" + getInterval(start) + "]ms");
        }

        return result;
    }



    protected static List<byte[]> serializeStriArray(Collection<String> array) {
        List<byte[]> values = new ArrayList<byte[]>();

        if (array == null || array.size() <= 0) {
            return values;
        }

        for (String str : array) {

            values.add(SerializeUtil.serializeString(str));
        }

        return values;
    }

    protected static Map<byte[], byte[]> serializeMap(Map<String, Object> map) throws Exception {
        Map<byte[], byte[]> result = null;
        if (map == null) {
            return result;
        }
        result = new HashMap<byte[], byte[]>();

        String key;
        Object value;
        byte[] keyBytes;
        byte[] valueBytes;
        for (Map.Entry<String, Object> entry : map.entrySet()) {

            key = entry.getKey();
            value = entry.getValue();

            keyBytes = SerializeUtil.serializeString(key);
            valueBytes = SerializeUtil.serialize(value);

            if (valueBytes == null) {
                continue;
            }

            result.put(keyBytes, valueBytes);

        }

        return result;
    }


    protected static Map<String, Object> unserizlizeMap(Map<byte[], byte[]> map) throws Exception {
        Map<String, Object> result = null;

        if (map == null) {
            return result;
        }
        result = new HashMap<String, Object>();

        byte[] keyBytes;
        byte[] valueBytes;

        String key;
        Object value;
        for (Map.Entry<byte[], byte[]> entry : map.entrySet()) {
            if (entry == null) {
                continue;
            }
            keyBytes = entry.getKey();
            valueBytes = entry.getValue();

            if (keyBytes == null || valueBytes == null) {
                continue;
            }

            key = SerializeUtil.unserializeString(keyBytes);
            value = SerializeUtil.unserialize(valueBytes);

            result.put(key, value);
        }


        return result;
    }
}
