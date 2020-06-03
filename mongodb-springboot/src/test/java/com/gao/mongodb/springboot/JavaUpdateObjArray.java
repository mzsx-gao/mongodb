package com.gao.mongodb.springboot;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.PushOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= Application.class)
public class JavaUpdateObjArray {

	private static final Logger logger = LoggerFactory.getLogger(JavaUpdateObjArray.class);
	
    private MongoDatabase db;


    private MongoCollection<Document> collection;
    
    @Autowired
    private MongoClient client;
    
    
    @Before
    public void init(){
	    	db = client.getDatabase("lison");
	    	collection=db.getCollection("users");
    }
    
    
    
    //--------------------------------------upsert demo--------------------------------------------------------------
    
    //测试upsert
    //db.users.update({"username":"cang"},{"$set":{"age":18}},{"upsert":true})
    @Test
    public void upsertTest(){
    	Bson filter = eq("username","cang");
    	Bson update = set("age", 18);
    	UpdateOptions upsert = new UpdateOptions().upsert(true);
		UpdateResult updateOne = collection.updateOne(filter, update,upsert);
    	System.out.println(updateOne.getModifiedCount());
    	System.out.println(updateOne.getUpsertedId());
    	
    }
    
    //测试unset,删除字段示例
    //db.users.updateMany({"username":"lison"},{"$unset":{"country":"","age":""}})
    @Test
    public void unsetTest(){
    	Bson filter = eq("username","lison");
    	Bson country = unset("country");
    	Bson age = unset("age");
    	Bson update = combine(country,age);
		UpdateResult updateOne = collection.updateMany(filter, update);
    	System.out.println(updateOne.getModifiedCount());
    	System.out.println(updateOne.getUpsertedId());
    	
    }
    
    //测试rename,更新字段名称示例
    //db.users.updateMany({"username":"lison"},{"$rename":{"lenght":"height", "username":"name"}})

    @Test
    public void renameTest(){
    	Bson filter = eq("username","lison");
    	Bson rename1 = rename("lenght", "height");
    	Bson rename2 = rename("username", "name");
    	Bson update = combine(rename1,rename2);
		UpdateResult updateOne = collection.updateMany(filter, update);
    	System.out.println(updateOne.getModifiedCount());
    	System.out.println(updateOne.getUpsertedId());
    	
    }
    
    
    //测试pull pullAll,删除字符串数组中元素示例
//    db.users.updateMany({ "username" : "james"}, { "$pull" : { "favorites.movies" : [ "小电影2 " , "小电影3"]}})
//    db.users.updateMany({ "username" : "james"}, { "$pullAll" : { "favorites.movies" : [ "小电影2 " , "小电影3"]}})

    @Test
    public void pullAllTest(){
    	Bson filter = eq("username","james");
    	Bson pull = pull("favorites.movies", Arrays.asList("小电影2 " , "小电影3"));
		UpdateResult updateOne = collection.updateMany(filter, pull);
    	System.out.println(updateOne.getModifiedCount());
    	System.out.println(updateOne.getUpsertedId());
    	
    	Bson pullAll = pullAll("favorites.movies", Arrays.asList("小电影2 " , "小电影3"));
		updateOne = collection.updateMany(filter, pullAll);
    	System.out.println(updateOne.getModifiedCount());
    	System.out.println(updateOne.getUpsertedId());
    }

    
    
    
    //--------------------------------------insert demo--------------------------------------------------------------
    
    //给james老师增加一条评论（$push）
    //db.users.updateOne({"username":"james"},
//                         {"$push":{"comments":{"author":"lison23",
//                                     "content":"ydddyyytttt",
//                                     "commentTime":ISODate("2019-01-06T00:00:00")}}})

    @Test
    public void addOneComment(){
    	Document comment = new Document().append("author", "lison23")
    			                        .append("content", "ydddyyytttt")
    			                        .append("commentTime", getDate("2019-01-06"));
    	Bson filter = eq("username","james");
		Bson update = push("comments",comment);
		UpdateResult updateOne = collection.updateOne(filter, update);
    	System.out.println(updateOne.getModifiedCount());
    	
    }
    
    




	//    给james老师批量新增两条评论（$push,$each）
//    db.users.updateOne({"username":"james"},     
//    	       {"$push":{"comments":
//    	                  {"$each":[{"author":"lison22","content":"yyyytttt","commentTime":ISODate("2019-02-06T00:00:00")},
//    	                            {"author":"lison23","content":"ydddyyytttt","commentTime":ISODate("2019-03-06T00:00:00")}]}}})

