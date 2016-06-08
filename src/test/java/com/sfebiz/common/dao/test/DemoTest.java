package com.sfebiz.common.dao.test;

import com.sfebiz.common.dao.domain.BaseQuery;
import com.sfebiz.common.dao.domain.PageResult;
import com.sfebiz.common.dao.helper.DaoHelper;
import com.sfebiz.common.dao.test.dao.DemoDao;
import com.sfebiz.common.dao.test.dao.DemoDeleteDao;
import com.sfebiz.common.dao.test.dao.DemoFeatureDao;
import com.sfebiz.common.dao.test.dao.DemoUniqueDao;
import com.sfebiz.common.dao.test.domain.DemoDO;
import com.sfebiz.common.dao.test.domain.DemoDeleteDO;
import com.sfebiz.common.dao.test.domain.DemoFeatureDO;
import com.sfebiz.common.dao.test.domain.DemoUniqueDO;
import com.sfebiz.common.dao.test.manager.DemoDeleteManager;
import com.sfebiz.common.dao.test.manager.DemoFeatureManager;
import com.sfebiz.common.dao.test.manager.DemoManager;
import com.sfebiz.common.dao.test.manager.DemoUniqueManager;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: <a href="mailto:lenolix@163.com">李星</a>
 * Version: 1.0.0
 * Since: 14/11/5 下午12:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-persistent.xml")
public class DemoTest {

    @Resource
    DemoManager demoManager;

    @Resource
    DemoFeatureManager demoFeatureManager;

    @Resource
    DemoDeleteManager demoDeleteManager;

    @Resource
    DemoUniqueManager demoUniqueManager;

    List<Long> ids = new ArrayList<Long>();

    @Before
    public void setup() {

    }

    @After
    public void tearDown() {
        if (null != ids) {
            for (Long id : ids) {
                demoManager.deleteById(id);
                demoDeleteManager.deleteById(id);
                demoFeatureManager.deleteById(id);
                demoUniqueManager.deleteById(id);
            }
        }
    }

//    @Test
//    public void GenerateSqlMap() {
//        DaoHelper.genXMLWithFeature("/Users/leno/Documents/work_at_alibaba/sf/haitao-common-dao/src/test/resources/sqlmap/demo-sql.xml",
//                DemoDao.class, DemoDO.class, "common_dao_demo");
//        DaoHelper.genXMLWithFeature("/Users/leno/Documents/work_at_alibaba/sf/haitao-common-dao/src/test/resources/sqlmap/demo-feature-sql.xml",
//                DemoFeatureDao.class, DemoFeatureDO.class, "common_dao_demo_feature");
//        DaoHelper.genXMLWithFeature("/Users/leno/Documents/work_at_alibaba/sf/haitao-common-dao/src/test/resources/sqlmap/demo-delete-sql.xml",
//                DemoDeleteDao.class, DemoDeleteDO.class, "common_dao_demo_delete", false);
//        DaoHelper.genXMLWithFeature("/Users/leno/Documents/work_at_alibaba/sf/haitao-common-dao/src/test/resources/sqlmap/demo-unique-sql.xml",
//                DemoUniqueDao.class, DemoUniqueDO.class, "common_dao_demo_unique", true);
//    }


