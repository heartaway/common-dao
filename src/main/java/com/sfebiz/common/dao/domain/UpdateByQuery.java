package com.sfebiz.common.dao.domain;

/**
 * <p></p>
 * User: <a href="mailto:xinyuan.ymm@alibaba-inc.com">心远</a>
 * Date: 14/10/10
 * Time: 下午4:28
 */
public class UpdateByQuery<T> extends BaseQuery<T> {

    public T update;

    public UpdateByQuery(BaseQuery<T> baseQuery, T update) {
        if (update == null) {
            throw new NullPointerException("update cannot be null");
        }
        this.update = update;
        this.setData(baseQuery.getData());
        this.setCurrentPage(baseQuery.getCurrentPage());
        this.setPageSize(baseQuery.getPageSize());
        this.equals = baseQuery.equals;
        this.sum = baseQuery.sum;
        this.gtes = baseQuery.gtes;
        this.gts = baseQuery.gts;
        this.likes = baseQuery.likes;
        this.ltes = baseQuery.ltes;
        this.lts = baseQuery.lts;
        this.ins = baseQuery.ins;
        this.notIns = baseQuery.notIns;
        this.notEquals = baseQuery.notEquals;
        this.groupBys = baseQuery.groupBys;
        this.orderBys = baseQuery.orderBys;
        this.ranges = baseQuery.ranges;
    }

    public T getUpdate() {
        return update;
    }

    public void setUpdate(T update) {
        this.update = update;
    }
}