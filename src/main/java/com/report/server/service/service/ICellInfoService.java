package com.report.server.service.service;

import com.report.server.model.dao.entity.CellInfoEntity;

import java.util.List;

/**
 * @Description TODO
 * @Author huguangyin
 * @Date 2019/6/19-20:58
 *  
 */
public interface ICellInfoService {

    /**
     * 查询单元格信息
     *
     * @param id 单元格id
     * @return
     * @throws Exception
     */
    CellInfoEntity queryCellInfo(Long id) throws Exception;

    /**
     * 查询单元格信息
     *
     * @param rowId
     * @param colId
     * @return
     * @throws Exception
     */
    CellInfoEntity queryCellInfoByRowColId(Long rowId, Long colId) throws Exception;

    /**
     * 查询单元格信息
     *
     * @param rowId
     * @return
     * @throws Exception
     */
    List<CellInfoEntity> queryCellInfoByRowId(Long rowId) throws Exception;

    /**
     * 查询单元格信息
     *
     * @param colId
     * @return
     * @throws Exception
     */
    List<CellInfoEntity> queryCellInfoByColId(Long colId) throws Exception;

    /**
     * 计数
     *
     * @return
     * @throws Exception
     */
    Integer countCellInfo() throws Exception;

    /**
     * 新增单元格
     *
     * @param entity
     * @return
     * @throws Exception
     */
    Long insertCellInfo(CellInfoEntity entity) throws Exception;

    /**
     * 新增或更新单元格
     *
     * @param entity
     * @return
     * @throws Exception
     */
    Long insertOrUpdateCellInfo(CellInfoEntity entity) throws Exception;

    /**
     * 更新单元格信息
     *
     * @param entity
     * @return
     * @throws Exception
     */
    Long updateCellInfo(CellInfoEntity entity) throws Exception;

    /**
     * 删除单元格
     *
     * @param id
     * @throws Exception
     */
    void deleteCellInfo(Long id) throws Exception;
}
