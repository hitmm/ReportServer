package com.report.server.service.service;


import com.report.server.model.common.PageEntity;
import com.report.server.model.service.dto.TableContentDTO;
import com.report.server.model.service.result.TableContentListResult;
import com.report.server.model.service.result.TableContentResult;

/**
 * @Description TODO
 * @Author huguangyin
 * @Date 2019/6/21-17:05
 *  
 */
public interface ITableContentService {

    /**
     * 根据userId查询该用户的全部表内容
     *
     * @param userId
     * @return
     */
    TableContentListResult queryTableContentByUserId(long userId, PageEntity page) throws Exception;

    /**
     * 根据tableId查询表内容
     *
     * @param tableId
     * @return
     */
    TableContentResult queryTableContentById(long tableId) throws Exception;

    /**
     * 新增一张表
     *
     * @param dto
     * @return
     * @throws Exception
     */
    TableContentResult insertNewTable(TableContentDTO dto) throws Exception;

    /**
     * 删除一个表
     *
     * @param tableId
     * @return
     * @throws Exception
     */
    TableContentResult deleteTable(long tableId) throws Exception;

    /**
     * 新增一行数据
     *
     * @param dto
     * @return
     * @throws Exception
     */
    TableContentResult insertNewRow(TableContentDTO dto) throws Exception;

    /**
     * 新增一列数据
     *
     * @param dto
     * @return
     * @throws Exception
     */
    TableContentResult insertNewCol(TableContentDTO dto) throws Exception;

    /**
     * 更新一行数据
     *
     * @param dto
     * @return
     * @throws Exception
     */
    TableContentResult updateRows(TableContentDTO dto) throws Exception;

    /**
     * 更新一列数据
     *
     * @param dto
     * @return
     * @throws Exception
     */
    TableContentResult updateCols(TableContentDTO dto) throws Exception;

}
