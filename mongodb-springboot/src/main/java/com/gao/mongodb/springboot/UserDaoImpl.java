package com.gao.mongodb.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

/**
 *   名称: UserDaoImpl.java
 *   描述:
 *   类型: JAVA
 *   最近修改时间:2017/11/23 17:48
 *   @version [版本号, V1.0]
 *   @since 2017/11/23 17:48
 *   @author gaoshudian
 */

@Service
public class UserDaoImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    //创建对象
    public void saveUser(UserEntity user) {
        mongoTemplate.save(user);
    }

    //根据用户名查询对象
    public UserEntity findUserByUserName(String userName) {
        Query query=new Query(Criteria.where("userName").is(userName));
        UserEntity user =  mongoTemplate.findOne(query , UserEntity.class);
        return user;
    }

    //更新对象
    public void updateUser(UserEntity user) {
        Query query=new Query(Criteria.where("id").is(user.getId()));
        Update update= new Update().set("userName", user.getUserName()).set("password", user.getPassword());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query,update,UserEntity.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
    }

    //删除对象
    public void deleteUserById(Long id) {
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,UserEntity.class);
    }
}