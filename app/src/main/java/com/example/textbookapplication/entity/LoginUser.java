package com.example.textbookapplication.entity;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginUser {
    public static LoginUser loginUser;

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserIconPath() {
        return userIconPath;
    }

    public void setUserIconPath(String userIconPath) {
        this.userIconPath = userIconPath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LoginUser(String usermessage, Context context) {

        try {
            JSONObject jsonObject = new JSONObject(usermessage);
            this.userId = jsonObject.getInt("userId");
            this.isAdmin = jsonObject.getBoolean("isAdmin");
            this.userPassword = jsonObject.getString("userPassword");
            this.userIconPath = jsonObject.getString("userIconPath");
            this.userName = jsonObject.getString("userName");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        loginUser = new LoginUser(this.userId,this.isAdmin,this.userPassword,this.userIconPath,this.userName);
        //SharePreferences
        saveUser(context,usermessage);
    }

    private void saveUser(Context context,String usermessage){
        SharedPreferences sp = context.getSharedPreferences("User",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("User",usermessage);
        editor.commit();
    }

    public LoginUser(Integer userId, Boolean isAdmin, String userPassword, String userIconPath, String userName) {
        this.userId = userId;
        this.isAdmin = isAdmin;
        this.userPassword = userPassword;
        this.userIconPath = userIconPath;
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    private Integer userId;
    private Boolean isAdmin;
    private String userPassword;
    private String userIconPath;
    private String userName;
}
