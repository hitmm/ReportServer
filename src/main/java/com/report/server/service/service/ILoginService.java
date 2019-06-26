package com.report.server.service.service;


import com.report.server.model.common.auth.EncryptionType;
import com.report.server.model.service.result.LoginResult;

/**
 * @Description 登录服务
 * @Author huguangyin
 * @Date 2019/6/25-10:12
 *  
 */
public interface ILoginService {
    /**
     * 明文登录
     *
     * @param ident
     * @param passWord
     * @return
     * @throws Exception
     */
    LoginResult login(String ident, String passWord) throws Exception;

    /**
     * 加密密码登录
     *
     * @param ident
     * @param passWord
     * @return
     * @throws Exception
     */
    LoginResult loginEncryption(String ident, String passWord, EncryptionType type) throws Exception;
}
