package com.report.server.dao.service;


import com.report.server.model.dao.entity.UserInfoEntity;

/**
 * @Description 用户信息
 * @Author huguangyin
 * @Date 2019/6/19-18:12
 *  
 */
public interface IUserInfoDAO {
    /**
     * 插入用户信息
     * @param entity
     * @return
     * @throws Exception
     */
    Long insertUserInfo(UserInfoEntity entity)throws Exception;

    /**
     * 更新或者新增表信息
     *
     * @param entity
     * @return
     * @throws Exception
     */
    Long updateOrInsertUserInfo(UserInfoEntity entity) throws Exception;

    /**
     * 更新用户信息
     * @param entity
     * @return
     * @throws Exception
     */
    Long updateUserInfo(UserInfoEntity entity)throws Exception;

    /**
     * 计数
     * @return
     * @throws Exception
     */
    Integer countUserInfo()throws Exception;

    /**
     * 查询用户信息
     * @param id
     * @return
     * @throws Exception
     */
    UserInfoEntity queryUserInfoById(Long id)throws Exception;

    /**
     * 查询用户信息
     *
     * @param userName
     * @return
     * @throws Exception
     */
    UserInfoEntity queryUserInfoByName(String userName) throws Exception;

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    boolean deleteUserInfo(Long id);
}
