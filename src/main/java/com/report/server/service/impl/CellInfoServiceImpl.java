package com.report.server.service.impl;

import com.report.server.dao.impl.CellInfoDAO;
import com.report.server.model.dao.entity.CellInfoEntity;
import com.report.server.service.service.ICellInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author huguangyin
 * @Date 2019/6/20-13:47
 *  
 */
@Service("cellInfoService")
@Component
public class CellInfoServiceImpl implements ICellInfoService {

    @Autowired
    private CellInfoDAO cellInfoDAO;

    public CellInfoServiceImpl(CellInfoDAO cellInfoDAO) {
        this.cellInfoDAO = cellInfoDAO;
    }

    @Override
    public CellInfoEntity queryCellInfo(Long id) throws Exception {
        return cellInfoDAO.queryCellInfoById(id);
    }

    @Override
    public CellInfoEntity queryCellInfoByRowColId(Long rowId, Long colId) throws Exception {
        return cellInfoDAO.queryCellInfoByRowColId(rowId, colId);
    }

    @Override
    public List<CellInfoEntity> queryCellInfoByRowId(Long rowId) throws Exception {
        return cellInfoDAO.queryCellInfoByRowId(rowId);
    }

    @Override
    public List<CellInfoEntity> queryCellInfoByColId(Long colId) throws Exception {
        return cellInfoDAO.queryCellInfoByColId(colId);
    }

    @Override
    public Integer countCellInfo() throws Exception {
        return cellInfoDAO.countCellInfo();
    }

    @Override
    public Long insertCellInfo(CellInfoEntity entity) throws Exception {
        return cellInfoDAO.insertCellInfo(entity);
    }

    @Override
    public Long insertOrUpdateCellInfo(CellInfoEntity entity) throws Exception {
        return cellInfoDAO.updateOrInsertCellInfo(entity);
    }

    @Override
    public Long updateCellInfo(CellInfoEntity entity) throws Exception {
        return cellInfoDAO.updateCellInfo(entity);
    }

    @Override
    public void deleteCellInfo(Long id) throws Exception {
        cellInfoDAO.deleteCellInfo(id);
    }

    public void setCellInfoDAO(CellInfoDAO cellInfoDAO) {
        this.cellInfoDAO = cellInfoDAO;
    }
}
