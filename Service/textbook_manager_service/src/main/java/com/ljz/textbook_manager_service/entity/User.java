package com.ljz.textbook_manager_service.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "`user`")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer userNo;
    private String userId;
    private Boolean isAdmin;
    private String userPassword;
    private String userIconPath;
    private String userName;

}
