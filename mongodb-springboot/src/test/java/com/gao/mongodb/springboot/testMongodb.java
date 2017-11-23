package com.gao.mongodb.springboot;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;

/**
 *   名称: testMongodb.java
 *   描述:
 *   类型: JAVA
 *   最近修改时间:2017/11/23 17:53
 *   @version [版本号, V1.0]
 *   @since 2017/11/23 17:53
 *   @author gaoshudian
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= Application.class)
public class testMongodb {

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private IUserRepository userRepository;

    /**
     * 测试通过mongoTemplate操作mongodb
     */
    @Test
    public void testSaveUser() throws Exception {
        UserEntity user=new UserEntity();
        user.setId("2l");
        user.setUserName("小明");
        user.setPassword("fffooo123");
        userDao.saveUser(user);
    }

    @Test
    public void testUpdateUser() throws Exception {
        UserEntity user=new UserEntity();
        user.setId("2l");
        user.setUserName("小明2");
        user.setPassword("fffooo123");
        userDao.updateUser(user);
    }

    /**
     * 测试通过MongoRepository操作mongodb
     */
    @Test
    public void testSaveUser2(){
        UserEntity user=new UserEntity();
        user.setId("2l");
        user.setUserName("小明3");
        user.setPassword("fffooo123");
        userRepository.save(user);
    }

}