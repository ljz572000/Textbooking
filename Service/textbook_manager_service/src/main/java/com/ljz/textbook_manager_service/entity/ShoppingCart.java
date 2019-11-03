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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userNo")
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bookNo")
    private TextBook book;
    private Integer bookNum;
    private Double bookValues;
    private Timestamp startTime;
}
