package com.gao.mongodb.springboot.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="comments")
public class Comments {
	
	private List<Comment> lists;

	public List<Comment> getLists() {
		return lists;
	}

	public void setLists(List<Comment> lists) {
		this.lists = lists;
	}

	@Override
	public String toString() {
		return "Comments{" +
				"lists=" + lists +
				'}';
	}
}