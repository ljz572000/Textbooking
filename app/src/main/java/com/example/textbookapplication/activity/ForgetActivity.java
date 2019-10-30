package com.example.textbookapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.textbookapplication.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_forget)
public class ForgetActivity extends AppCompatActivity {
    @ViewInject(R.id.back_to_login)
    private ImageButton back_to_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_forget);
        x.view().inject(this);
        back_to_login.setOnClickListener(backToLogin());
    }

    private View.OnClickListener backToLogin(){
        return view -> finish();
    }
}
