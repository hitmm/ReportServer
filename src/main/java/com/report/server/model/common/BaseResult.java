package com.report.server.model.common;

import lombok.Data;

/**
 * @Description TODO
 * @Author huguangyin
 * @Date 2019/6/22-9:40
 *  
 */
@Data
public abstract class BaseResult<T> implements Result<T> {
    private int code;
    private String message;
    private String mark;

    protected BaseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    protected BaseResult() {
    }

    public BaseResult(int code) {
        this.code = code;
    }
}
