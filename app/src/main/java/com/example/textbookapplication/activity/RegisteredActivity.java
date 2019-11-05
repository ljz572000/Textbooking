package com.example.textbookapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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

@ContentView(R.layout.activity_registered)
public class RegisteredActivity extends AppCompatActivity {
    @ViewInject(R.id.back_to_login)
    private ImageButton back_to_login;
    @ViewInject(R.id.register_id)
    private EditText register_id;
    @ViewInject(R.id.register_pwd)
    private EditText register_pwd;
    @ViewInject(R.id.register_username)
    private EditText register_username;
    @ViewInject(R.id.submit_register)
    private Button submit_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        back_to_login.setOnClickListener(v->finish());
        submit_register.setOnClickListener(register());
    }
    private View.OnClickListener register(){
        return v->{
//            Toast.makeText(getApplicationContext(),"提交",Toast.LENGTH_SHORT).show();
            RequestParams params = new RequestParams("https://www.lijinzhou.top:2020/insertAUser");
            params.addQueryStringParameter("userId", register_id.getText().toString());
            params.addQueryStringParameter("isAdmin", false);
            params.addQueryStringParameter("userPassword", BCrypt.hashpw(register_pwd.getText().toString(), BCrypt.gensalt()));
            params.addQueryStringParameter("userIconPath", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1572716673727&di=b245301eaef365c8623ce611c26b44b0&imgtype=0&src=http%3A%2F%2Fwww.itmop.com%2Fupload%2F2017-9%2F15046867122689390.jpeg");
            params.addQueryStringParameter("userName", register_username.getText());
            x.http().post(params, new Callback.CommonCallback<String>(){
                @Override
                public void onSuccess(String result) {
                    finish();
                }
                @Override
                public void onError(Throwable ex, boolean isOnCallback) {}
                @Override
                public void onCancelled(CancelledException cex) {}
                @Override
                public void onFinished() {}
            });
        };
    }
}
