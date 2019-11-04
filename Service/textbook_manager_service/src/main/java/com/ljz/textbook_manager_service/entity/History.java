package com.ljz.textbook_manager_service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
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
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH🇲🇲ss", timezone = "GMT+8")
    private Date startTime;
//    @Column(name = "CREATIONTIME", nullable=false,updatable = false)
}
