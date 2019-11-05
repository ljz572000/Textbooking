package com.example.textbookapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.textbookapplication.R;
import com.example.textbookapplication.encryption.BCrypt;
import com.example.textbookapplication.entity.LoginUser;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_repair_pwd)
public class RepairPwdActivity extends AppCompatActivity {
    @ViewInject(R.id.repair_pwd_back)
    private ImageButton repair_pwd_back;
    @ViewInject(R.id.oldPwd)
    private EditText oldPwd;
    @ViewInject(R.id.newPwd)
    private EditText newPwd;
    @ViewInject(R.id.newPwd2)
    private EditText newPwd2;
    @ViewInject(R.id.submit_pwd)
    private Button submit_pwd;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        context = getApplicationContext();
        repair_pwd_back.setOnClickListener(v->finish());
        submit_pwd.setOnClickListener(repairPwd());
    }

    private View.OnClickListener repairPwd() {
        return v -> {
            String old_pwd = oldPwd.getText().toString();
            String new_pwd = newPwd.getText().toString();
            String new_pwd2 = newPwd2.getText().toString();
            LoginUser user = LoginUser.getLoginUser(context);

            if (old_pwd.equals(new_pwd)) {
                Toast.makeText(context, "您输入的两次新旧密码相同，请重新输入", Toast.LENGTH_SHORT).show();
            } else if (new_pwd.equals(new_pwd2)){
                if ( BCrypt.checkpw(old_pwd,user.getUserPassword())) {
                    changePwd(user.getUserNo(), new_pwd);
                    //清楚数据
                    SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
                    sp.edit().clear().commit();
                    // 跳转到登陆界面
                    Intent logoutIntent = new Intent(RepairPwdActivity.this, LoginActivity.class);
                    logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(logoutIntent);
                    finish();
                } else {
                    Toast.makeText(context, "您输入的旧密码有误，请重新输入", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(context, "您两次输入的密码不匹配，请重新输入", Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void changePwd(Integer user_no, String user_pwd) {
        //https://www.lijinzhou.top:2020/repairPwd?user_no=1&user_pwd=1234
        RequestParams params = new RequestParams("https://www.lijinzhou.top:2020/repairPwd");
        params.addQueryStringParameter("user_no", user_no);
        params.addQueryStringParameter("user_pwd",BCrypt.hashpw(user_pwd, BCrypt.gensalt()));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                runOnUiThread(() -> Toast.makeText(context, "密码修改成功,请重新登录！！！", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }
}
