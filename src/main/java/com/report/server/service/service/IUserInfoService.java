package com.report.server.service.service;


import com.report.server.model.dao.entity.UserInfoEntity;

import java.util.List;

/**
 * @Description TODO
 * @Author huguangyin
 * @Date 2019/6/19-20:58
 *  
 */
public interface IUserInfoService {

    /**
     * 查询用户信息
     * @param id 用户id
     * @return
     * @throws Exception
     */
    UserInfoEntity queryUserInfo(Long id)throws Exception;

    /**
     * 查询用户信息
     * @param sql 自定义sql
     * @return
     * @throws Exception
     */
    List<UserInfoEntity> queryUserInfoCustom(String sql, Object[] params)throws Exception;

    /**
     * 查询用户信息
     *
     * @param userName 用户userName
     * @return
     * @throws Exception
     */
    UserInfoEntity queryUserInfo(String userName) throws Exception;

    /**
     * 计数
     * @return
     * @throws Exception
     */
    Integer countUserInfo()throws Exception;

    /**
     * 新增用户
     * @param entity
     * @return
     * @throws Exception
     */
    Long insertUserInfo(UserInfoEntity entity)throws Exception;

    /**
     * 新增或更新用户信息
     *
     * @param entity
     * @return
     * @throws Exception
     */
    Long insertOrUpdateUserInfo(UserInfoEntity entity) throws Exception;

    /**
     * 更新用户信息
     * @param entity
     * @return
     * @throws Exception
     */
    Long updateUserInfo(UserInfoEntity entity)throws Exception;

    /**
     * 删除用户
     * @param id
     * @throws Exception
     */
    void deleteUserInfo(Long id)throws Exception;
}
