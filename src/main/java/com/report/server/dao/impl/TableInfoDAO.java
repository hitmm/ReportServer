package com.report.server.dao.impl;

import com.report.server.dao.service.ITableInfoDAO;
import com.report.server.model.common.PageEntity;
import com.report.server.model.dao.entity.TableInfoEntity;
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
public class TableInfoDAO extends BaseDAO implements ITableInfoDAO {

    private final static String DELETE_ID_HQL = "delete from TableInfoEntity where id = ?0";
    private final static String QUERY_TABLE_USER_SQL = "select * from table_info where table_owner = ? limit ?,?";
    private final static String COUNT_TABLE_HQL = "select count(1) from TableInfoEntity;";

    public static void main(String[] args) {
        new TableInfoDAO().deleteTableInfo(2L);
    }

    @Override
    public Long insertTableInfo(TableInfoEntity entity) throws Exception {
        return save(entity);
    }

    @Override
    public Long updateTableInfo(TableInfoEntity entity) throws Exception {
        return updateTableInfo(entity);
    }

    @Override
    public Long updateOrInsertTableInfo(TableInfoEntity entity) throws Exception {
        return saveOrUpdate(entity);
    }

    @Override
    public Integer countTableInfo() throws Exception {
        return count(COUNT_TABLE_HQL, null);
    }

    @Override
    public TableInfoEntity queryTableInfoById(Long id) throws Exception {
        return query(id, TableInfoEntity.class);
    }

    @Override
    public List<TableInfoEntity> queryTableInfoByUserId(Long userId, PageEntity page) throws Exception {
        int pageNum = 1;
        int pageSize = Integer.MAX_VALUE;
        if (page != null) {
            pageNum = page.getPageNum();
            pageSize = page.getPageSize();
        }
        int start = (pageNum - 1) * pageSize;
        int end = start + pageSize;
        return nativeQuery(QUERY_TABLE_USER_SQL, new Object[]{userId, start, end}, TableInfoEntity.class);
    }

    @Override
    public boolean deleteTableInfo(Long id) {
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
