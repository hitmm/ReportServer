package com.report.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.report.server.model.common.ErrorCode;
import com.report.server.model.common.PageEntity;
import com.report.server.model.dao.entity.CellInfoEntity;
import com.report.server.model.dao.entity.ReferenceInfoEntity;
import com.report.server.model.dao.entity.TableInfoEntity;
import com.report.server.model.service.dto.TableContentDTO;
import com.report.server.model.service.result.TableContentListResult;
import com.report.server.model.service.result.TableContentResult;
import com.report.server.service.service.*;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Description 表内容服务
 * @Author huguangyin
 * @Date 2019/6/21-17:19
 *  
 */
@Data
@Component
public class TableContentServiceImpl implements ITableContentService {
    private final static Logger logger = LoggerFactory.getLogger(TableInfoServiceImpl.class);

    private final static int ROW_TYPE = 0;
    private final static int COL_TYPE = 1;
    private final static int NONE_TYPE = -1;

    @Autowired
    private ITableInfoService tableInfoService;
    @Autowired
    private ICellInfoService cellInfoService;
    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IReferenceInfoService referenceInfoService;

    @Override
    public TableContentListResult queryTableContentByUserId(long userId, PageEntity page) throws Exception {
        List<TableInfoEntity> tableInfoEntityList = tableInfoService.queryTableInfoByUserId(userId, page);
        if (tableInfoEntityList == null || tableInfoEntityList.isEmpty()) {
            return TableContentListResult.failed(ErrorCode.CODE_NOT_FOUND_TABLE);
        }
        Map<Long, List<ReferenceInfoEntity>> referenceInfoEntityMap = getReferenceInfos(tableInfoEntityList);
        Map<Long, List<CellInfoEntity>> cellInfoEntityMap = getCellInfos(tableInfoEntityList, referenceInfoEntityMap);
        List<TableContentDTO> dtos = mergeTableInfos(tableInfoEntityList, referenceInfoEntityMap, cellInfoEntityMap);

        return TableContentListResult.success(dtos);
    }

    @Override
    public TableContentResult queryTableContentById(long tableId) throws Exception {
        TableInfoEntity tableInfoEntity = tableInfoService.queryTableInfo(tableId);
        if (tableInfoEntity == null) {
            return TableContentResult.failed(ErrorCode.CODE_NOT_FOUND_TABLE, "cannot found table");
        }
        List<TableInfoEntity> tableInfoEntityList = Collections.singletonList(tableInfoEntity);
        Map<Long, List<ReferenceInfoEntity>> referenceInfoEntityMap = getReferenceInfos(tableInfoEntityList);
        if (referenceInfoEntityMap.isEmpty()) {
            return TableContentResult.failed(ErrorCode.CODE_NOT_FOUND_TABLEREFERENCE, "cannot found table reference");
        }
        Map<Long, List<CellInfoEntity>> cellInfoEntityMap = getCellInfos(tableInfoEntityList, referenceInfoEntityMap);
        System.out.println("cellInfoEntityMap : " + JSONObject.toJSONString(cellInfoEntityMap));
        List<TableContentDTO> dtos = mergeTableInfos(tableInfoEntityList, referenceInfoEntityMap, cellInfoEntityMap);
        if (cellInfoEntityMap.isEmpty() || dtos == null
                || dtos.isEmpty()) {
            return TableContentResult.failed(ErrorCode.CODE_NOT_FOUND_TABLECONTENT, "cannot found cell");
        }
        return TableContentResult.success(dtos.get(0));
    }

    @Override
    public TableContentResult insertNewTable(TableContentDTO dto) throws Exception {
        TableInfoEntity tableInfoEntity = dto.getTableInfoEntity();
        Long tableId = tableInfoService.insertTableInfo(tableInfoEntity);
        //获取全部行列信息
        List<ReferenceInfoEntity> referenceInfoEntityList = getAllReferenceInfo(dto);
        System.out.println(String.format("referenceInfoEntityList : %s.", JSONObject.toJSONString(referenceInfoEntityList)));
        //将表ID填入行列信息中
        referenceInfoEntityList = mergeTableId2Reference(referenceInfoEntityList, tableId);
        System.out.println("11111111111111111111111111111111111");
        //将单元格及行列信息入库
        boolean result = saveDetailInfo(dto, referenceInfoEntityList, NONE_TYPE);
        System.out.println("22222222222222222222222222");

        if (result) {
            return TableContentResult.success(tableId);
        }
        /**
         * 添加不成功此处将表级信息删除
         */
        tableInfoService.deleteTableInfo(tableId);
        return TableContentResult.failed(ErrorCode.CODE_UNKNOWN, "cannot insert table");
    }

