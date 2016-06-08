package com.sfebiz.common.dao.domain;

import java.util.List;

/**
 * <p></p>
 * User: <a href="mailto:xinyuan.ymm@alibaba-inc.com">心远</a>
 * Date: 14/10/10
 * Time: 下午3:59
 */
public class InOrNotin {

    private String column;
    private List<Object> values;

    public void setColumn(String column) {
        this.column = column;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }

    public List<Object> getValues() {

        return values;
    }

    public String getColumn() {
        return column;
    }

}
