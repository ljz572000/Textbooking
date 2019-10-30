package com.example.textbookapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.textbookapplication.AboutActivity;
import com.example.textbookapplication.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_setting)
public class SettingActivity extends AppCompatActivity {
    @ViewInject(R.id.back_to_me)
    private ImageButton backToMe;
    @ViewInject(R.id.quit)
    private RelativeLayout quit;
    @ViewInject(R.id.about)
    private RelativeLayout about;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        backToMe.setOnClickListener(goback());
        quit.setOnClickListener(logout());
        about.setOnClickListener(appContext());
        context = getApplicationContext();
    }

    private View.OnClickListener appContext(){
        return view -> {
            Intent aboutIntent = new Intent(SettingActivity.this, AboutActivity.class);
            startActivity(aboutIntent);
            finish();
        };
    }
    private View.OnClickListener goback() {
        return view -> finish();
    }

    private View.OnClickListener logout() {
        return view -> {
            //清楚数据
            SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
            sp.edit().clear().commit();
            // 跳转到登陆界面
            Intent logoutIntent = new Intent(SettingActivity.this, LoginActivity.class);
            logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(logoutIntent);
            finish();
        };
    }
}
