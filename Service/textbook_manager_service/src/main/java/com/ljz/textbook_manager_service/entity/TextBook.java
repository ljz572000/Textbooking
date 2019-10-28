package com.ljz.textbook_manager_service.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private Integer totalnum;
}
