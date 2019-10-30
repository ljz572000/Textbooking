package com.ljz.textbook_manager_service.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer orderNo;
    private Integer bookNo;
    private Integer userNo;
    private Integer bookNum;
    private Double bookValues;
    private Timestamp startTime;
}
