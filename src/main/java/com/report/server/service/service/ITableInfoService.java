package com.report.server.service.service;


import com.report.server.model.common.PageEntity;
import com.report.server.model.dao.entity.TableInfoEntity;

import java.util.List;

/**
 * @Description TODO
 * @Author huguangyin
 * @Date 2019/6/19-20:58
 *  
 */
public interface ITableInfoService {

    /**
     * 查询表的信息
     *
     * @param id 表的id
     * @return
     * @throws Exception
     */
    TableInfoEntity queryTableInfo(Long id) throws Exception;

    /**
     * 查询表的信息
     *
     * @param userId 表的ownerId
     * @return
     * @throws Exception
     */
    List<TableInfoEntity> queryTableInfoByUserId(Long userId, PageEntity page) throws Exception;

    /**
     * 计数
     *
     * @return
     * @throws Exception
     */
    Integer countTableInfo() throws Exception;

    /**
     * 新增表的
     *
     * @param entity
     * @return
     * @throws Exception
     */
    Long insertTableInfo(TableInfoEntity entity) throws Exception;

    /**
     * 新增或更新表级信息
     *
     * @param entity
     * @return
     * @throws Exception
     */
    Long insertOrUpdateTableInfo(TableInfoEntity entity) throws Exception;

    /**
     * 更新表的信息
     *
     * @param entity
     * @return
     * @throws Exception
     */
    Long updateTableInfo(TableInfoEntity entity) throws Exception;

    /**
     * 删除表的
     *
     * @param id
     * @throws Exception
     */
    void deleteTableInfo(Long id) throws Exception;
}
