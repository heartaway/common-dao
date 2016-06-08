package com.sfebiz.common.dao;

import com.sfebiz.common.dao.domain.BaseQuery;
import com.sfebiz.common.dao.domain.PageResult;
import com.sfebiz.common.dao.exception.DataAccessException;

import java.io.Serializable;
import java.util.List;

/**
 * User: <a href="mailto:lenolix@163.com">李星</a>
 * Version: 1.0.0
 * Since: 14/11/6 上午12:27
 */
public interface BaseService<T extends Serializable> {

    /**
     * 新建对象
     *
     * @param t 对象
     * @return z主键id
     * @throws
     */
    public void insert(T t) throws DataAccessException;

    /**
     * 新建对象或更新对象
     *
     * @param t
     * @return
     */
    public void insertOrUpdate(T t) throws DataAccessException;

    /**
     * 更新对象
     *
     * @param t 对象
     * @return
     * @throws
     */
    public int update(T t) throws DataAccessException;

    /**
     * ID查找对象
     *
     * @param id 对象ID
     * @return
     * @throws
     */
    public T getById(Long id) throws DataAccessException;

    /**
     * ID查找对象，会行锁
     *
     * @param id 对象ID
     * @return
     * @throws
     */
    public T getByIdForUpdate(Long id) throws DataAccessException;

    /**
     * 按条件查找
     *
     * @param query
     * @return
     */
    public List<T> query(BaseQuery<T> query) throws DataAccessException;

    /**
     * 分页查找
     *
     * @param query
     * @return
     * @throws DataAccessException
     */
    public PageResult<T> query4Page(BaseQuery<T> query) throws DataAccessException;

    /**
     * ID删除对象
     *
     * @param id
     * @return
     * @throws
     */
    public int deleteById(Long id) throws DataAccessException;

    /**
     * 删除对象
     *
     * @param query
     * @return
     * @throws
     */
    public int delete(BaseQuery<T> query) throws DataAccessException;

    /**
     * 按条件计数
     *
     * @param query
     * @return
     * @throws DataAccessException
     */
    public long count(BaseQuery<T> query) throws DataAccessException;

}
