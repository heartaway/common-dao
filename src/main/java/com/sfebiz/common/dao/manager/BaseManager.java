package com.sfebiz.common.dao.manager;

import com.sfebiz.common.dao.BaseDao;
import com.sfebiz.common.dao.BaseService;
import com.sfebiz.common.dao.domain.BaseQuery;
import com.sfebiz.common.dao.domain.PageResult;
import com.sfebiz.common.dao.domain.UpdateByQuery;
import com.sfebiz.common.dao.exception.DataAccessException;

import java.io.Serializable;
import java.util.List;

/**
 * User: <a href="mailto:yanmingming1989@163.com">心远</a>
 * Version: 1.0.2
 * Since: 14/12/13 上午12:31
 */
public abstract class BaseManager<DO extends Serializable> {

    public abstract BaseDao<DO> getDao();

    public void insert(DO aDo) throws DataAccessException {
        this.getDao().insert(aDo);
    }

    public void insertOrUpdate(DO aDo) throws DataAccessException {
        this.getDao().insertOrUpdate(aDo);
    }

    public int update(DO aDo) throws DataAccessException {
        return this.getDao().update(aDo);
    }

    public int updateByQuery(UpdateByQuery<DO> uDO) throws DataAccessException {
        return this.getDao().updateByQuery(uDO);
    }

    public DO getById(Long id) throws DataAccessException {
        return this.getDao().getById(id);
    }

    public DO getByIdForUpdate(Long id) throws DataAccessException {
        return this.getDao().getByIdForUpdate(id);
    }

    public List<DO> query(BaseQuery<DO> query) throws DataAccessException {
        return this.getDao().query(query);
    }

    public PageResult<DO> query4Page(BaseQuery<DO> query) throws DataAccessException {
        PageResult<DO> pResult = new PageResult<DO>();
        pResult.setCurrentPage(query.getCurrentPage());
        pResult.setPageSize(query.getPageSize());
        long count = this.count(query);
        List<DO> result = this.getDao().query4Page(query);
        pResult.setTotalItem((int) count);
        pResult.setResult(result);
        return pResult;
    }

    public int deleteById(Long id) throws DataAccessException {
        return this.getDao().deleteById(id);
    }

    public int delete(BaseQuery<DO> query) throws DataAccessException {
        return this.getDao().delete(query);
    }

    public long count(BaseQuery<DO> query) throws DataAccessException {
        return this.getDao().count(query);
    }

}

