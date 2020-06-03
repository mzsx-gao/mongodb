package com.gao.mongodb.springboot;


import com.gao.mongodb.springboot.dao.UserDaoImpl;
import com.gao.mongodb.springboot.entity.RepayPlanEntity;
import com.gao.mongodb.springboot.entity.UserEntity;
import com.gao.mongodb.springboot.repository.IUserRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= Application.class)
public class testMongodb {

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 测试通过mongoTemplate操作mongodb
     */
    @Test
    public void testSaveUser() throws Exception {
        UserEntity user=new UserEntity();
        user.setId("2l");
        user.setUserName("小明");
        user.setPassword("fffooo123");
        userDao.saveUser(user);
    }

    @Test
    public void testUpdateUser() throws Exception {
        UserEntity user=new UserEntity();
        user.setId("2l");
        user.setUserName("小明2");
        user.setPassword("fffooo123");
        userDao.updateUser(user);
    }

    /**
     * 测试通过MongoRepository操作mongodb
     */
    @Test
    public void testSaveUser2(){
        UserEntity user=new UserEntity();
        user.setId("2l");
        user.setUserName("小明3");
        user.setPassword("fffooo123");
        userRepository.save(user);
    }

    // 多个条件查询 Query: { "acctSeq" : "8220" , "loanperiod" : "1"}, Fields: null, Sort: { }
    @Test
    public void testQuery1(){
        Criteria and1 = Criteria.where("acctSeq").is("8220");
        Criteria and2 = Criteria.where("loanperiod").is("1");
        Query query = new Query(new Criteria().andOperator(and1,and2));
        List<RepayPlanEntity> repayPlanEntities = mongoTemplate.find(query,RepayPlanEntity.class);
        System.out.println(repayPlanEntities);
    }

    //Query: { "$or" : [ { "acctSeq" : "8220"} , { "acctSeq" : "8221"}]}, Fields: null, Sort: { }
    @Test
    public void testQuery2(){
        Criteria or1 = Criteria.where("acctSeq").is("8220");
        Criteria or2 = Criteria.where("acctSeq").is("8221");
        Query query = new Query(new Criteria().orOperator(or1,or2));
        List<RepayPlanEntity> repayPlanEntities = mongoTemplate.find(query,RepayPlanEntity.class);
        System.out.println(repayPlanEntities);
    }

}