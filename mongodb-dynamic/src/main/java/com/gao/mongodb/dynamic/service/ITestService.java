package com.gao.mongodb.dynamic.service;

import com.gao.mongodb.dynamic.entity.MongoDynamicEntity;
import com.gao.mongodb.dynamicdatasource.entity.TenantVo;

public interface ITestService {

    void dynamicDatasource(TenantVo tenantVo, MongoDynamicEntity mongoDynamicEntity);

    void defaultDatasource(MongoDynamicEntity mongoDynamicEntity);

    void dynamicStoreFileInGridFs(TenantVo tenantVo,String filePath);
    void storeFileInGridFs(String filePath);
}
