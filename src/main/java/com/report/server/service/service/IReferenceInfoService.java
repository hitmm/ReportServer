package com.report.server.service.service;

import com.report.server.model.dao.entity.ReferenceInfoEntity;

import java.util.List;

/**
 * @Description TODO
 * @Author huguangyin
 * @Date 2019/6/19-20:58
 *  
 */
public interface IReferenceInfoService {

    /**
     * 查询Reference信息
     *
     * @param id Referenceid
     * @return
     * @throws Exception
     */
    ReferenceInfoEntity queryReferenceInfo(Long id) throws Exception;

    /**
     * 根据tableId查询Reference信息
     *
     * @param tableId tableId
     * @return
     * @throws Exception
     */
    List<ReferenceInfoEntity> queryReferenceInfoByTableId(Long tableId) throws Exception;

    /**
     * 计数
     *
     * @return
     * @throws Exception
     */
    Integer countReferenceInfo() throws Exception;

    /**
     * 新增Reference
     *
     * @param entity
     * @return
     * @throws Exception
     */
    Long insertReferenceInfo(ReferenceInfoEntity entity) throws Exception;

    /**
     * 新增或更新行列
     *
     * @param entity
     * @return
     * @throws Exception
     */
    Long insertOrUpdateReferenceInfo(ReferenceInfoEntity entity) throws Exception;

    /**
     * 更新Reference信息
     *
     * @param entity
     * @return
     * @throws Exception
     */
    Long updateReferenceInfo(ReferenceInfoEntity entity) throws Exception;

    /**
     * 删除Reference
     *
     * @param id
     * @throws Exception
     */
    void deleteReferenceInfo(Long id) throws Exception;
}
