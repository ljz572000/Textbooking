package com.example.textbookapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.textbookapplication.R;
import com.example.textbookapplication.entity.LoginUser;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class ForgetActivity extends AppCompatActivity {
    @ViewInject(R.id.back_to_login)
    private ImageButton back_to_login;
    @ViewInject(R.id.forget_account)
    private EditText forget_account;
    @ViewInject(R.id.submit_forget)
    private Button submit_forget;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        x.view().inject(this);
        context = getApplicationContext();
        back_to_login.setOnClickListener(v->finish());
        submit_forget.setOnClickListener(v-> goReSetPwd());
    }

    private void goReSetPwd(){
        String userId = forget_account.getText().toString();
        //https://www.lijinzhou.top:2020/goToResetPage?userId=20160750
        RequestParams params = new RequestParams("https://www.lijinzhou.top:2020/api/goToResetPage");
        params.addQueryStringParameter("userId", userId);
        x.http().post(params, new Callback.CommonCallback<String>(){
            @Override
            public void onSuccess(String result) {
                if (result.equals("您输入的账号不存在")){
                    runOnUiThread(()->Toast.makeText(context,"您输入的账号不存在",Toast.LENGTH_SHORT).show());
                }else {
                    runOnUiThread(()->Toast.makeText(context,"请查看邮箱",Toast.LENGTH_SHORT).show());
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {}
            @Override
            public void onCancelled(CancelledException cex) {}
            @Override
            public void onFinished() {}
        });
    }
}
