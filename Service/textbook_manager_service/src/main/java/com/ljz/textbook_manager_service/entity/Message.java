package com.ljz.textbook_manager_service.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table
public class Message {
    @Id
    private Integer messNo;
    private Integer userNo;
    private Timestamp startTime;
    private String content;
}
