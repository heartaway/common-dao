package com.sfebiz.common.dao.test.manager.impl;

import com.sfebiz.common.dao.BaseDao;
import com.sfebiz.common.dao.impl.BaseServiceImpl;
import com.sfebiz.common.dao.test.dao.DemoUniqueDao;
import com.sfebiz.common.dao.test.domain.DemoUniqueDO;
import com.sfebiz.common.dao.test.manager.DemoUniqueManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * User: <a href="mailto:lenolix@163.com">李星</a>
 * Version: 1.0.0
 * Since: 15/10/7 下午7:59
 */
@Component("demoUniqueManager")
public class DemoUniqueManagerImpl extends BaseServiceImpl<DemoUniqueDO> implements DemoUniqueManager {

    @Resource
    DemoUniqueDao demoUniqueDao;

    @Override
    public BaseDao<DemoUniqueDO> getDao() {
        return demoUniqueDao;
    }
}
