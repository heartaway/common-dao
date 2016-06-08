package com.sfebiz.common.dao.domain;

import java.io.Serializable;

/**
 * <p></p>
 * User: <a href="mailto:xinyuan.ymm@alibaba-inc.com">心远</a>
 * Date: 14/10/10
 * Time: 下午4:00
 */
public class Range implements Serializable {

    private String column;
    /**
     * >= from
     */

    private Object from;
    private Object to;


    public void setColumn(String column) {
        this.column = column;
    }

    public void setFrom(Object from) {
        this.from = from;
    }

    public void setTo(Object to) {
        this.to = to;
    }

    public String getColumn() {
        return column;
    }

    public Object getFrom() {
        return from;
    }

    public Object getTo() {
        return to;
    }

    public Range(String column, Object from, Object to) {
        this.column = column;
        this.from = from;
        this.to = to;
    }
}
