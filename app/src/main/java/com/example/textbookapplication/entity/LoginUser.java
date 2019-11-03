package com.example.textbookapplication.entity;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginUser {
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
            this.userNo = jsonObject.getInt("userNo");
            this.userId = jsonObject.getString("userId");
            this.isAdmin = jsonObject.getBoolean("isAdmin");
            this.userPassword = jsonObject.getString("userPassword");
            this.userIconPath = jsonObject.getString("userIconPath");
            this.userName = jsonObject.getString("userName");
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        Log.i(TAG, "LoginUser: "+this.userId);
//        loginUser = new LoginUser(this.userId,this.isAdmin,this.userPassword,this.userIconPath,this.userName);
        //SharePreferences
        saveUser(context,usermessage);
    }

    private void saveUser(Context context,String usermessage){
        SharedPreferences sp = context.getSharedPreferences("User",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("User",usermessage);
        editor.commit();
    }

    public LoginUser(Integer userNo, String userId, Boolean isAdmin, String userPassword, String userIconPath, String userName) {
        this.userNo = userNo;
        this.userId = userId;
        this.isAdmin = isAdmin;
        this.userPassword = userPassword;
        this.userIconPath = userIconPath;
        this.userName = userName;
    }

    public static LoginUser getLoginUser(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        final String userinfo = sharedPreferences.getString("User","");
        JSONObject jsonObject = null;
        LoginUser user = null;
        try {
            jsonObject = new JSONObject(userinfo);
            Integer userNo = jsonObject.getInt("userNo");
            String userId = jsonObject.getString("userId");
            Boolean isAdmin = jsonObject.getBoolean("isAdmin");
            String userPassword = jsonObject.getString("userPassword");
            String userIconPath = jsonObject.getString("userIconPath");
            String userName = jsonObject.getString("userName");
            user = new LoginUser(userNo,userId,isAdmin,userPassword,userIconPath,userName);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }

    public Integer getUserNo() {

        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    private Integer userNo;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private Boolean isAdmin;
    private String userPassword;
    private String userIconPath;
    private String userName;
}
