package com.gao.mongodb.springboot.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("userentity")
@Data
public class UserEntity {

    private String id;
    private String userName;
    private String password;

}