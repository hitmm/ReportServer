package com.report.server.service.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;

/**
 * 序列化与反序列化工具类
 * <p>
 * Created by jinkai on 2017/5/4.
 */
public class SerializeUtil {
    private final static Logger log = LoggerFactory.getLogger(SerializeUtil.class);

    /**
     * 序列化字符串
     *
     * @param bytes
     * @return
     */
    public static String unserializeString(byte[] bytes) {
        return new String(bytes, Charset.forName("utf-8"));
    }

    /**
     * 反序列化字符串
     *
     * @param str
     * @return
     */
    public static byte[] serializeString(String str) {
        return str.getBytes(Charset.forName("utf-8"));
    }

    public static Object unserialize(byte[] bytes) throws Exception {
        if (bytes == null || bytes.length == 0) {
            return null;
        }

        Object obj = null;
        try {
            ObjectInputStream inputStream;
            inputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
            obj = inputStream.readObject();
        } catch (IOException e) {
            log.warn(String.format("Excetpion : ", e.getMessage()), e);
            throw e;
        } catch (ClassNotFoundException e) {
            log.warn(String.format("Excetpion : ", e.getMessage()), e);
            throw e;
        }

        return obj;

    }

    public static byte[] serialize(Object value) throws Exception {
        if (value == null) {
            return null;
        }

        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream(); //构造一个字节输出流
        ObjectOutputStream oos = new ObjectOutputStream(arrayOutputStream); //构造一个类输出流
        oos.writeObject(value); //写这个对象

        oos.flush();
        return arrayOutputStream.toByteArray();
    }


}
