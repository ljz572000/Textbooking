package com.example.textbookapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.textbookapplication.Fragment.HomeFragment;
import com.example.textbookapplication.Fragment.MeFragment;
import com.example.textbookapplication.Fragment.MessageFragment;
import com.example.textbookapplication.Fragment.ShoppingCartFragment;
import com.example.textbookapplication.Network.Service;
import com.example.textbookapplication.R;
import com.example.textbookapplication.adapter.MyFragmentAdapter;
import com.example.textbookapplication.encryption.BCrypt;
import com.example.textbookapplication.entity.LoginUser;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
public class MainActivity extends AppCompatActivity {

    //底部导航栏
    @ViewInject(R.id.radio0)
    private RadioButton home_radio;
    @ViewInject(R.id.radio1)
    private RadioButton message_radio;
    @ViewInject(R.id.radio2)
    private RadioButton shoppingCart_radio;
    @ViewInject(R.id.radio3)
    private RadioButton me_radio;
    @ViewInject(R.id.viewPager)
    private ViewPager viewPager;

    private boolean isExit=false;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);
        isLocalUser();
        home_radio.setOnClickListener(changeFragment());
        message_radio.setOnClickListener(changeFragment());
        shoppingCart_radio.setOnClickListener(changeFragment());
        me_radio.setOnClickListener(changeFragment());
        setupFragment();
    }

    private static final String TAG = "MainActivity";
    private void isLocalUser(){
        SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        final String userinfo = sharedPreferences.getString("User","");
        if (userinfo.equals("")){
            //前往登录页面
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }else {
            //使用本地信息登录
            try {
                JSONObject jsonObject = new JSONObject(userinfo);
                String userId = jsonObject.getString("userId");
                String userPassword = jsonObject.getString("userPassword");
                Call call = Service.loginSerive(userId);
                call.enqueue(new Callback(){
                    @Override
                    public void onFailure(Call call, IOException e) {
                        call.cancel();
                        runOnUiThread(() -> Toast.makeText(MainActivity.this, "网络连接错误", Toast.LENGTH_SHORT).show());
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String info = response.body().string();

                        if (response.isSuccessful()) {
                            //此处，先将响应体保存到内存中
                            if (!info.equals("")) {
                                LoginUser user = new LoginUser(info,context);
                                if (userPassword.equals(user.getUserPassword())){
                                    //如果是管理员前往管理员界面
                                    if (user.getAdmin()){
                                        Intent intent = new Intent(MainActivity.this, AdminMainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                } else {
                                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }



    private View.OnClickListener changeFragment(){
        return view -> {
            switch (view.getId()){
                case R.id.radio0:
                    viewPager.setCurrentItem(0,false);
                    break;
                case R.id.radio1:
                    viewPager.setCurrentItem(1,false);
                    break;
                case R.id.radio2:
                    viewPager.setCurrentItem(2,false);
                    break;
                case R.id.radio3:
                    viewPager.setCurrentItem(3,false);
                    break;
                default:
                    break;
            }
        };
    }

    private void setupFragment(){
        FragmentManager fm = getSupportFragmentManager();
        List<Fragment> fs = new ArrayList<>();
        fs.add(new HomeFragment());
        fs.add(new MessageFragment());
        fs.add(new ShoppingCartFragment());
        fs.add(new MeFragment());
        MyFragmentAdapter adapter = new MyFragmentAdapter(fm,fs);

        viewPager.setAdapter(adapter);

        viewPager.setCurrentItem(0,false);
        home_radio.setChecked(true);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        home_radio.setChecked(true);
                        break;
                    case 1:
                        message_radio.setChecked(true);
                        break;
                    case 2:
                        shoppingCart_radio.setChecked(true);
                        break;
                    case 3:
                        me_radio.setChecked(true);
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