package com.example.textbookapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.textbookapplication.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class AboutActivity extends AppCompatActivity {
    @ViewInject(R.id.back_to_setting)
    private ImageButton back_to_setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        x.view().inject(this);
        back_to_setting.setOnClickListener(v->{
            Intent intent = new Intent(AboutActivity.this,SettingActivity.class);
            startActivity(intent);
        });
    }
}