    @Test
    public void addManyComment(){
    	Document comment1 = new Document().append("author", "lison33")
    			                        .append("content", "lison33lison33")
    			                        .append("commentTime", getDate("2019-02-06"));
    	Document comment2 = new Document().append("author", "lison44")
                						.append("content", "lison44lison44")
                						.append("commentTime", getDate("2019-03-06"));
    	
    	Bson filter = eq("username","james");
		Bson pushEach = pushEach("comments",Arrays.asList(comment1,comment2));
		UpdateResult updateOne = collection.updateOne(filter, pushEach);
    	System.out.println(updateOne.getModifiedCount());
    	
    }
    
    
//    给james老师批量新增两条评论并对数组进行排序（$push,$eachm,$sort）
//    db.users.updateOne({"username":"james"}, 
//    	      {"$push": {"comments":
//    	                {"$each":[ {"author":"lison22","content":"yyyytttt","commentTime":ISODate("2019-04-06T00:00:00")},
//    	                           {"author":"lison23","content":"ydddyyytttt","commentTime":ISODate("2019-05-06T00:00:00")} ], 
//    	                  $sort: {"commentTime":-1} } } })

    @Test
    public void addManySortComment(){
    	Document comment1 = new Document().append("author", "lison00")
    			                        .append("content", "lison00lison00")
    			                        .append("commentTime", getDate("2019-04-06"));
    	Document comment2 = new Document().append("author", "lison01")
                						.append("content", "lison01lison01")
    			                        .append("commentTime", getDate("2019-05-06"));
    	
    	Bson filter = eq("username","james");
    	
    	Document sortDoc = new Document().append("commentTime", -1);
    	PushOptions pushOption = new PushOptions().sortDocument(sortDoc);
    	
		Bson pushEach = pushEach("comments",Arrays.asList(comment1,comment2),pushOption);
		
		UpdateResult updateOne = collection.updateOne(filter, pushEach);
    	System.out.println(updateOne.getModifiedCount());
    	
    }
 
    //--------------------------------------delete demo--------------------------------------------------------------
 
//    删除lison1对james的所有评论 （批量删除）
//    db.users.update({"username":“james"},
//                               {"$pull":{"comments":{"author":"lison33"}}})

    @Test
    public void deleteByAuthorComment(){
    	Document comment = new Document().append("author", "lison33");
		Bson filter = eq("username","james");
		Bson update = pull("comments",comment);
		UpdateResult updateOne = collection.updateOne(filter, update);
		System.out.println(updateOne.getModifiedCount());
    }
    
    
//    删除lison5对lison评语为“lison是苍老师的小迷弟”的评论（精确删除）
//    db.users.update({"username":"lison"},
//            {"$pull":{"comments":{"author":"lison5",
//                                  "content":"lison是苍老师的小迷弟"}}})
    @Test
    public void deleteByAuthorContentComment(){
    	Document comment = new Document().append("author", "lison5")
    			                         .append("content", "lison是苍老师的小迷弟");
		Bson filter = eq("username","lison");
		Bson update = pull("comments",comment);
		UpdateResult updateOne = collection.updateOne(filter, update);
		System.out.println(updateOne.getModifiedCount());
    }
    
    //--------------------------------------update demo--------------------------------------------------------------
//    db.users.updateMany({"username":"james","comments.author":"lison01"},
//            {"$set":{"comments.$.content":"xxoo",
//                        "comments.$.author":"lison10" }})
//    	含义：精确修改某人某一条精确的评论，如果有多个符合条件的数据，则修改最后一条数据。无法批量修改数组元素
  @Test
  public void updateOneComment(){
	  	Bson filter = and(eq("username","james"),eq("comments.author","lison01"));
	  	Bson updateContent = set("comments.$.content","xxoo");
	  	Bson updateAuthor = set("comments.$.author","lison10");
	  	Bson update = combine(updateContent,updateAuthor);
	  	UpdateResult updateOne = collection.updateOne(filter, update);
	  	System.out.println(updateOne.getModifiedCount());
	  
  }
  
  
//--------------------------------------findandModify demo--------------------------------------------------------------
  //使用findandModify方法在修改数据同时返回更新前的数据或更新后的数据
//db.fam.findAndModify({query:{name:'morris1'}, 
//    update:{$inc:{age:1}}, 
//    'new':true});

  @Test
  public void findAndModifyTest(){
	  Bson filter = eq("name","morris1");
	  Bson update = inc("age",1);
//	  //实例化findAndModify的配置选项
	  FindOneAndUpdateOptions fauo = new FindOneAndUpdateOptions();
//	  //配置"new":true
	  fauo.returnDocument(ReturnDocument.AFTER);//
	  MongoCollection<Document> numCollection = db.getCollection("fam");
	  Document ret = numCollection.findOneAndUpdate(filter, update,fauo);
	  System.out.println(ret.toJson());
  }
  
  
  private Date getDate(String string) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date parse=null;
		try {
			parse = sdf.parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parse;
	}
  
}