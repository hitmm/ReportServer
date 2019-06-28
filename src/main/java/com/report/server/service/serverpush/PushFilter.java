package com.report.server.service.serverpush;

import com.report.server.model.dao.entity.UserInfoEntity;
import com.report.server.service.service.IUserInfoService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description TODO
 * @Author huguangyin
 * @Date 2019/6/28-17:27
 *  
 */
@Component
@Data
public class PushFilter implements IPushFilter {

    @Autowired
    private IUserInfoService userInfoService;

    @Override
    public boolean filter(String ip) throws Exception {
        List<UserInfoEntity> userInfoEntityList = userInfoService.queryUserInfoCustom("select * from user_info where ip = ?", new Object[]{ip});
        //对用户加规则
        if (userInfoEntityList == null || userInfoEntityList.isEmpty()) {
            return false;
        }
        return userInfoEntityList.size() == 1;
    }
}
