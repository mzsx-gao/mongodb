package com.gao.mongodb.springboot.dao;

import com.gao.mongodb.springboot.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<UserEntity,String>{

}
