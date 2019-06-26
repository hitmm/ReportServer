package com.report.server.model.service.dto;

import com.report.server.model.dao.entity.CellInfoEntity;
import com.report.server.model.dao.entity.ReferenceInfoEntity;
import com.report.server.model.dao.entity.TableInfoEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author huguangyin
 * @Date 2019/6/21-16:42
 *  
 */
@Data
public class TableContentDTO {
    /**
     * 表Id
     */
    private Long tableId;

    /**
     * 表级信息
     */
    private TableInfoEntity tableInfoEntity;

    /**
     * 按行数据集
     */
    private Map<Long, RowEntry> rowEntryMap;

    /**
     * 单元格集合，数据
     */
    private Map<Long, CellInfoEntity> cellInfoEntityMap;

    /**
     * 行集
     */
    private List<ReferenceInfoEntity> rows;

    /**
     * 列集
     */
    private List<ReferenceInfoEntity> cols;

    public TableContentDTO() {
    }

    @Data
    public static class RowEntry {
        private long rowId;
        private List<Long> cellIds;

        public void addCellId(Long cellId) {
            if (cellIds == null) {
                cellIds = new ArrayList<>();
            }
            cellIds.add(cellId);
        }
    }
}
