package com.report.server.service.serverpush;

/**
 * @Description TODO
 * @Author huguangyin
 * @Date 2019/6/28-17:25
 *  
 */
public interface IPushFilter {

    /**
     * 根据ip过滤
     * @param ip
     * @return
     * @throws Exception
     */
    boolean filter(String ip)throws Exception;
}
