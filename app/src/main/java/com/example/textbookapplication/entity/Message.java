package com.example.textbookapplication.entity;

import java.sql.Timestamp;

public class Message {

    public LoginUser getUser() {
        return user;
    }

    public void setUser(LoginUser user) {
        this.user = user;
    }

    public Integer getMessNo() {
        return messNo;
    }

    public void setMessNo(Integer messNo) {
        this.messNo = messNo;
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

    public Message(Integer messNo, LoginUser user, String startTime, String content) {
        this.messNo = messNo;
        this.user = user;
        this.startTime = startTime;
        this.content = content;
    }

    private Integer messNo;
    private LoginUser user;
    private String startTime;
    private String content;
}
