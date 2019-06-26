package com.report.server.service.impl;

import com.report.server.dao.impl.ReferenceInfoDAO;
import com.report.server.model.dao.entity.ReferenceInfoEntity;
import com.report.server.service.service.IReferenceInfoService;
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
@Service("referenceInfoService")
@Component
public class ReferenceInfoServiceImpl implements IReferenceInfoService {

    @Autowired
    private ReferenceInfoDAO referenceInfoDAO;

    public ReferenceInfoServiceImpl(ReferenceInfoDAO referenceInfoDAO) {
        this.referenceInfoDAO = referenceInfoDAO;
    }

    @Override
    public ReferenceInfoEntity queryReferenceInfo(Long id) throws Exception {
        return referenceInfoDAO.queryReferenceInfoById(id);
    }

    @Override
    public List<ReferenceInfoEntity> queryReferenceInfoByTableId(Long tableId) throws Exception {
        return referenceInfoDAO.queryReferenceInfoByTableId(tableId);
    }

    @Override
    public Integer countReferenceInfo() throws Exception {
        return referenceInfoDAO.countReferenceInfo();
    }

    @Override
    public Long insertReferenceInfo(ReferenceInfoEntity entity) throws Exception {
        return referenceInfoDAO.insertReferenceInfo(entity);
    }

    @Override
    public Long insertOrUpdateReferenceInfo(ReferenceInfoEntity entity) throws Exception {
        return referenceInfoDAO.updateOrInsertReferenceInfo(entity);
    }

    @Override
    public Long updateReferenceInfo(ReferenceInfoEntity entity) throws Exception {
        return referenceInfoDAO.updateReferenceInfo(entity);
    }

    @Override
    public void deleteReferenceInfo(Long id) throws Exception {
        referenceInfoDAO.deleteReferenceInfo(id);
    }

    public void setReferenceInfoDAO(ReferenceInfoDAO referenceInfoDAO) {
        this.referenceInfoDAO = referenceInfoDAO;
    }
}
