package com.sfebiz.common.dao.domain;

import java.util.List;

/**
 * <p></p>
 * User: <a href="mailto:xinyuan.ymm@alibaba-inc.com">心远</a>
 * Date: 14/10/10
 * Time: 下午4:00
 */
public class PageResult<E> extends BaseParms {
    private List<E> result;

    public void setResult(List<E> result) {
        this.result = result;
    }

    public List<E> getResult() {
        return result;
    }

    public PageResult() {
    }
}
