package com.example.textbookapplication.entity;

public class Order {
    private Integer orderNo;
    private TextBook TextBook;
    private LoginUser user;
    private Integer bookNum;
    private Double bookValues;
    private String startTime;

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public com.example.textbookapplication.entity.TextBook getTextBook() {
        return TextBook;
    }

    public void setTextBook(com.example.textbookapplication.entity.TextBook textBook) {
        TextBook = textBook;
    }

    public LoginUser getUser() {
        return user;
    }

    public void setUser(LoginUser user) {
        this.user = user;
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

    public Order(Integer orderNo, com.example.textbookapplication.entity.TextBook textBook, LoginUser user, Integer bookNum, Double bookValues, String startTime) {
        this.orderNo = orderNo;
        TextBook = textBook;
        this.user = user;
        this.bookNum = bookNum;
        this.bookValues = bookValues;
        this.startTime = startTime;
    }
}
