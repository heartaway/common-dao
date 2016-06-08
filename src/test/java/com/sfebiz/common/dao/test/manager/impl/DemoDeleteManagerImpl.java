package com.sfebiz.common.dao.test.manager.impl;

import com.sfebiz.common.dao.BaseDao;
import com.sfebiz.common.dao.impl.BaseServiceImpl;
import com.sfebiz.common.dao.test.dao.DemoDeleteDao;
import com.sfebiz.common.dao.test.domain.DemoDeleteDO;
import com.sfebiz.common.dao.test.manager.DemoDeleteManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * User: <a href="mailto:lenolix@163.com">李星</a>
 * Version: 1.0.0
 * Since: 15/1/2 下午3:53
 */
@Component("demoDeleteManager")
public class DemoDeleteManagerImpl extends BaseServiceImpl<DemoDeleteDO> implements DemoDeleteManager {

    @Resource
    DemoDeleteDao demoDeleteDao;

    @Override
    public BaseDao<DemoDeleteDO> getDao() {
        return demoDeleteDao;
    }
}
