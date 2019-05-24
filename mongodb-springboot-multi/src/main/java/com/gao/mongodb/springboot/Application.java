package com.gao.mongodb.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 *   名称: Application.java
 *   描述:
 *   类型: JAVA
 *   最近修改时间:2017/11/23 17:46
 *   @version [版本号, V1.0]
 *   @since 2017/11/23 17:46
 *   @author gaoshudian
 */

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}