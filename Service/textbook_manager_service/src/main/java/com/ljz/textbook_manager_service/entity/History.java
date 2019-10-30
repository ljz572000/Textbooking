package com.ljz.textbook_manager_service.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table
public class History {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer historyNo;
    private Integer orderNo;
    private Timestamp startTime;
}
