package com.sfebiz.common.dao.test.manager.impl;

import com.sfebiz.common.dao.BaseDao;
import com.sfebiz.common.dao.helper.DaoHelper;
import com.sfebiz.common.dao.impl.BaseServiceImpl;
import com.sfebiz.common.dao.test.dao.DemoFeatureDao;
import com.sfebiz.common.dao.test.domain.DemoFeatureDO;
import com.sfebiz.common.dao.test.manager.DemoFeatureManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * User: <a href="mailto:lenolix@163.com">李星</a>
 * Version: 1.0.0
 * Since: 14/11/6 上午12:38
 */
@Component("demoFeatureManager")
public class DemoFeatureManagerImpl extends BaseServiceImpl<DemoFeatureDO> implements DemoFeatureManager {

    @Resource
    DemoFeatureDao demoFeatureDao;

    /**
     * 生成本地SqlMap文件
     *
     * @param args
     */
    public static void main(String[] args) {
        DaoHelper.genXMLWithFeature("/Users/leno/Documents/work_at_alibaba/sf/haitao-common-dao/src/test/resources/sqlmap/testdemo-feature-sql.xml",
                DemoFeatureDao.class, DemoFeatureDO.class, "test_demo_feature");
    }

    @Override
    public BaseDao<DemoFeatureDO> getDao() {
        return this.demoFeatureDao;
    }

}