    @Override
    public TableContentResult deleteTable(long tableId) throws Exception {
        TableContentResult result = null;
        try {
            List<ReferenceInfoEntity> referenceInfoEntityList = referenceInfoService.queryReferenceInfoByTableId(tableId);
            boolean res1 = deleteCells(referenceInfoEntityList);
            boolean res2 = false;
            if (res1) {
                res2 = deleteReferenceInfos(referenceInfoEntityList);
            } else {
                return TableContentResult.failed(ErrorCode.CODE_ERROR_DELETE_CELL, "delete celll error");
            }
            if (res2) {
                tableInfoService.deleteTableInfo(tableId);
                return TableContentResult.success(tableId);
            }
            return TableContentResult.failed(ErrorCode.CODE_ERROR_DELETE_REFERENCE, "delete reference error");
        } catch (Exception e) {
            result = TableContentResult.failed(ErrorCode.CODE_ERROR_DELETE_TABLE, e.getMessage());
        }
        return result;
    }

    @Override
    public TableContentResult insertNewRow(TableContentDTO dto) throws Exception {
        List<ReferenceInfoEntity> cols = null;
        if (dto == null || dto.getTableId() == null) {
            return null;
        }
        Long tableId = dto.getTableId();
        List<ReferenceInfoEntity> rows = dto.getRows();
        rows = mergeTableId2Reference(rows, tableId);
        cols = getAppointReferenceInfo(tableId, COL_TYPE);
        //这里将cols存入rows返回结果中好取存入的行id
        rows.addAll(cols);
        boolean result = saveDetailInfo(dto, rows, COL_TYPE);
        if (result) {
            return TableContentResult.success(rows.get(0).getId());
        }
        return TableContentResult.failed(ErrorCode.CODE_UNKNOWN, "insert row error");
    }

    @Override
    public TableContentResult insertNewCol(TableContentDTO dto) throws Exception {
        List<ReferenceInfoEntity> rows = null;
        if (dto == null || dto.getTableId() == null) {
            return null;
        }
        Long tableId = dto.getTableId();
        List<ReferenceInfoEntity> cols = dto.getCols();
        cols = mergeTableId2Reference(cols, tableId);
        rows = getAppointReferenceInfo(tableId, ROW_TYPE);
        //这里将rows存入cols返回结果中好取存入的列id
        cols.addAll(rows);
        boolean result = saveDetailInfo(dto, cols, ROW_TYPE);
        if (result) {
            return TableContentResult.success(cols.get(0).getId());
        }
        return TableContentResult.failed(ErrorCode.CODE_UNKNOWN, "insert col error");
    }

    @Override
    public TableContentResult updateRows(TableContentDTO dto) throws Exception {
        List<ReferenceInfoEntity> cols = null;
        List<ReferenceInfoEntity> referenceInfoEntityList = new ArrayList<>();
        if (dto == null) {
            return TableContentResult.failed(ErrorCode.CODE_ERROR_NULL_REQUEST, "invalid request-dto");
        }
        List<ReferenceInfoEntity> rows = dto.getRows();
        Long tableId = dto.getTableId();
        if (rows == null || rows.isEmpty()) {
            return TableContentResult.failed(ErrorCode.CODE_ERROR_NULL_REQUEST, "invalid request-rows");
        }
        cols = getAppointReferenceInfo(tableId, COL_TYPE);
        referenceInfoEntityList.addAll(rows);
        referenceInfoEntityList.addAll(cols);
        boolean result = saveDetailInfo(dto, referenceInfoEntityList, COL_TYPE);
        if (result) {
            return TableContentResult.success(tableId);
        }

        return TableContentResult.failed(ErrorCode.CODE_UNKNOWN, "unable to update rows");
    }

