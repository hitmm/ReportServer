package com.report.server.model.common;

/**
 * @Description 本系统结果接口
 * @Author huguangyin
 * @Date 2019/6/22-9:35
 *  
 */
public interface Result<T> {
    Long getId();

    int getCode();

    String getMessage();

    T getData();
}
