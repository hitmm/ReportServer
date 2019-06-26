package com.report.server.dao.service;

import com.report.server.model.dao.entity.ReferenceInfoEntity;

import java.util.List;

/**
 * @Description 表的行或列信息
 * @Author huguangyin
 * @Date 2019/6/19-18:12
 *  
 */
public interface IReferenceInfoDAO {
    /**
     * 插入表的行或列信息
     *
     * @param entity
     * @return
     * @throws Exception
     */
    Long insertReferenceInfo(ReferenceInfoEntity entity) throws Exception;

    /**
     * 更新表的行或列信息
     *
     * @param entity
     * @return
     * @throws Exception
     */
    Long updateReferenceInfo(ReferenceInfoEntity entity) throws Exception;

    /**
     * 更新或者新增行列信息
     *
     * @param entity
     * @return
     * @throws Exception
     */
    Long updateOrInsertReferenceInfo(ReferenceInfoEntity entity) throws Exception;

    /**
     * 计数
     *
     * @return
     * @throws Exception
     */
    Integer countReferenceInfo() throws Exception;

    /**
     * 查询表的行或列信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    ReferenceInfoEntity queryReferenceInfoById(Long id) throws Exception;

    /**
     * 根据表的id查询表的行或列信息
     *
     * @param tableId
     * @return
     * @throws Exception
     */
    List<ReferenceInfoEntity> queryReferenceInfoByTableId(Long tableId) throws Exception;

    /**
     * 删除表的行或列信息
     *
     * @param id
     * @return
     */
    boolean deleteReferenceInfo(Long id);
}
