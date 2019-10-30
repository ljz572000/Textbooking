package com.ljz.textbook_manager_service.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class TextBook {
    @Id
    private Integer bookNo;
    private String bookName;
    private String bookPic;
    private String author;
    private Double bookPrice;

    public Integer getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(Integer totalnum) {
        this.totalnum = totalnum;
    }

    private Integer totalnum;
}
