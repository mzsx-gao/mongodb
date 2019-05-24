package com.gao.mongodb.springboot;


import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.QueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 *   名称: testSpringDataMongodb.java
 *   描述:
 *   类型: JAVA
 *   最近修改时间:2017/11/23 17:53
 *   @version [版本号, V1.0]
 *   @since 2017/11/23 17:53
 *   @author gaoshudian
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= Application.class)
public class testSpringDataMongodb {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 测试通过mongoTemplate操作mongodb
     */
    @Test
    public void testSaveUser(){
        UserEntity user=new UserEntity();
        user.setId("2l");
        user.setUserName("小明");
        user.setPassword("fffooo123");
        mongoTemplate.save(user);
    }

    // BasicDBObject查询
    // 多个条件查询 Query: { "acctSeq" : "8220" , "loanperiod" : "1"}, Fields: null, Sort: { }
    @Test
    public void testQuery1(){
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put("acctSeq","8220");
        dbObject.put("loanperiod","1");
        Query query = new BasicQuery(dbObject);
        List<RepayPlanEntity> repayPlanEntities = mongoTemplate.find(query,RepayPlanEntity.class);
        System.out.println(repayPlanEntities);
    }

    //BasicDBList查询
    //Query: { "$or" : [ { "acctSeq" : "8220"} , { "acctSeq" : "8221"}]}, Fields: null, Sort: { }
    @Test
    public void testQuery2(){

        BasicDBList basicDBList = new BasicDBList();
        basicDBList.add(new BasicDBObject("acctSeq","8220"));
        basicDBList.add(new BasicDBObject("acctSeq","8221"));
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put("$or",basicDBList);
        Query query = new BasicQuery(dbObject);
        List<RepayPlanEntity> repayPlanEntities = mongoTemplate.find(query,RepayPlanEntity.class);
        System.out.println(repayPlanEntities);
    }

    //QueryBuilder查询
    //Query: { "$or" : [ { "acctSeq" : "8220"} , { "acctSeq" : "8221"}]}, Fields: { "subAcctNo" : 1 , "acctSeq" : 1}, Sort: { }
    @Test
    public void testQuery3(){

        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.or(new BasicDBObject("acctSeq", "8220"), new BasicDBObject("acctSeq","8221"));
        BasicDBObject fieldsObject=new BasicDBObject();
        fieldsObject.put("subAcctNo", 1);
        fieldsObject.put("acctSeq", 1);
        Query query=new BasicQuery(queryBuilder.get(),fieldsObject);
        List<RepayPlanEntity> repayPlanEntities = mongoTemplate.find(query,RepayPlanEntity.class);
        System.out.println(repayPlanEntities);
    }


}