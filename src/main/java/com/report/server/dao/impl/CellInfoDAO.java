package com.report.server.dao.impl;

import com.report.server.dao.service.ICellInfoDAO;
import com.report.server.model.dao.entity.CellInfoEntity;
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
public class CellInfoDAO extends BaseDAO implements ICellInfoDAO {

    private final static String DELETE_ID_HQL = "delete from CellInfoEntity where id = ?0";
    private final static String QUERY_COL_ROW_HQL = "from CellInfoEntity where rowId = ?0 and colId = ?1";
    private final static String QUERY_ROW_HQL = "from CellInfoEntity where rowId = ?0";
    private final static String QUERY_COL_HQL = "from CellInfoEntity where colId = ?0";
    private final static String COUNT_CELL_HQL = "select count(1) from cellInfo;";

    public static void main(String[] args) {
        new CellInfoDAO().deleteCellInfo(2L);
    }

    @Override
    public Long insertCellInfo(CellInfoEntity entity) throws Exception {
        return save(entity);
    }

    @Override
    public Long updateOrInsertCellInfo(CellInfoEntity entity) throws Exception {
        return saveOrUpdate(entity);
    }

    @Override
    public Long updateCellInfo(CellInfoEntity entity) throws Exception {
        return updateCellInfo(entity);
    }

    @Override
    public Integer countCellInfo() throws Exception {
        return count(COUNT_CELL_HQL, null);
    }

    @Override
    public CellInfoEntity queryCellInfoById(Long id) throws Exception {
        return query(id, CellInfoEntity.class);
    }

    @Override
    public CellInfoEntity queryCellInfoByRowColId(Long rowId, Long colId) throws Exception {
        return query(QUERY_COL_ROW_HQL, new Object[]{rowId, colId});
    }

    @Override
    public List<CellInfoEntity> queryCellInfoByRowId(Long rowId) throws Exception {
        return query(QUERY_ROW_HQL, new Object[]{rowId});
    }

    @Override
    public List<CellInfoEntity> queryCellInfoByColId(Long colId) throws Exception {
        return query(QUERY_COL_HQL, new Object[]{colId});
    }

    @Override
    public boolean deleteCellInfo(Long id) {
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
