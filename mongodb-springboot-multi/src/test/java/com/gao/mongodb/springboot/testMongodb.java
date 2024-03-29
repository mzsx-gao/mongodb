package com.gao.mongodb.springboot;


import com.gao.mongodb.springboot.dao.IUserRepository;
import com.gao.mongodb.springboot.dao.UserDaoImpl;
import com.gao.mongodb.springboot.entity.UserEntity;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;


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
    public void testSaveUser() {
        UserEntity user=new UserEntity();
        user.setId("2l");
        user.setUserName("小明");
        user.setPassword("fffooo123");
        userDao.saveUser(user);
    }

    @Test
    public void testUpdateUser() {
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