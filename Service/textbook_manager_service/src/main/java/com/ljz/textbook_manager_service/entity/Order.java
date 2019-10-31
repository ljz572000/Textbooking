package com.ljz.textbook_manager_service.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer orderNo;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "bookNo")
    private TextBook TextBook;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "userId")
    private User user;
    private Integer bookNum;
    private Double bookValues;
    private Timestamp startTime;
}