    @Override
    public TableContentResult updateCols(TableContentDTO dto) throws Exception {
        List<ReferenceInfoEntity> rows = null;
        List<ReferenceInfoEntity> referenceInfoEntityList = new ArrayList<>();
        if (dto == null) {
            return TableContentResult.failed(ErrorCode.CODE_ERROR_NULL_REQUEST, "invalid request-dto");
        }
        List<ReferenceInfoEntity> cols = dto.getCols();
        Long tableId = dto.getTableId();
        if (cols == null || cols.isEmpty()) {
            return TableContentResult.failed(ErrorCode.CODE_ERROR_NULL_REQUEST, "invalid request-cols");
        }
        rows = getAppointReferenceInfo(tableId, ROW_TYPE);
        referenceInfoEntityList.addAll(cols);
        referenceInfoEntityList.addAll(rows);
        boolean result = saveDetailInfo(dto, referenceInfoEntityList, ROW_TYPE);
        if (result) {
            return TableContentResult.success(tableId);
        }

        return TableContentResult.failed(ErrorCode.CODE_UNKNOWN, "unable to update cols");
    }

    private List<ReferenceInfoEntity> getAppointReferenceInfo(Long tableId, int referenceType) {
        List<ReferenceInfoEntity> referenceInfoEntityList = null;
        try {
            List<ReferenceInfoEntity> tmpRefereceList = referenceInfoService.queryReferenceInfoByTableId(tableId);
            if (tmpRefereceList == null || tmpRefereceList.isEmpty()) {
                return referenceInfoEntityList;
            }
            referenceInfoEntityList = new ArrayList<>();
            for (ReferenceInfoEntity entity : tmpRefereceList) {
                if (entity == null || entity.getReferenceType() != referenceType) {
                    continue;
                }
                referenceInfoEntityList.add(entity);
            }
        } catch (Exception e) {
            logger.warn(String.format("getAppointReferenceInfo error,Exception : %s.", e.getMessage()));
        }
        return referenceInfoEntityList;
    }

    private boolean deleteCells(List<ReferenceInfoEntity> referenceInfoEntityList) {
        try {
            for (ReferenceInfoEntity entity : referenceInfoEntityList) {
                if (entity == null || entity.getReferenceType() == 0) {
                    continue;
                }
                long rowId = entity.getId();
                List<CellInfoEntity> cellInfoEntityList = cellInfoService.queryCellInfoByRowId(rowId);
                for (CellInfoEntity cellInfoEntity : cellInfoEntityList) {
                    if (cellInfoEntity == null) {
                        continue;
                    }
                    cellInfoService.deleteCellInfo(cellInfoEntity.getId());
                }
            }
            return true;
        } catch (Exception e) {
            logger.warn(String.format("Exception : %s.", e.getMessage()));
        }
        return false;
    }

    private boolean deleteReferenceInfos(List<ReferenceInfoEntity> referenceInfoEntityList) {
        try {
            for (ReferenceInfoEntity entity : referenceInfoEntityList) {
                if (entity == null) {
                    continue;
                }
                referenceInfoService.deleteReferenceInfo(entity.getId());
            }
            return true;
        } catch (Exception e) {
            logger.warn(String.format("deleteReferenceInfos Exception : %s.", e.getMessage()));
        }
        return false;
    }

