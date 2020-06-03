package com.gao.mongodb.springboot.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.util.Date;

@Document(collection = "orders")
@Data
public class Order {

    @Id
    private String id;

    private String orderCode;

    private String useCode;

    private Date orderTime;

    private BigDecimal price;

    private String[] Auditors;
}
