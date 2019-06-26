package com.report.server.model.dao.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Description TODO
 * @Author huguangyin
 * @Date 2019/6/26-15:46
 *  
 */
@Entity
@Table(name = "table_info", schema = "report")
public class TableInfoEntity implements IEntity{
    private long id;
    private String tableName;
    private String tableAlias;
    private Long tableSize;
    private Long tableRowCount;
    private Long tableOwner;

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
    @Column(name = "table_name")
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Basic
    @Column(name = "table_alias")
    public String getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    @Basic
    @Column(name = "table_size")
    public Long getTableSize() {
        return tableSize;
    }

    public void setTableSize(Long tableSize) {
        this.tableSize = tableSize;
    }

    @Basic
    @Column(name = "table_row_count")
    public Long getTableRowCount() {
        return tableRowCount;
    }

    public void setTableRowCount(Long tableRowCount) {
        this.tableRowCount = tableRowCount;
    }

    @Basic
    @Column(name = "table_owner")
    public Long getTableOwner() {
        return tableOwner;
    }

    public void setTableOwner(Long tableOwner) {
        this.tableOwner = tableOwner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TableInfoEntity that = (TableInfoEntity) o;
        return id == that.id &&
                Objects.equals(tableName, that.tableName) &&
                Objects.equals(tableAlias, that.tableAlias) &&
                Objects.equals(tableSize, that.tableSize) &&
                Objects.equals(tableRowCount, that.tableRowCount) &&
                Objects.equals(tableOwner, that.tableOwner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tableName, tableAlias, tableSize, tableRowCount, tableOwner);
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
