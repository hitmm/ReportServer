package com.report.server.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.report.server.dao.impl.UserInfoDAO;
import com.report.server.model.dao.entity.UserInfoEntity;
import com.report.server.service.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description TODO
 * @Author huguangyin
 * @Date 2019/6/20-13:47
 *  
 */
@Component
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private UserInfoDAO userInfoDAO;

    public UserInfoServiceImpl(UserInfoDAO userInfoDAO) {
        this.userInfoDAO = userInfoDAO;
    }

    @Override
    public UserInfoEntity queryUserInfo(Long id) throws Exception {
        return userInfoDAO.queryUserInfoById(id);
    }

    @Override
    public List<UserInfoEntity> queryUserInfoCustom(String hql, Object[] params) throws Exception {
        return userInfoDAO.queryUserInfoCustom(hql,params);
    }

    @Override
    public UserInfoEntity queryUserInfo(String userName) throws Exception {
        return userInfoDAO.queryUserInfoByName(userName);
    }

    @Override
    public Integer countUserInfo() throws Exception {
        return userInfoDAO.countUserInfo();
    }

    @Override
    public Long insertUserInfo(UserInfoEntity entity) throws Exception {
        return userInfoDAO.insertUserInfo(entity);
    }

    @Override
    public Long insertOrUpdateUserInfo(UserInfoEntity entity) throws Exception {
        return userInfoDAO.updateOrInsertUserInfo(entity);
    }

    @Override
    public Long updateUserInfo(UserInfoEntity entity) throws Exception {
        return userInfoDAO.updateUserInfo(entity);
    }

    @Override
    public void deleteUserInfo(Long id) throws Exception {
        userInfoDAO.deleteUserInfo(id);
    }

    public static void main(String[] args) throws Exception {
        UserInfoEntity userInfoEntity = new UserInfoServiceImpl(new UserInfoDAO()).queryUserInfo(1212L);
        System.out.println(JSONObject.toJSONString(userInfoEntity));
    }
    public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
        this.userInfoDAO = userInfoDAO;
    }
}
