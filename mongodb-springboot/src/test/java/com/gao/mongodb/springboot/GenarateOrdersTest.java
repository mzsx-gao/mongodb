package com.gao.mongodb.springboot;

import com.gao.mongodb.springboot.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= Application.class)
public class GenarateOrdersTest {

	private static final Logger logger = LoggerFactory.getLogger(GenarateOrdersTest.class);
	
	@Autowired
	private MongoTemplate tempelate;
    
  	//随机生成orderTest数据
	@Test
	public void batchInsertOrder() {
		String[] userCodes = new String[] { "james", "AV", "allen", "six",
				"peter", "mark", "king", "zero", "lance", "deer", "lison" };
		String[] auditors = new String[] { "auditor1","auditor2","auditor3","auditor4","auditor5"};
		List<Order> list = new ArrayList<>();
		Random rand = new Random();
		for (int i = 0; i < 1000; i++) {
			Order order = new Order();
			int num = rand.nextInt(11);
			order.setUseCode(userCodes[num]);
			order.setOrderCode(UUID.randomUUID().toString());
			order.setOrderTime(RondomDateTest.randomDate("2015-01-01","2017-10-31"));
			order.setPrice(RondomDateTest.randomBigDecimal(10000, 1));
			int length = rand.nextInt(5)+1;
			String[] temp = new String[length];
			for (int j = 0; j < temp.length; j++) {
				temp[j] = getFromArrays(temp,auditors,rand); 
			}
			order.setAuditors(temp);
			list.add(order);
		}
		tempelate.insertAll(list);
	}

	private String getFromArrays(String[] temp, String[] auditors, Random rand) {
		String ret = null;
		boolean test = true;
		while (test) {
			ret = auditors[rand.nextInt(5)];
			int i =0;
			for (String _temp : temp) {
				i++;
				if(ret.equals(_temp)){
					break;
				}
			}
			if(i==temp.length){
				test=false;
			}
			
		}
		return ret;
	}
}