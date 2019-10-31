package com.example.textbookapplication.entity;

import java.sql.Timestamp;

public class Message {
    public Message(Integer messNo, Integer userNo, String startTime, String content) {
        this.messNo = messNo;
        this.userNo = userNo;
        this.startTime = startTime;
        this.content = content;
    }

    public Integer getMessNo() {
        return messNo;
    }

    public void setMessNo(Integer messNo) {
        this.messNo = messNo;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private Integer messNo;
    private Integer userNo;
    private String startTime;
    private String content;
}