    private boolean saveDetailInfo(TableContentDTO dto, List<ReferenceInfoEntity> referenceInfoEntityList, int notSaveType) {
        try {
            //先存行列信息
            Map<String, ReferenceInfoEntity> referenceInfoEntityMap = new HashMap<>();
            for (ReferenceInfoEntity referenceInfoEntity : referenceInfoEntityList) {
                if (referenceInfoEntity == null) {
                    continue;
                }
                if (notSaveType == referenceInfoEntity.getReferenceType()) {
                    String index = referenceInfoEntity.getReferenceIndex();
                    referenceInfoEntityMap.put(index, referenceInfoEntity);
                    continue;
                }
                Long referenceId = referenceInfoService.insertOrUpdateReferenceInfo(referenceInfoEntity);
                System.out.println("3333333333333333333333333333333333333333333333333333" + JSONObject.toJSONString(referenceInfoEntity));
                referenceInfoEntity.setId(referenceId);
                String index = referenceInfoEntity.getReferenceIndex();
                referenceInfoEntityMap.put(index, referenceInfoEntity);
            }
            System.out.println("4444444444" + JSONObject.toJSONString(referenceInfoEntityList));
            //存cell信息
            //给cell赋上rowId,colId
            Map<Long, CellInfoEntity> cellInfoEntityMap = dto.getCellInfoEntityMap();
            if (cellInfoEntityMap == null) {
                return false;
            }
            Collection<CellInfoEntity> cellInfoEntityList = cellInfoEntityMap.values();
            for (CellInfoEntity entity : cellInfoEntityList) {
                try {
                    if (entity == null) {
                        continue;
                    }
                    String rowIndex = entity.getRowIndex();
                    String colIndex = entity.getColIndex();

                    ReferenceInfoEntity rowEntity = referenceInfoEntityMap.get(rowIndex);
                    ReferenceInfoEntity colEntity = referenceInfoEntityMap.get(colIndex);
                    long rowId = rowEntity.getId();
                    long colId = colEntity.getId();
                    entity.setRowId(rowId);
                    entity.setColId(colId);
                    entity.setTableId(rowEntity.getTableId());
                    Long cellId = cellInfoService.insertOrUpdateCellInfo(entity);
                    entity.setId(cellId);
                } catch (Exception e) {
                    logger.warn(String.format("saveDetailInfo Exception : %s.", e.getMessage()));
                    break;
                }
            }
            return true;
        } catch (Exception e) {
            logger.warn(String.format("saveDetailInfo Exception : %s.", e.getMessage()));
        }
        return false;
    }

    private List<ReferenceInfoEntity> mergeTableId2Reference(List<ReferenceInfoEntity> referenceInfoEntityList, Long tableId) {
        if (referenceInfoEntityList == null || referenceInfoEntityList.isEmpty()) {
            return null;
        }
        for (ReferenceInfoEntity entity : referenceInfoEntityList) {
            if (entity == null) {
                continue;
            }
            entity.setTableId(tableId);
        }
        return referenceInfoEntityList;
    }

    private List<ReferenceInfoEntity> getAllReferenceInfo(TableContentDTO dto) {
        List<ReferenceInfoEntity> cols = dto.getCols();
        List<ReferenceInfoEntity> rows = dto.getRows();
        List<ReferenceInfoEntity> merge = new ArrayList<>(rows);
        merge.addAll(cols);
        return merge;
    }


    private List<TableContentDTO> mergeTableInfos(List<TableInfoEntity> tableInfoEntityList, Map<Long, List<ReferenceInfoEntity>> referenceInfoEntityMap, Map<Long, List<CellInfoEntity>> cellInfoEntityMap) {
        List<TableContentDTO> dtos = new ArrayList<>();
        TableContentDTO dto = null;
        List<ReferenceInfoEntity> rows = null;
        List<ReferenceInfoEntity> cols = null;
        Map<Long, TableContentDTO.RowEntry> rowEntryMap = null;
        List<TableContentDTO.RowEntry> rowEntryList = null;
        TableContentDTO.RowEntry rowEntry = null;
        for (TableInfoEntity tableInfoEntity : tableInfoEntityList) {
            if (tableInfoEntity == null) {
                continue;
            }
            long id = tableInfoEntity.getId();
            dto = new TableContentDTO();
            dto.setTableId(id);
            dto.setTableInfoEntity(tableInfoEntity);

            //set row
            //set col
            List<ReferenceInfoEntity> referenceInfoEntityList = referenceInfoEntityMap.get(id);
            rows = new ArrayList<>();
            cols = new ArrayList<>();
            for (ReferenceInfoEntity referenceInfoEntity : referenceInfoEntityList) {
                if (referenceInfoEntity == null) {
                    continue;
                }
                if (referenceInfoEntity.getReferenceType() == 0) {
                    cols.add(referenceInfoEntity);
                    continue;
                }
                rows.add(referenceInfoEntity);
            }
            dto.setRows(rows);
            dto.setCols(cols);
            //set cell
            List<CellInfoEntity> cellInfoEntityList = cellInfoEntityMap.get(id);
            Map<Long, CellInfoEntity> cellEntityForIdMap = convertListToMap(cellInfoEntityList);
            dto.setCellInfoEntityMap(cellEntityForIdMap);
            //set rowEntry
            rowEntryMap = new HashMap<>();
            for (CellInfoEntity cellInfoEntity : cellInfoEntityList) {
                if (cellInfoEntity == null) {
                    continue;
                }
                long cellId = cellInfoEntity.getId();
                long rowId = cellInfoEntity.getRowId();
                rowEntry = rowEntryMap.get(rowId);
                if (rowEntry == null) {
                    rowEntry = new TableContentDTO.RowEntry();
                    rowEntryMap.put(rowId, rowEntry);
                }
                rowEntry.setRowId(rowId);
                rowEntry.addCellId(cellId);
            }
            dto.setRowEntryMap(rowEntryMap);
            dtos.add(dto);
        }
        return dtos;
    }

