package com.gao.mongodb.springboot.config;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.Data;
import org.springframework.data.mongodb.core.MongoDbFactorySupport;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;

@Data
public abstract class AbstractMongoConfig {

    // 变量名跟配置的参数对应
    private String uri, database, username, password,authenticationDatabase;

    public MongoClient mongoClient() {

        MongoCredential createCredential = MongoCredential.createCredential
                (username, authenticationDatabase, password.toCharArray());

//        List<ServerAddress> serverAddresses = Arrays.asList(
//                new ServerAddress(mongoProperties.getHost(), mongoProperties.getPort()));

        MongoClientSettings settings = MongoClientSettings.builder()
                .credential(createCredential)
//                .applyToClusterSettings(builder -> builder.hosts(serverAddresses))
                .applyConnectionString(new ConnectionString(uri))
                .readPreference(ReadPreference.secondary())
                .writeConcern(WriteConcern.W1.withJournal(true))
                .build();
        //mongo-java-driver3.7版本以后API变成如下这样，不再用com.mongodb.MongoClient这个类了
        com.mongodb.client.MongoClient mongoClient = MongoClients.create(settings);
        return mongoClient;
    }

    MongoDbFactorySupport<?> mongoDbFactory() {
        return new SimpleMongoClientDbFactory(mongoClient(), database);
    }

    /*
     * Factory method to create the MongoTemplate
     */
    abstract public MongoTemplate getMongoTemplate() throws Exception;

}