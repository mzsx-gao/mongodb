package com.gao.mongodb;


import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.*;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MongodbTest {

    private static MongoCollection<Document> collection;

    //获取
    @Before
    public void before(){
        MongoCredential credential = MongoCredential.createCredential("root","admin","root".toCharArray());
        MongoClientSettings settings = MongoClientSettings.builder()
                .credential(credential)
                .applyToSslSettings(builder -> builder.enabled(true))
                .applyToClusterSettings(builder -> builder.hosts(Arrays.asList(new ServerAddress("172.19.7.200", 27017))))
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase db = mongoClient.getDatabase("test");
        collection = db.getCollection("test");
        System.out.println(collection);
    }

    //插入一个文档
    @Test
    public void testInsert(){
        Document document = new Document();
        document.append("name", "wang").append("gender", "female");
        collection.insertOne(document);
    }

    //查询所有的文档
    @Test
    public  void findAll() {
        List<Document> results = new ArrayList<Document>();
        FindIterable<Document> iterables = collection.find();
        MongoCursor<Document> cursor = iterables.iterator();
        while (cursor.hasNext()) {
            results.add(cursor.next());
        }
        System.out.println(results);
    }

    //根据条件查询
    @Test
    public void findBy() {
        Document filter = new Document();
        filter.append("name", "wang");
        List<Document> results = new ArrayList<Document>();
        FindIterable<Document> iterables = collection.find(filter);
        MongoCursor<Document> cursor = iterables.iterator();
        while (cursor.hasNext()) {
            results.add(cursor.next());
        }
        for(Document doc : results){
            System.out.println(doc.toJson());
        }
    }

    //更新查询到的第一个
    @Test
    public void testUpdateOne(){
        Document filter = new Document();
        filter.append("gender", "female");
        //注意update文档里要包含"$set"字段
        Document update = new Document();
        update.append("$set", new Document("gender", "male"));
        UpdateResult result = collection.updateOne(filter, update);
        System.out.println("matched count = " + result.getMatchedCount());
    }

    //更新查询到的所有的文档
    @Test
    public void testUpdateMany(){
        Document filter = new Document();
        filter.append("gender", "male");
        //注意update文档里要包含"$set"字段
        Document update = new Document();
        update.append("$set", new Document("gender", "female"));
        UpdateResult result = collection.updateMany(filter, update);
        System.out.println("matched count = " + result.getMatchedCount());
    }

    //更新一个文档, 结果是replacement是新文档，老文档完全被替换
    @Test
    public void testReplace(){
        Document filter = new Document();
        filter.append("name", "wang");
        //注意：更新文档时，不需要使用"$set"
        Document replacement = new Document();
        replacement.append("name","wang").append("gender", "1234");
        collection.replaceOne(filter, replacement);
    }

    //删除一个文档
    @Test
    public void testDeleteOne(){
        Document filter = new Document();
        filter.append("name", "wang");
        collection.deleteOne(filter);
    }

    //根据条件删除多个文档
    @Test
    public void testDeleteMany(){
        Document filter = new Document();
        filter.append("name", "wang");
        collection.deleteMany(filter);
    }
}