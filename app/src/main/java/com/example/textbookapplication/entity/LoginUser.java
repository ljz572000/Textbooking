package com.example.textbookapplication.entity;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getUserNo() { return userNo; }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
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
            this.money = jsonObject.getDouble("money");
            this.address = jsonObject.getString("address");
            this.major = jsonObject.getString("major");
            this.mail = jsonObject.getString("mail");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            this.startTime = sdf.parse(jsonObject.getString("startTime"));
            this.birth = sdf.parse(jsonObject.getString("birth"));
            this.isFemale = jsonObject.getBoolean("isFemale");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        saveUser(context,usermessage);
    }

    private void saveUser(Context context,String usermessage){
        SharedPreferences sp = context.getSharedPreferences("User",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("User",usermessage);
        editor.commit();
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
            Double money = jsonObject.getDouble("money");
            String address = jsonObject.getString("address");
            String major = jsonObject.getString("major");
            String mail = jsonObject.getString("mail");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startTime = sdf.parse(jsonObject.getString("startTime"));
            Date birth = sdf.parse(jsonObject.getString("birth"));
            Boolean isFemale = jsonObject.getBoolean("isFemale");
            user = new LoginUser(userNo,userId,isAdmin,userPassword,userIconPath,userName,money,address,major,mail,startTime,birth,isFemale);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void updatePersonMess(LoginUser user,Context context){
//        https://www.lijinzhou.top:2020/login?userId=20160750
        RequestParams params = new RequestParams("https://www.lijinzhou.top:2020/login");
        params.addQueryStringParameter("userId", user.getUserId());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LoginUser loginUser = new LoginUser(result,context);
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {}
            @Override
            public void onCancelled(CancelledException cex) {}
            @Override
            public void onFinished() {}
        });
    }

    public LoginUser(Integer userNo, String userId, Boolean isAdmin, String userPassword, String userIconPath, String userName, Double money, String address, String major, String mail, Date startTime, Date birth, Boolean isFemale) {
        this.userNo = userNo;
        this.userId = userId;
        this.isAdmin = isAdmin;
        this.userPassword = userPassword;
        this.userIconPath = userIconPath;
        this.userName = userName;
        this.money = money;
        this.address = address;
        this.major = major;
        this.mail = mail;
        this.startTime = startTime;
        this.birth = birth;
        this.isFemale = isFemale;
    }

    private Integer userNo;
    private String userId;
    private Boolean isAdmin;
    private String userPassword;
    private String userIconPath;
    private String userName;
    private Double money;
    private String address;
    private String major;
    private String mail;
    private Date startTime;
    private Date birth;
    private Boolean isFemale;

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getMoney() {
        return money;
    }

    public String getAddress() {
        return address;
    }

    public String getMajor() {
        return major;
    }

    public String getMail() {
        return mail;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getBirth() {
        return birth;
    }

    public Boolean getFemale() {
        return isFemale;
    }
}