    /**
     * 测试包含唯一索引的表，使用insertOrUpdate
     */
    @Test
    public void TestDemoUniqueCRUD() {
        DemoUniqueDO insert = new DemoUniqueDO();
        insert.setName("lenolixtest");
        insert.setCode("uniquecode");
        insert.setId(0L);
        demoUniqueManager.insertOrUpdate(insert);

        Long id = insert.getId();
        ids.add(insert.getId());

        System.out.println("id:" + insert.getId());

        DemoUniqueDO get = demoUniqueManager.getByIdForUpdate(insert.getId());
        Assert.assertNotNull(get);
        Assert.assertEquals("lenolixtest", get.getName());

        BaseQuery<DemoUniqueDO> query = new BaseQuery<DemoUniqueDO>(new DemoUniqueDO());
        query.addLike("name", "lix");
        List<DemoUniqueDO> queryResult = demoUniqueManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(1, queryResult.size());

        query = new BaseQuery<DemoUniqueDO>(new DemoUniqueDO());
        query.addIn("name", Arrays.asList(new String[]{"lenolixtest", "lenolixtest2"}));
        queryResult = demoUniqueManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(1, queryResult.size());

        query = new BaseQuery<DemoUniqueDO>(new DemoUniqueDO());
        query.addNotIn("name", Arrays.asList(new String[]{"lenolixtest", "lenolixtest2"}));
        queryResult = demoUniqueManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(0, queryResult.size());

        query = new BaseQuery<DemoUniqueDO>(new DemoUniqueDO());
        query.addGt("name", "leno");
        queryResult = demoUniqueManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(1, queryResult.size());

        query = new BaseQuery<DemoUniqueDO>(new DemoUniqueDO());
        query.addGte("name", "lenolixtest");
        query.addGt("id", 0);
        queryResult = demoUniqueManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(1, queryResult.size());

        query = new BaseQuery<DemoUniqueDO>(new DemoUniqueDO());
        query.addLt("name", "lenolixtest1");
        queryResult = demoUniqueManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(1, queryResult.size());

        query = new BaseQuery<DemoUniqueDO>(new DemoUniqueDO());
        query.addLte("name", "lenolixtest");
        queryResult = demoUniqueManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(1, queryResult.size());

        query = new BaseQuery<DemoUniqueDO>(new DemoUniqueDO());
        query.addRange("name", "lenolixtest", "lenolixtestMax");
        queryResult = demoUniqueManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(1, queryResult.size());

        query = new BaseQuery<DemoUniqueDO>(new DemoUniqueDO());
        query.addRange("name", "lenolix", "lenolixtest");
        queryResult = demoUniqueManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(0, queryResult.size());

        query = new BaseQuery<DemoUniqueDO>(new DemoUniqueDO());
        query.addRange("name", "lenolix", "lenolixtest");
        queryResult = demoUniqueManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(0, queryResult.size());

        insert.setName("new lenolixtest");
        demoUniqueManager.insertOrUpdate(insert);

        get = demoUniqueManager.getById(insert.getId());
        Assert.assertNotNull(get);
        Assert.assertEquals("new lenolixtest", get.getName());

        demoUniqueManager.deleteById(id);
        get = demoUniqueManager.getById(insert.getId());

        Assert.assertNull(get);

        demoUniqueManager.insertOrUpdate(insert);
        get = demoUniqueManager.getById(insert.getId());
        Assert.assertNotNull(get);
        Assert.assertEquals("new lenolixtest", get.getName());

    }

    @Test
    public void TestDemoCRUD() {
        DemoDO insert = new DemoDO();
        insert.setName("lenolixtest");
        demoManager.insertOrUpdate(insert);

        Long id = insert.getId();
        ids.add(insert.getId());

        System.out.println("id:" + insert.getId());

        DemoDO get = demoManager.getByIdForUpdate(insert.getId());
        Assert.assertNotNull(get);
        Assert.assertEquals("lenolixtest", get.getName());

        BaseQuery<DemoDO> query = new BaseQuery<DemoDO>(new DemoDO());
        query.addLike("name", "lix");
        List<DemoDO> queryResult = demoManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(1, queryResult.size());

        query = new BaseQuery<DemoDO>(new DemoDO());
        query.addIn("name", Arrays.asList(new String[]{"lenolixtest", "lenolixtest2"}));
        queryResult = demoManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(1, queryResult.size());

        query = new BaseQuery<DemoDO>(new DemoDO());
        query.addNotIn("name", Arrays.asList(new String[]{"lenolixtest", "lenolixtest2"}));
        queryResult = demoManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(0, queryResult.size());

        query = new BaseQuery<DemoDO>(new DemoDO());
        query.addGt("name", "leno");
        queryResult = demoManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(1, queryResult.size());

        query = new BaseQuery<DemoDO>(new DemoDO());
        query.addGte("name", "lenolixtest");
        query.addGt("id", 1);
        queryResult = demoManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(1, queryResult.size());

        query = new BaseQuery<DemoDO>(new DemoDO());
        query.addLt("name", "lenolixtest1");
        queryResult = demoManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(1, queryResult.size());

        query = new BaseQuery<DemoDO>(new DemoDO());
        query.addLte("name", "lenolixtest");
        queryResult = demoManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(1, queryResult.size());

        query = new BaseQuery<DemoDO>(new DemoDO());
        query.addRange("name", "lenolixtest", "lenolixtestMax");
        queryResult = demoManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(1, queryResult.size());

        query = new BaseQuery<DemoDO>(new DemoDO());
        query.addRange("name", "lenolix", "lenolixtest");
        queryResult = demoManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(0, queryResult.size());

        query = new BaseQuery<DemoDO>(new DemoDO());
        query.addRange("name", "lenolix", "lenolixtest");
        queryResult = demoManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(0, queryResult.size());

        insert.setName("new lenolixtest");
        demoManager.insertOrUpdate(insert);

        get = demoManager.getById(insert.getId());
        Assert.assertNotNull(get);
        Assert.assertEquals("new lenolixtest", get.getName());

        demoManager.deleteById(id);

        get = demoManager.getById(insert.getId());
        Assert.assertNull(get);

    }

