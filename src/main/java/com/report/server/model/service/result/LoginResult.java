package com.report.server.model.service.result;

import com.report.server.model.common.BaseResult;
import com.report.server.model.common.ErrorCode;
import com.report.server.model.dao.entity.UserInfoEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description TODO
 * @Author huguangyin
 * @Date 2019/6/25-10:13
 *  
 */
@Data
public class LoginResult extends BaseResult<UserInfoEntity> implements Serializable {
    private UserInfoEntity entity = null;

    public LoginResult(int code, String message) {
        super(code, message);
    }

    public LoginResult(int code) {
        super(code);
    }

    public static LoginResult failed(int code, String message) {
        return new LoginResult(code, message);
    }

    public static LoginResult success(UserInfoEntity data) {
        LoginResult loginResult = new LoginResult(ErrorCode.CODE_SUCCESS);
        loginResult.setEntity(data);
        return loginResult;
    }

    @Override
    public Long getId() {
        if (entity == null) {
            return null;
        }
        return entity.getId();
    }

    @Override
    public UserInfoEntity getData() {
        return null;
    }
}
