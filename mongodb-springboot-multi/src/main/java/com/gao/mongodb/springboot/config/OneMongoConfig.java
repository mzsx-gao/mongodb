package com.gao.mongodb.springboot.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

//使得@ConfigurationProperties生效有两种方式:
// 1.某个地方加上@EnableConfigurationProperties(Person.class)，这种方式内部也是将Person.class注册为spring的一个bean
// 2.此类加上@Component
@Component
@ConfigurationProperties(prefix = "spring.data.mongodb.one")
public class OneMongoConfig extends AbstractMongoConfig{

    @Primary
    @Bean(name = "mongoTemplate")
    @Override
    public MongoTemplate getMongoTemplate() {
        return new MongoTemplate(mongoDbFactory());
    }
}
