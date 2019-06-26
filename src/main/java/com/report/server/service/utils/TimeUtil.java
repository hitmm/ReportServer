package com.report.server.service.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * @author jinkai
 */
public class TimeUtil {

    /**
     * 获取当前时间(单位：ms)
     * @return
     */
    public static long getCurrentTime(){
        return System.currentTimeMillis();
    }

    /**
     * 获取Unix时间戳（单位：s）
     * @return
     */
    public static int getCurrentTimeSeconds(){
        return (int) (getCurrentTime()/1000);
    }

    /**
     * 获取时间间隔（ms）
     * @param time
     * @return
     */
    public static long getInterval(Long time){
        return getCurrentTime() - time;
    }


    /**
     * 获取当前时间戳
     * 格式 YYYYMMDDHHMISSMS，时间 采用北京时间，24 小时制，精 确至毫秒
     * @return
     */
    public static String dateFormat(){
        return dateFormat(null,"");
    }

    public static String dateFormat(String format){
        return dateFormat(null,format);
    }

    /**
     * 格式 YYYYMMDDHHMISSMS，时间 采用北京时间，24 小时制，精 确至毫秒
     * @param date   默认取系统时间
     * @return
     */
    public static String dateFromat(Date date){
        return dateFormat(date,"");
    }

    /**
     * 根据传入日期及格式返回字符串
     * @param date   默认取系统时间
     * @param format 默认为yyyyMMddHHmmssms 精确到毫秒
     * @return
     */
    public static String dateFormat(Date date,String format){
        if(date==null){
            date = new Date();
        }
        if(StringUtils.isBlank(format)){
            format ="yyyyMMddHHmmssms";
        }
        SimpleDateFormat sdf =   new SimpleDateFormat( format );
        return sdf.format(date);
    }

}