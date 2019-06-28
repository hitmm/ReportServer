package com.report.server.web.controller;

import com.report.server.model.common.PageEntity;
import com.report.server.model.service.result.TableContentListResult;
import com.report.server.service.service.ITableContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author huguangyin
 * @Date 2019/6/26-17:41
 *  
 */
@RestController
public class TableContentController {

    @Autowired
    private ITableContentService tableContentService;

    @GetMapping(value = "/table/{userId}/{pageNum}/{pageSize}")
    public TableContentListResult getTableContent(@PathVariable("userId")Long userId,
                                                  @PathVariable("pageNum")int pageNum,
                                                  @PathVariable("pageSize")int pageSize) throws Exception {
        PageEntity entity = new PageEntity();
        entity.setPageNum(pageNum);
        entity.setPageSize(pageSize);
        return tableContentService.queryTableContentByUserId(userId,entity);
    }
}
