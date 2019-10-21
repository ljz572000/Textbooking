package com.example.textbookapplication.Network;

import android.content.Context;

import org.xutils.http.RequestParams;

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

       public static Call textbookList(Integer pagecount,Integer size){
           String getUrl = "https://www.lijinzhou.top:2020/Textbooks?pagecount="+pagecount+"&size="+size;
           OkHttpClient client = new OkHttpClient();
           Request request = new Request.Builder().url(getUrl).build();
           return client.newCall(request);
       }
}
