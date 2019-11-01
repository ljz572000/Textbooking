package com.example.textbookapplication.entity;

import java.sql.Timestamp;

public class ShoppingCart {
    public ShoppingCart(Integer shoppingCartNo, LoginUser user, TextBook book, Integer bookNum, Double bookValues, String startTime) {
        this.shoppingCartNo = shoppingCartNo;
        this.user = user;
        this.book = book;
        this.bookNum = bookNum;
        this.bookValues = bookValues;
        this.startTime = startTime;
    }

    private Integer shoppingCartNo;
    private LoginUser user;
    private TextBook book;
    private Integer bookNum;
    private Double bookValues;
    private String startTime;

    public Integer getShoppingCartNo() {
        return shoppingCartNo;
    }

    public void setShoppingCartNo(Integer shoppingCartNo) {
        this.shoppingCartNo = shoppingCartNo;
    }

    public LoginUser getUser() {
        return user;
    }

    public void setUser(LoginUser user) {
        this.user = user;
    }

    public TextBook getBook() {
        return book;
    }

    public void setBook(TextBook book) {
        this.book = book;
    }

    public Integer getBookNum() {
        return bookNum;
    }

    public void setBookNum(Integer bookNum) {
        this.bookNum = bookNum;
    }

    public Double getBookValues() {
        return bookValues;
    }

    public void setBookValues(Double bookValues) {
        this.bookValues = bookValues;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
