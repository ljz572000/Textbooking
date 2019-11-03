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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userNo")
    private User user;
    private Timestamp startTime;
    private String content;
}
