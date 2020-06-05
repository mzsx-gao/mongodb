package com.gao.mongodb.springboot.config;

import com.gao.mongodb.springboot.convert.BigDecimalToDecimal128Converter;
import com.gao.mongodb.springboot.convert.Decimal128ToBigDecimalConverter;
import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoDbFactorySupport;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 注意下面的这些配置，springboot默认已经配置了，这里配置可以覆盖springboot的默认配置
 */
@Configuration
public class MongoConfig {


    @Bean
    public MongoClient mongoClient(MongoProperties mongoProperties) {

		MongoCredential createCredential = MongoCredential.createCredential
                (mongoProperties.getUsername(),
                        mongoProperties.getAuthenticationDatabase(),
                        mongoProperties.getPassword());

//        List<ServerAddress> serverAddresses = Arrays.asList(
//                new ServerAddress(mongoProperties.getHost(), mongoProperties.getPort()));

        MongoClientSettings settings = MongoClientSettings.builder()
                .credential(createCredential)
//                .applyToClusterSettings(builder -> builder.hosts(serverAddresses))
                .applyConnectionString(new ConnectionString(mongoProperties.getUri()))
                .readPreference(ReadPreference.secondary())
                .writeConcern(WriteConcern.W1.withJournal(true))
                .build();
        //mongo-java-driver3.7版本以后API变成如下这样，不再用com.mongodb.MongoClient这个类了
        MongoClient mongoClient = MongoClients.create(settings);
        return mongoClient;
    }

    @Bean
    MongoDbFactorySupport<?> mongoDbFactory(MongoClient mongoClient,MongoProperties mongoProperties) {
        return new SimpleMongoClientDbFactory(mongoClient, mongoProperties.getMongoClientDatabase());
    }

    @Bean
    public MongoMappingContext mongoMappingContext() {
        MongoMappingContext context = new MongoMappingContext();
        return context;
    }

    //添加自定义类型转换器
    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoDbFactory mongoDbFactory
            ,MongoMappingContext mongoMappingContext) {
        DefaultDbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
        List<Object> list = new ArrayList<>();
        list.add(new BigDecimalToDecimal128Converter());//自定义的类型转换器
        list.add(new Decimal128ToBigDecimalConverter());//自定义的类型转换器
        converter.setCustomConversions(new MongoCustomConversions(list));
        return converter;
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory,
                                       MappingMongoConverter mappingMongoConverter) {
        return new MongoTemplate(mongoDbFactory, mappingMongoConverter);
    }
}
