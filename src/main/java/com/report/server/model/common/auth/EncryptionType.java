package com.report.server.model.common.auth;

import java.io.Serializable;

/**
 * @Description 加密算法类型
 * @Author huguangyin
 * @Date 2019/6/25-10:19
 *  
 */
public enum EncryptionType implements Serializable {
    /**
     * 算法类型
     */
    MD5, SHA1, RSA, DES, TDES;
}
