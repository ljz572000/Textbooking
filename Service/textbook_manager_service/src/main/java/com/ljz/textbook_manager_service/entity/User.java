package com.ljz.textbook_manager_service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "`user`")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer userNo;
    @Column(name="user_id", unique=true, nullable=false)
    private String userId;
    private Boolean isAdmin;
    private String userPassword;
    private String userIconPath;
    private String userName;
    private Double money;
    private String address;
    private String major;
    private String mail;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date birth;
    private Boolean isFemale;
    public Integer getUserNo() {
        return userNo;
    }
    public String getMail() {
        return mail;
    }
}
