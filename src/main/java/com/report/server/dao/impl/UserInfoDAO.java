package com.report.server.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.report.server.dao.service.IUserInfoDAO;
import com.report.server.model.dao.entity.UserInfoEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description TODO
 * @Author huguangyin
 * @Date 2019/6/19-18:15
 *  
 */
@Component
public class UserInfoDAO extends BaseDAO implements IUserInfoDAO {

    private final static String DELETE_ID_HQL = "delete from UserInfoEntity where id = ?0";
    private final static String QUERY_NAME_HQL = "from UserInfoEntity where userName = ?0";
    private final static String QUERY_ID_HQL = "from UserInfoEntity where id = ?0";
    private final static String COUNT_USER_HQL = "select count(1) from UserInfoEntity;";

    public static void main(String[] args) throws Exception {
        UserInfoEntity userInfoEntity = new UserInfoDAO().queryUserInfoById(1212L);
        System.out.println(JSONObject.toJSONString(userInfoEntity));
    }

    @Override
    public Long insertUserInfo(UserInfoEntity entity) throws Exception {
        return save(entity);
    }

    @Override
    public Long updateOrInsertUserInfo(UserInfoEntity entity) throws Exception {
        return saveOrUpdate(entity);
    }

    @Override
    public Long updateUserInfo(UserInfoEntity entity) throws Exception {
        return updateUserInfo(entity);
    }

    @Override
    public Integer countUserInfo() throws Exception {
        return count(COUNT_USER_HQL, null);
    }

    @Override
    public UserInfoEntity queryUserInfoById(Long id) throws Exception {
        return query(id,UserInfoEntity.class);
    }

    @Override
    public UserInfoEntity queryUserInfoByName(String userName) throws Exception {
        List<UserInfoEntity> resultTmp = query(QUERY_NAME_HQL, new Object[]{userName});
        return resultTmp.get(0);
    }

    @Override
    public boolean deleteUserInfo(Long id) {
        delete(DELETE_ID_HQL, new Object[]{id});
        return true;
    }

}
