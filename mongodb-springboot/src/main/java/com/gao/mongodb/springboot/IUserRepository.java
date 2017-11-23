package com.gao.mongodb.springboot;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *   名称: IUserRepository.java <br>
 *   描述: <br>
 *   类型: JAVA <br>
 *   最近修改时间:2017/11/23 18:26.<br>
 *   @version [版本号, V1.0]
 *   @since 2017/11/23 18:26.
 *   @author gaoshudian
 */
public interface IUserRepository extends MongoRepository<UserEntity,String>{

}