    @Test
    public void TestDemoFeatureCRUD() {

        DemoFeatureDO insert = new DemoFeatureDO();
        insert.setName("lenolixtest");
        insert.addFeature("key1", 1);
        insert.addFeature("key2", "2");
        demoFeatureManager.insert(insert);

        Long id = insert.getId();
        ids.add(insert.getId());

        System.out.println("id:" + insert.getId());

        DemoFeatureDO get = demoFeatureManager.getById(insert.getId());
        Assert.assertNotNull(get);
        Assert.assertEquals("lenolixtest", get.getName());
        Assert.assertEquals(1, get.getFeature("key1"));
        Assert.assertEquals("2", get.getFeature("key2"));


        BaseQuery<DemoFeatureDO> query = new BaseQuery<DemoFeatureDO>(new DemoFeatureDO());
        query.addLike("name", "lix");
        List<DemoFeatureDO> queryResult = demoFeatureManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(1, queryResult.size());

        query = new BaseQuery<DemoFeatureDO>(new DemoFeatureDO());
        query.addIn("name", Arrays.asList(new String[]{"lenolixtest", "lenolixtest2"}));
        queryResult = demoFeatureManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(1, queryResult.size());

        query = new BaseQuery<DemoFeatureDO>(new DemoFeatureDO());
        query.addNotIn("name", Arrays.asList(new String[]{"lenolixtest", "lenolixtest2"}));
        queryResult = demoFeatureManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(0, queryResult.size());

        query = new BaseQuery<DemoFeatureDO>(new DemoFeatureDO());
        query.addGt("name", "leno");
        queryResult = demoFeatureManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(1, queryResult.size());

        query = new BaseQuery<DemoFeatureDO>(new DemoFeatureDO());
        query.addGte("name", "lenolixtest");
        queryResult = demoFeatureManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(1, queryResult.size());

        query = new BaseQuery<DemoFeatureDO>(new DemoFeatureDO());
        query.addLt("name", "lenolixtest1");
        queryResult = demoFeatureManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(1, queryResult.size());

        query = new BaseQuery<DemoFeatureDO>(new DemoFeatureDO());
        query.addLte("name", "lenolixtest");
        queryResult = demoFeatureManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(1, queryResult.size());

        query = new BaseQuery<DemoFeatureDO>(new DemoFeatureDO());
        query.addRange("name", "lenolixtest", "lenolixtestMax");
        queryResult = demoFeatureManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(1, queryResult.size());

        query = new BaseQuery<DemoFeatureDO>(new DemoFeatureDO());
        query.addRange("name", "lenolix", "lenolixtest");
        queryResult = demoFeatureManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(0, queryResult.size());

        query = new BaseQuery<DemoFeatureDO>(new DemoFeatureDO());
        query.addRange("name", "lenolix", "lenolixtest");
        queryResult = demoFeatureManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(0, queryResult.size());

        insert.setName("new lenolixtest");
        demoFeatureManager.update(insert);

        get = demoFeatureManager.getById(insert.getId());
        Assert.assertNotNull(get);
        Assert.assertEquals("new lenolixtest", get.getName());

        demoFeatureManager.deleteById(id);

        get = demoFeatureManager.getById(insert.getId());
        Assert.assertNull(get);

    }

