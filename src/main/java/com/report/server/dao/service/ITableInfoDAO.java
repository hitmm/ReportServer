package com.report.server.dao.service;

import com.report.server.model.common.PageEntity;
import com.report.server.model.dao.entity.TableInfoEntity;

import java.util.List;

/**
 * @Description 表信息
 * @Author huguangyin
 * @Date 2019/6/19-18:12
 *  
 */
public interface ITableInfoDAO {
    /**
     * 插入表信息
     *
     * @param entity
     * @return
     * @throws Exception
     */
    Long insertTableInfo(TableInfoEntity entity) throws Exception;

    /**
     * 更新表信息
     *
     * @param entity
     * @return
     * @throws Exception
     */
    Long updateTableInfo(TableInfoEntity entity) throws Exception;

    /**
     * 更新或者新增表信息
     *
     * @param entity
     * @return
     * @throws Exception
     */
    Long updateOrInsertTableInfo(TableInfoEntity entity) throws Exception;

    /**
     * 计数
     *
     * @return
     * @throws Exception
     */
    Integer countTableInfo() throws Exception;

    /**
     * 查询表信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    TableInfoEntity queryTableInfoById(Long id) throws Exception;

    /**
     * 查询表信息
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<TableInfoEntity> queryTableInfoByUserId(Long userId, PageEntity page) throws Exception;

    /**
     * 删除表信息
     *
     * @param id
     * @return
     */
    boolean deleteTableInfo(Long id);
}
