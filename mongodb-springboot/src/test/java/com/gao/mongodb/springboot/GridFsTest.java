package com.gao.mongodb.springboot;

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
@SpringBootTest(classes= Application.class)
public class GridFsTest {

	@Autowired
	GridFsTemplate gridFsTemplate;

	// 存文件
	@Test
	public void storeFileInGridFs() {
		Resource file = new ClassPathResource("gridFs测试.xlsx");
		try {
			gridFsTemplate.store(file.getInputStream(), file.getFilename(), "xlsx");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// 下载文件
	@Test
	public void findFilesInGridFs(){
		GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(GridFsCriteria.whereFilename().is("gridFs测试.xlsx")));
		GridFsResource gridFsResource = gridFsTemplate.getResource(gridFSFile);
		OutputStream os;
		try {
			byte[] bs = new byte[1024];
			int len;
			InputStream in = gridFsResource.getInputStream();
			File file = new File("/Users/gaoshudian/Desktop/" + gridFSFile.getFilename());
			os = new FileOutputStream(file);
			while ((len = in.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
		} catch (IOException e) {
		}
	}

	//删除文件
	@Test
	public void delete() {
		gridFsTemplate.delete(new Query().addCriteria(Criteria.where("filename").is("gridFs测试.xlsx")));
	}

	// 所有文件
	@Test
	public void readFilesFromGridFs() {
		GridFsResource[] txtFiles = gridFsTemplate.getResources("*");
		for (GridFsResource txtFile : txtFiles) {
			System.out.println(txtFile.getFilename());
		}
	}

}