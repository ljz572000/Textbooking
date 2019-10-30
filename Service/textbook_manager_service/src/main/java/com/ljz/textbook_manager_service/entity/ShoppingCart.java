package com.ljz.textbook_manager_service.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer shoppingCartNo;
    private Integer userNo;
    private Integer bookNo;
    private Integer bookNum;
    private Double bookValues;
    private Timestamp startTime;
}
