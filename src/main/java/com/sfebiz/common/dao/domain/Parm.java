package com.sfebiz.common.dao.domain;

import java.io.Serializable;

/**
 * <p></p>
 * User: <a href="mailto:xinyuan.ymm@alibaba-inc.com">心远</a>
 * Date: 14/10/10
 * Time: 下午3:59
 */
public class Parm implements Serializable {

    private String column;

    private Object value;

    public void setColumn(String column) {
        this.column = column;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getColumn() {
        return column;
    }

    public Object getValue() {
        return value;
    }

    public Parm(String column, Object value) {
        this.column = column;
        this.value = value;
    }
}