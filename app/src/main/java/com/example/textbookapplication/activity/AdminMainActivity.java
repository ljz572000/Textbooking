package com.example.textbookapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.textbookapplication.Fragment.AdminFragment;
import com.example.textbookapplication.Fragment.HomeFragment;
import com.example.textbookapplication.Fragment.ManageFragment;
import com.example.textbookapplication.Fragment.MeFragment;
import com.example.textbookapplication.Fragment.MessageFragment;
import com.example.textbookapplication.Fragment.ShoppingCartFragment;
import com.example.textbookapplication.R;
import com.example.textbookapplication.adapter.MyFragmentAdapter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AdminMainActivity extends AppCompatActivity {
    @ViewInject(R.id.manage)
    private RadioButton manage;
    @ViewInject(R.id.admin)
    private RadioButton admin;
    @ViewInject(R.id.adminViewPager)
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        x.view().inject(this);
        manage.setOnClickListener(changeFragment());
        admin.setOnClickListener(changeFragment());
        setupFragment();
    }
    private View.OnClickListener changeFragment(){
        return view -> {
            switch (view.getId()){
                case R.id.manage:
                    viewPager.setCurrentItem(0,false);
                    break;
                case R.id.admin:
                    viewPager.setCurrentItem(1,false);
                    break;
                default:
                    break;
            }
        };
    }

    private void setupFragment(){
        FragmentManager fm = getSupportFragmentManager();
        List<Fragment> fs = new ArrayList<>();
        fs.add(new ManageFragment());
        fs.add(new AdminFragment());
        MyFragmentAdapter adapter = new MyFragmentAdapter(fm,fs);

        viewPager.setAdapter(adapter);

        viewPager.setCurrentItem(0,false);
        manage.setChecked(true);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        manage.setChecked(true);
                        break;
                    case 1:
                        admin.setChecked(true);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private boolean isExit=false;

    //退出方法
    @Override
    public void onBackPressed() {
        if (isExit){
            super.onBackPressed();
            finish();
        }else {
            isExit = true;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            },2000);
            Toast.makeText(getApplicationContext(), "再点击一次退出程序", Toast.LENGTH_SHORT).show();
        }
    }
}
