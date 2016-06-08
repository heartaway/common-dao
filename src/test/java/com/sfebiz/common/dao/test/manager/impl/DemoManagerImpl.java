package com.sfebiz.common.dao.test.manager.impl;

import com.sfebiz.common.dao.BaseDao;
import com.sfebiz.common.dao.helper.DaoHelper;
import com.sfebiz.common.dao.impl.BaseServiceImpl;
import com.sfebiz.common.dao.test.dao.DemoDao;
import com.sfebiz.common.dao.test.domain.DemoDO;
import com.sfebiz.common.dao.test.manager.DemoManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * User: <a href="mailto:lenolix@163.com">李星</a>
 * Version: 1.0.0
 * Since: 14/11/6 上午12:38
 */
@Component("demoManager")
public class DemoManagerImpl extends BaseServiceImpl<DemoDO> implements DemoManager {

    @Resource
    DemoDao demoDao;

    /**
     * 生成本地SqlMap文件
     *
     * @param args
     */
    public static void main(String[] args) {
        DaoHelper.genXML("/Users/leno/Documents/work_at_alibaba/sf/haitao-common-dao/src/test/resources/sqlmap/testdemo-sql.xml",
                DemoDao.class, DemoDO.class, "test_demo");
    }

    @Override
    public BaseDao<DemoDO> getDao() {
        return this.demoDao;
    }

}
