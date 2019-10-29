package com.ljz.textbook_manager_service.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table
public class ShoppingCart {
    @Id
    private Integer shoppingCartNo;
    private Integer userNo;
    private Integer bookNo;
    private Integer bookNum;
    private Double bookValues;
    private Timestamp startTime;
}
