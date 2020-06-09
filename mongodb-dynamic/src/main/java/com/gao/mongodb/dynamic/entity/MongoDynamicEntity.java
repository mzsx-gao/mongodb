package com.gao.mongodb.dynamic.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection="mongoDynamic")
@Data
public class MongoDynamicEntity {
	private String name;
}