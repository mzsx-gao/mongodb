package com.gao.mongodb.springboot.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection="users")
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

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", country="
				+ country + ", address=" + address + ", favorites=" + favorites
				+ ", age=" + age + ", salary=" + salary + ", lenght=" + lenght
				+ ", comments=" + comments + "]";
	}


}