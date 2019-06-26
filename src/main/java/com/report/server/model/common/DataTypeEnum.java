package com.report.server.model.common;

/**
 * @Description TODO
 * @Author huguangyin
 * @Date 2019/6/22-14:28
 *  
 */
public enum DataTypeEnum {
    /**
     * 0-short,1-int,2-long,3-double,4-float,5-String,6-boolean,7-byte,8-others
     */
    SHORT(0, ""),
    INT(1, ""),
    LONG(2, ""),
    DOUBLE(3, ""),
    FLOAT(4, ""),
    STRING(5, ""),
    BOOLEAN(6, ""),
    BYTE(7, ""),
    OTHERS(8, "");

    private int dataType;
    private String msg;

    DataTypeEnum(int dataType, String msg) {
        this.dataType = dataType;
        this.msg = msg;
    }

    public DataTypeEnum forCode(int dataType) {
        DataTypeEnum result = DataTypeEnum.OTHERS;
        for (DataTypeEnum dataTypeEnum : DataTypeEnum.values()) {
            if (dataType == dataTypeEnum.dataType) {
                result = dataTypeEnum;
                break;
            }
        }
        return result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }
}
