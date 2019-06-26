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
@Table(name = "reference_info", schema = "report")
public class ReferenceInfoEntity implements IEntity{
    private long id;
    private String referenceName;
    private String referenceAlias;
    private byte referenceType;
    private long referenceLength;
    private int referenceDataType;
    private long tableId;
    private String referenceIndex;

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
    @Column(name = "reference_name")
    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    @Basic
    @Column(name = "reference_alias")
    public String getReferenceAlias() {
        return referenceAlias;
    }

    public void setReferenceAlias(String referenceAlias) {
        this.referenceAlias = referenceAlias;
    }

    @Basic
    @Column(name = "reference_type")
    public byte getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(byte referenceType) {
        this.referenceType = referenceType;
    }

    @Basic
    @Column(name = "reference_length")
    public long getReferenceLength() {
        return referenceLength;
    }

    public void setReferenceLength(long referenceLength) {
        this.referenceLength = referenceLength;
    }

    @Basic
    @Column(name = "reference_data_type")
    public int getReferenceDataType() {
        return referenceDataType;
    }

    public void setReferenceDataType(int referenceDataType) {
        this.referenceDataType = referenceDataType;
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
    @Column(name = "reference_index")
    public String getReferenceIndex() {
        return referenceIndex;
    }

    public void setReferenceIndex(String referenceIndex) {
        this.referenceIndex = referenceIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReferenceInfoEntity that = (ReferenceInfoEntity) o;
        return id == that.id &&
                referenceType == that.referenceType &&
                referenceLength == that.referenceLength &&
                referenceDataType == that.referenceDataType &&
                tableId == that.tableId &&
                Objects.equals(referenceName, that.referenceName) &&
                Objects.equals(referenceAlias, that.referenceAlias) &&
                Objects.equals(referenceIndex, that.referenceIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, referenceName, referenceAlias, referenceType, referenceLength, referenceDataType, tableId, referenceIndex);
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
