package com.ljz.textbook_manager_service.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer messNo;
    private Integer userNo;
    private Timestamp startTime;
    private String content;
}
