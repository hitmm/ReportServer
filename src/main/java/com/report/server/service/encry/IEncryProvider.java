package com.report.server.service.encry;

/**
 * @Description TODO
 * @Author huguangyin
 * @Date 2019/6/25-11:30
 *  
 */
public interface IEncryProvider {

    String encrypt(String str, String publicKey) throws Exception;

    String decrypt(String str, String privateKey) throws Exception;
}
