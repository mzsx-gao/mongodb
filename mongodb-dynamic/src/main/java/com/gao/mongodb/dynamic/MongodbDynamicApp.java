package com.gao.mongodb.dynamic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;

/**
 * 名称: MongodbDynamicApp
 * 描述: mongodb动态数据源
 *
 * @author gaoshudian
 * @date 2020-06-05 16:16
 */
@SpringBootApplication(scanBasePackages = {"com.gao.mongodb.dynamic","com.gao.mongodb.dynamicdatasource"},
        exclude={MongoDataAutoConfiguration.class})
public class MongodbDynamicApp {
    public static void main(String[] args) {
        SpringApplication.run(MongodbDynamicApp.class, args);
    }
}
