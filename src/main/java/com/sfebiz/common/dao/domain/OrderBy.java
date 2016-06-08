package com.sfebiz.common.dao.domain;

import java.io.Serializable;

/**
 * <p></p>
 * To change this template use File | Settings | File Templates.
 */
public class OrderBy implements Serializable {

    private String column;

    private String direction;

    public void setColumn(String column) {
        this.column = column;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public String getColumn() {
        return column;
    }
}
