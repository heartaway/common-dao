package com.sfebiz.common.dao.test.domain;

import com.sfebiz.common.dao.domain.BaseDO;

/**
 * User: <a href="mailto:lenolix@163.com">李星</a>
 * Version: 1.0.0
 * Since: 15/10/7 下午7:56
 */
public class DemoUniqueDO extends BaseDO {

    private static final long serialVersionUID = -6908585071754705170L;

    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
