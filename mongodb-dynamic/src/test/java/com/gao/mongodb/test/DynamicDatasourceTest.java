package com.gao.mongodb.test;

import com.gao.mongodb.dynamic.MongodbDynamicApp;
import com.gao.mongodb.dynamic.entity.MongoDynamicEntity;
import com.gao.mongodb.dynamic.service.ITestService;
import com.gao.mongodb.dynamicdatasource.entity.TenantVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 名称: DynamicDatasourceTest
 * 描述: 动态数据源测试
 *
 * @author gaoshudian
 * @date 2020-06-05 16:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= MongodbDynamicApp.class)
public class DynamicDatasourceTest {

    @Autowired
    private ITestService testService;

    @Test
    public void dynamicDatasource(){
        TenantVo tenantVo = new TenantVo();
        tenantVo.setTenantCode("10000");
        MongoDynamicEntity mongoDynamicEntity = new MongoDynamicEntity();
        mongoDynamicEntity.setName("商户10000保存");
        testService.dynamicDatasource(tenantVo,mongoDynamicEntity);

        tenantVo.setTenantCode("10001");
        mongoDynamicEntity.setName("商户10001保存");
        testService.dynamicDatasource(tenantVo,mongoDynamicEntity);

        //再次调用是为了测试 if (tenantMongoConfig.getMongoTemplateMap().get(mongoSourcesKey) == null){} 这个判断
        testService.dynamicDatasource(tenantVo,mongoDynamicEntity);

    }

    @Test
    public void defaultDatasource(){
        MongoDynamicEntity mongoDynamicEntity = new MongoDynamicEntity();
        mongoDynamicEntity.setName("默认数据源保存");
        testService.defaultDatasource(mongoDynamicEntity);
    }
}
