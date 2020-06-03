package com.gao.mongodb.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import java.math.BigDecimal;
import java.util.List;

@Data
public class User {

    private ObjectId id;

    private String username;

    private String country;

    private Address address;

    private Favorites favorites;

    private int age;

    private BigDecimal salary;

    private float lenght;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    private List<Comment> comments;
}
