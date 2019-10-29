package com.ljz.textbook_manager_service.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table
public class History {
    @Id
    private Integer historyNo;
    private Integer orderNo;
    private Timestamp startTime;
}