    /**
     * 测试物理删除的逻辑
     */
    @Test
    public void TestDemoDeleteCRUD() {
        DemoDeleteDO insert = new DemoDeleteDO();
        insert.setName("lenolixtest");
        demoDeleteManager.insert(insert);

        Long id = insert.getId();
        ids.add(insert.getId());

        System.out.println("id:" + insert.getId());

        DemoDeleteDO get = demoDeleteManager.getByIdForUpdate(insert.getId());
        Assert.assertNotNull(get);
        Assert.assertEquals("lenolixtest", get.getName());

        BaseQuery<DemoDeleteDO> query = new BaseQuery<DemoDeleteDO>(new DemoDeleteDO());
        query.addLike("name", "lix");
        List<DemoDeleteDO> queryResult = demoDeleteManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(1, queryResult.size());

        query = new BaseQuery<DemoDeleteDO>(new DemoDeleteDO());
        query.addIn("name", Arrays.asList(new String[]{"lenolixtest", "lenolixtest2"}));
        queryResult = demoDeleteManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(1, queryResult.size());

        query = new BaseQuery<DemoDeleteDO>(new DemoDeleteDO());
        query.addNotIn("name", Arrays.asList(new String[]{"lenolixtest", "lenolixtest2"}));
        queryResult = demoDeleteManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(0, queryResult.size());

        query = new BaseQuery<DemoDeleteDO>(new DemoDeleteDO());
        query.addGt("name", "leno");
        queryResult = demoDeleteManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(1, queryResult.size());

        query = new BaseQuery<DemoDeleteDO>(new DemoDeleteDO());
        query.addGte("name", "lenolixtest");
        query.addGt("id", 1);
        queryResult = demoDeleteManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(1, queryResult.size());

        query = new BaseQuery<DemoDeleteDO>(new DemoDeleteDO());
        query.addLt("name", "lenolixtest1");
        queryResult = demoDeleteManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(1, queryResult.size());

        query = new BaseQuery<DemoDeleteDO>(new DemoDeleteDO());
        query.addLte("name", "lenolixtest");
        queryResult = demoDeleteManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(1, queryResult.size());

        query = new BaseQuery<DemoDeleteDO>(new DemoDeleteDO());
        query.addRange("name", "lenolixtest", "lenolixtestMax");
        queryResult = demoDeleteManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(1, queryResult.size());

        query = new BaseQuery<DemoDeleteDO>(new DemoDeleteDO());
        query.addRange("name", "lenolix", "lenolixtest");
        queryResult = demoDeleteManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(0, queryResult.size());

        query = new BaseQuery<DemoDeleteDO>(new DemoDeleteDO());
        query.addRange("name", "lenolix", "lenolixtest");
        queryResult = demoDeleteManager.query(query);
        Assert.assertNotNull(queryResult);
        Assert.assertEquals(0, queryResult.size());

        insert.setName("new lenolixtest");
        demoDeleteManager.update(insert);

        get = demoDeleteManager.getById(insert.getId());
        Assert.assertNotNull(get);
        Assert.assertEquals("new lenolixtest", get.getName());

        demoDeleteManager.deleteById(id);

        get = demoDeleteManager.getById(insert.getId());
        Assert.assertNull(get);

    }


    @Test
    public void TestQueryPage() {

        try {
            DemoDO insert1 = new DemoDO();
            insert1.setName("lenolixtest1");
            demoManager.insert(insert1);
            ids.add(insert1.getId());

            Thread.sleep(2000);

            DemoDO insert2 = new DemoDO();
            insert2.setName("lenolixtest2");
            demoManager.insert(insert2);
            ids.add(insert2.getId());

            BaseQuery<DemoDO> query = new BaseQuery<DemoDO>(new DemoDO());
            query.addLike("name", "lix");
            query.addOrderBy("id", 1);
            query.setCurrentPage(0);
            query.setPageSize(20);
            PageResult<DemoDO> queryResult = demoManager.query4Page(query);
            Assert.assertNotNull(queryResult);
            Assert.assertEquals(2, queryResult.getResult().size());
            Assert.assertEquals(insert1.getId(), queryResult.getResult().get(0).getId());
            Assert.assertEquals(insert2.getId(), queryResult.getResult().get(1).getId());

            query = new BaseQuery<DemoDO>(new DemoDO());
            query.addLike("name", "lix");
            query.addOrderBy("gmtCreate", 1);
            query.setCurrentPage(0);
            query.setPageSize(20);
            queryResult = demoManager.query4Page(query);
            Assert.assertNotNull(queryResult);
            Assert.assertEquals(2, queryResult.getResult().size());
            Assert.assertEquals(insert1.getId(), queryResult.getResult().get(0).getId());
            Assert.assertEquals(insert2.getId(), queryResult.getResult().get(1).getId());


            query = new BaseQuery<DemoDO>(new DemoDO());
            query.addLike("name", "lix");
            query.setCurrentPage(0);
            query.setPageSize(20);
            query.addOrderBy("id", 2);
            queryResult = demoManager.query4Page(query);
            Assert.assertNotNull(queryResult);
            Assert.assertEquals(2, queryResult.getResult().size());
            Assert.assertEquals(insert1.getId(), queryResult.getResult().get(1).getId());
            Assert.assertEquals(insert2.getId(), queryResult.getResult().get(0).getId());


            query = new BaseQuery<DemoDO>(new DemoDO());
            query.addLike("name", "lix");
            demoManager.delete(query);

            queryResult = demoManager.query4Page(query);
            Assert.assertNotNull(queryResult);
            Assert.assertEquals(0, queryResult.getResult().size());

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }


    }
}
