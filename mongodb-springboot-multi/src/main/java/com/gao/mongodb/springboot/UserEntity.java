package com.gao.mongodb.springboot;

/**
 *   名称: UserEntity.java
 *   描述:
 *   类型: JAVA
 *   最近修改时间:2017/11/23 17:46
 *   @version [版本号, V1.0]
 *   @since 2017/11/23 17:46
 *   @author gaoshudian
 */
public class UserEntity {

    private String id;
    private String userName;
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}