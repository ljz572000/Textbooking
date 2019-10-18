package com.example.textbookapplication.Network;

import android.content.Context;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class Service {
       public static Call loginSerive(String userId, String userPassword){
           String postUrl = "https://www.lijinzhou.top:2020/login";
           OkHttpClient client = new OkHttpClient();
           FormBody formBody = new FormBody.Builder().add("userId", userId).add("userPassword", userPassword).build();

           Request request = new Request.Builder()
                   .url(postUrl)
                   .post(formBody)
                   .build();

           return client.newCall(request);
       }
}
