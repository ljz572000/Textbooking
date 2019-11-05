package com.ljz.textbook_manager_service.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "`reset_code`")
public class ResetCode {
    @Id
    @Column(name = "`user_no`")
    private Integer userNo;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
