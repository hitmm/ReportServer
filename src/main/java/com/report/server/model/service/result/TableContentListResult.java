package com.report.server.model.service.result;

import com.report.server.model.common.BaseResult;
import com.report.server.model.common.ErrorCode;
import com.report.server.model.service.dto.TableContentDTO;
import lombok.Data;

import java.util.List;

/**
 * @Description 返回data为List
 * @Author huguangyin
 * @Date 2019/6/22-9:37
 *  
 */
@Data
public class TableContentListResult extends BaseResult<List<TableContentDTO>> {
    private List<TableContentDTO> dtos;
    private Long id;

    public TableContentListResult(int code, String msg, Long id) {
        this.setCode(code);
        this.setMessage(msg);
        this.setId(id);
    }


    public static TableContentListResult success(Long id) {
        return new TableContentListResult(ErrorCode.CODE_SUCCESS, "SUCCESS", id);
    }

    public static TableContentListResult success(List<TableContentDTO> dtos) {
        TableContentListResult result = new TableContentListResult(ErrorCode.CODE_SUCCESS, "SUCCESS", null);
        result.setData(dtos);
        return result;
    }

    public static TableContentListResult failed(int errorCode) {
        return new TableContentListResult(errorCode, "UNKNOWN REASON", null);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public List<TableContentDTO> getData() {
        return dtos;
    }

    public void setData(List<TableContentDTO> dtos) {
        this.dtos = dtos;
    }
}
