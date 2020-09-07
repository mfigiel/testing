package com.gateway.repository.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private BigDecimal unitPrice;
    private String description;
    private String category;
    private Long unitsInStock;
    private Long unitsInOrder;
    private boolean discontinued;
    private String condition;
    private String withdrawn;

}
