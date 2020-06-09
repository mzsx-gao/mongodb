package com.gao.mongodb.dynamic.service.impl;

import com.gao.mongodb.dynamic.entity.MongoDynamicEntity;
import com.gao.mongodb.dynamic.service.ITestService;
import com.gao.mongodb.dynamicdatasource.entity.TenantVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 名称: TestServiceImpl
 * 描述: 动态数据源测试
 *
 * @author gaoshudian
 * @date 2020-06-05 16:26
 */
@Service
public class TestServiceImpl implements ITestService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    GridFsTemplate gridFsTemplate;

    @Override
    public void dynamicDatasource(TenantVo tenantVo, MongoDynamicEntity mongoDynamicEntity) {
        mongoTemplate.save(mongoDynamicEntity);
    }

    @Override
    public void defaultDatasource(MongoDynamicEntity mongoDynamicEntity) {
        mongoTemplate.save(mongoDynamicEntity);
    }

    @Override
    public void dynamicStoreFileInGridFs(TenantVo tenantVo, String filePath) {
        Resource file = new ClassPathResource(filePath);
        try {
            gridFsTemplate.store(file.getInputStream(), file.getFilename(), "xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void storeFileInGridFs(String filePath) {
        Resource file = new ClassPathResource(filePath);
        try {
            gridFsTemplate.store(file.getInputStream(), file.getFilename(), "xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
