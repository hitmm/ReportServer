package com.report.server.dao.impl;

import com.report.server.dao.service.IReferenceInfoDAO;
import com.report.server.model.dao.entity.ReferenceInfoEntity;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description TODO
 * @Author huguangyin
 * @Date 2019/6/19-18:15
 *  
 */
@Component
public class ReferenceInfoDAO extends BaseDAO implements IReferenceInfoDAO {

    private final static String DELETE_ID_HQL = "delete from ReferenceInfoEntity where id = ?0";
    private final static String QUERY_REFERENCE_TABLEID_HQL = "from ReferenceInfoEntity where tableId = ?0";
    private final static String COUNT_REFERENCE_HQL = "select count(1) from ReferenceInfoEntity;";

    public static void main(String[] args) {
        new ReferenceInfoDAO().deleteReferenceInfo(2L);
    }

    @Override
    public Long insertReferenceInfo(ReferenceInfoEntity entity) throws Exception {
        return save(entity);
    }

    @Override
    public Long updateReferenceInfo(ReferenceInfoEntity entity) throws Exception {
        return updateReferenceInfo(entity);
    }

    @Override
    public Long updateOrInsertReferenceInfo(ReferenceInfoEntity entity) throws Exception {
        return saveOrUpdate(entity);
    }

    @Override
    public Integer countReferenceInfo() throws Exception {
        return count(COUNT_REFERENCE_HQL, null);
    }

    @Override
    public ReferenceInfoEntity queryReferenceInfoById(Long id) throws Exception {
        return query(id, ReferenceInfoEntity.class);
    }

    @Override
    public List<ReferenceInfoEntity> queryReferenceInfoByTableId(Long tableId) throws Exception {
        return query(QUERY_REFERENCE_TABLEID_HQL, new Object[]{tableId});
    }

    @Override
    public boolean deleteReferenceInfo(Long id) {
        delete(DELETE_ID_HQL, new Object[]{id});
        return true;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
