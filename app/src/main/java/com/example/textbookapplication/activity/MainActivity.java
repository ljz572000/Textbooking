package com.example.textbookapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.RadioButton;

import com.example.textbookapplication.Fragment.HomeFragment;
import com.example.textbookapplication.Fragment.MeFragment;
import com.example.textbookapplication.Fragment.MessageFragment;
import com.example.textbookapplication.Fragment.ShoppingCartFragment;
import com.example.textbookapplication.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.radio0)
    private RadioButton home_radio;
    @ViewInject(R.id.radio1)
    private RadioButton radio1;
    @ViewInject(R.id.radio2)
    private RadioButton radio2;
    @ViewInject(R.id.radio3)
    private RadioButton me_radio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);

        home_radio.setOnClickListener(view -> changeFragment(new HomeFragment()));
        radio1.setOnClickListener(view -> changeFragment(new MessageFragment()));

        radio2.setOnClickListener(v -> changeFragment(new ShoppingCartFragment()));
        me_radio.setOnClickListener(v -> changeFragment(new MeFragment()));

//        Intent intent = getIntent();
//        String message = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);
//        textView.setText(message);
    }

    private void changeFragment(Fragment fragment){
        // 实例化碎片管理器对象
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        //选择fragment替换的部分
        ft.replace(R.id.cl_content,fragment);
        ft.commit();
    }
}