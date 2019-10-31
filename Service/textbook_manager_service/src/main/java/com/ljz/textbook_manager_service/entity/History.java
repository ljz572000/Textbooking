package com.ljz.textbook_manager_service.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "`history`")
public class History {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer historyNo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderNo")
    private Order orderNo;
    private Timestamp startTime;
}
