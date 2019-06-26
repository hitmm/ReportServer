package com.report.server.model.dao.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Description TODO
 * @Author huguangyin
 * @Date 2019/6/26-15:45
 *  
 */
@Entity
@Table(name = "cell_info", schema = "report")
public class CellInfoEntity implements IEntity{
    private long id;
    private long rowId;
    private long colId;
    private int cellType;
    private String cellValue;
    private long tableId;
    private String rowIndex;
    private String colIndex;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "row_id")
    public long getRowId() {
        return rowId;
    }

    public void setRowId(long rowId) {
        this.rowId = rowId;
    }

    @Basic
    @Column(name = "col_id")
    public long getColId() {
        return colId;
    }

    public void setColId(long colId) {
        this.colId = colId;
    }

    @Basic
    @Column(name = "cell_type")
    public int getCellType() {
        return cellType;
    }

    public void setCellType(int cellType) {
        this.cellType = cellType;
    }

    @Basic
    @Column(name = "cell_value")
    public String getCellValue() {
        return cellValue;
    }

    public void setCellValue(String cellValue) {
        this.cellValue = cellValue;
    }

    @Basic
    @Column(name = "table_id")
    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

    @Basic
    @Column(name = "row_index")
    public String getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(String rowIndex) {
        this.rowIndex = rowIndex;
    }

    @Basic
    @Column(name = "col_index")
    public String getColIndex() {
        return colIndex;
    }

    public void setColIndex(String colIndex) {
        this.colIndex = colIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CellInfoEntity that = (CellInfoEntity) o;
        return id == that.id &&
                rowId == that.rowId &&
                colId == that.colId &&
                cellType == that.cellType &&
                tableId == that.tableId &&
                Objects.equals(cellValue, that.cellValue) &&
                Objects.equals(rowIndex, that.rowIndex) &&
                Objects.equals(colIndex, that.colIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rowId, colId, cellType, cellValue, tableId, rowIndex, colIndex);
    }

    @Override
    @Transient
    public Long getIdent() {
        return id;
    }

    @Override
    @Transient
    public String getVersion() {
        return null;
    }
}
