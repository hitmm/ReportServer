package com.report.server.model.common;

/**
 * @Description TODO
 * @Author huguangyin
 * @Date 2019/6/22-12:15
 *  
 */
public interface ErrorCode {
    /**
     * 成功
     */
    int CODE_SUCCESS = 0;
    /**
     * 不明原因的失败
     */
    int CODE_UNKNOWN = -1;

    /**
     * 主键重复
     */
    int CODE_DUPLICATE_PRIMARYKEY = 1;

    /**
     * 未发现表级信息
     */
    int CODE_NOT_FOUND_TABLE = 2;

    /**
     * 未发现表内容信息
     */
    int CODE_NOT_FOUND_TABLEREFERENCE = 3;

    /**
     * 未发现表内容信息
     */
    int CODE_NOT_FOUND_TABLECONTENT = 4;

    /**
     * 未发现表单元格信息
     */
    int CODE_ERROR_DELETE_CELL = 5;

    /**
     * 未发现表行列信息
     */
    int CODE_ERROR_DELETE_REFERENCE = 6;

    /**
     * 未发现表级信息
     */
    int CODE_ERROR_DELETE_TABLE = 7;

    /**
     * 空请求信息
     */
    int CODE_ERROR_NULL_REQUEST = 8;

    /**
     * 空用户名信息
     */
    int CODE_ERROR_NULL_USER_IDENT = 9;

    /**
     * 空用户名信息
     */
    int CODE_ERROR_NOT_FOUND_USER = 10;
}
