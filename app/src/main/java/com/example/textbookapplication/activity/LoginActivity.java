package com.example.textbookapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.textbookapplication.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    // 登陆按钮
    @ViewInject(R.id.btnLogin)
    private Button btnLogin;
    // 显示用户名和密码
    @ViewInject(R.id.editId)
    private EditText editId;
    @ViewInject(R.id.editPwd)
    private EditText editPwd;
    // 创建等待框
    @ViewInject(R.id.loading)
    private ProgressDialog dialog;

    public static final String EXTRA_MESSAGE = "LoginMessage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //获取控件
        x.view().inject(this);
//        检测网络
        if (!checkNetwork()){
            Toast toast = Toast.makeText(LoginActivity.this,"网络未连接", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }

        btnLogin.setOnClickListener(v -> {
            btnLogin.setEnabled(false);//点了登录后不可以再点，避免用户乱点
            login(editId.getText().toString(),editPwd.getText().toString());
        });

    }

    private boolean checkNetwork(){
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        if (networkInfo !=null){
            return networkInfo.isAvailable();
        }
        return false;
    }

    private void login(String userId, String userPassword){
        String postUrl= "https://www.lijinzhou.top:2020/login";
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder().add("userId",userId).add("userPassword",userPassword).build();

        Request request = new Request.Builder()
                .url(postUrl)
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                call.cancel();
                runOnUiThread(() -> Toast.makeText(LoginActivity.this,"网络连接错误，请检查网络设置后重试。", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String info = response.body().string();
                if (response.isSuccessful()){
                    //此处，先将响应体保存到内存中
                    if (!info.equals("")){
                            Log.i("onResponse", info);
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra(EXTRA_MESSAGE,info);
                            startActivity(intent);
                            finish();
                    }else {
                        runOnUiThread(() -> Toast.makeText(LoginActivity.this,"登录失败", Toast.LENGTH_SHORT).show());
                    }
                }

            }
        });
    }

}
