package com.sfebiz.common.dao.test.domain;

import com.sfebiz.common.dao.domain.BaseDO;

/**
 * User: <a href="mailto:lenolix@163.com">李星</a>
 * Version: 1.0.0
 * Since: 14/11/5 上午11:48
 */
public class DemoDO extends BaseDO {


    private static final long serialVersionUID = -4304727011455272268L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
