package com.report.server.model.service.result;

import com.report.server.model.common.BaseResult;
import com.report.server.model.common.ErrorCode;
import com.report.server.model.service.dto.TableContentDTO;
import lombok.Data;

/**
 * @Description 返回data为单个DTO
 * @Author huguangyin
 * @Date 2019/6/22-9:37
 *  
 */
@Data
public class TableContentResult extends BaseResult<TableContentDTO> {
    private TableContentDTO dto;
    private Long id;

    public TableContentResult(int code, String msg, Long id) {
        this.setCode(code);
        this.setMessage(msg);
        this.setId(id);
    }


    public static TableContentResult success(Long id) {
        return new TableContentResult(ErrorCode.CODE_SUCCESS, "SUCCESS", id);
    }

    public static TableContentResult success(TableContentDTO dto) {
        TableContentResult result = new TableContentResult(ErrorCode.CODE_SUCCESS, "SUCCESS", dto.getTableId());
        result.setData(dto);
        return result;
    }

    public static TableContentResult failed(int errorCode, String msg) {
        return new TableContentResult(errorCode, msg, null);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public TableContentDTO getData() {
        return dto;
    }

    public void setData(TableContentDTO dto) {
        this.dto = dto;
    }
}
