package com.gao.mongodb.test;

import com.gao.mongodb.dynamic.MongodbDynamicApp;
import com.gao.mongodb.dynamic.service.ITestService;
import com.gao.mongodb.dynamicdatasource.entity.TenantVo;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= MongodbDynamicApp.class)
public class GridFsTest {

	@Autowired
	private ITestService testService;

	// 存文件
	@Test
	public void storeFileInGridFs() {
		testService.storeFileInGridFs("gridFs测试.xlsx");
	}

	@Test
	public void dynamicStoreFileInGridFs() {
		TenantVo tenantVo = new TenantVo();
		tenantVo.setTenantCode("10000");
		testService.dynamicStoreFileInGridFs(tenantVo,"gridFs测试.xlsx");
	}


}