    private Map<Long, CellInfoEntity> convertListToMap(List<CellInfoEntity> source) {
        Map<Long, CellInfoEntity> cellInfoEntityMap = new HashMap<>();
        if (source == null || source.isEmpty()) {
            return new HashMap<>();
        }
        for (CellInfoEntity entity : source) {
            if (entity == null) {
                continue;
            }
            long cellId = entity.getId();
            cellInfoEntityMap.put(cellId, entity);
        }
        return cellInfoEntityMap;
    }

    private Map<Long, List<CellInfoEntity>> getCellInfos(List<TableInfoEntity> tableInfoEntityList, Map<Long, List<ReferenceInfoEntity>> referenceInfoEntityMap) throws Exception {
        Map<Long, List<CellInfoEntity>> cellInfoEntityMap = new HashMap<>();
        List<CellInfoEntity> cellInfoEntityList = null;
        for (TableInfoEntity entity : tableInfoEntityList) {
            if (entity == null) {
                continue;
            }
            long tableId = entity.getId();
            cellInfoEntityList = new ArrayList<>();
            List<ReferenceInfoEntity> referenceInfoEntityList = referenceInfoEntityMap.get(tableId);
            //按照行取数据
            for (ReferenceInfoEntity referenceInfoEntity : referenceInfoEntityList) {
                if (referenceInfoEntity == null || referenceInfoEntity.getReferenceType() == COL_TYPE) {
                    continue;
                }
                long referenceId = referenceInfoEntity.getId();
                List<CellInfoEntity> tmpCellInfoList = cellInfoService.queryCellInfoByRowId(referenceId);
                System.out.println("tmpCellInfoList : " + JSONObject.toJSONString(tmpCellInfoList) + " rowId : " + referenceId);
                cellInfoEntityList.addAll(tmpCellInfoList);
            }
            cellInfoEntityMap.put(tableId, cellInfoEntityList);
        }
        return cellInfoEntityMap;
    }

    private Map<Long, List<ReferenceInfoEntity>> getReferenceInfos(List<TableInfoEntity> tableInfoEntityList) throws Exception {
        Map<Long, List<ReferenceInfoEntity>> referenceInfosMap = new HashMap<>();
        for (TableInfoEntity entity : tableInfoEntityList) {
            if (entity == null) {
                continue;
            }
            long tableId = entity.getId();
            List<ReferenceInfoEntity> referenceInfoEntityList = referenceInfoService.queryReferenceInfoByTableId(tableId);
            referenceInfosMap.put(tableId, referenceInfoEntityList);
        }
        return referenceInfosMap;
    }

//    public void setUserInfoService(UserInfoServiceImpl userInfoService) {
//        this.userInfoService = userInfoService;
//    }
//
//    public IUserInfoService getUserInfoService() {
//        return userInfoService;
//    }
//
//    public void setReferenceInfoService(ReferenceInfoServiceImpl referenceInfoService) {
//        this.referenceInfoService = referenceInfoService;
//    }
//
//    public IReferenceInfoService getReferenceInfoService() {
//        return referenceInfoService;
//    }
//
//    public void setCellInfoService(CellInfoServiceImpl cellInfoService) {
//        this.cellInfoService = cellInfoService;
//    }
//
//    public ICellInfoService getCellInfoService() {
//        return cellInfoService;
//    }
//
//    public void setTableInfoService(TableInfoServiceImpl tableInfoService) {
//        this.tableInfoService = tableInfoService;
//    }
//    private ITableInfoService getTableInfoService(){
//        return tableInfoService;
//    }

}
