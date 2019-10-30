package com.example.textbookapplication.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.textbookapplication.R;
import com.example.textbookapplication.activity.SettingActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.me_fragment)
public class MeFragment extends BaseFragment {
    @ViewInject(R.id.setting)
    private RelativeLayout setting;
    @ViewInject(R.id.history)
    private RelativeLayout history;
    @ViewInject(R.id.money)
    private RelativeLayout money;
    @ViewInject(R.id.repair)
    private RelativeLayout repair;

    private Context context;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        setting.setOnClickListener(goSetting());
        history.setOnClickListener(showsomething());
        money.setOnClickListener(showsomething());
        repair.setOnClickListener(showsomething());
    }

    private View.OnClickListener showsomething() {
        return view -> Toast.makeText(getContext(), "点击xxx", Toast.LENGTH_SHORT).show();
    }

    private View.OnClickListener goSetting() {
        return view -> {

//            Toast.makeText(getContext(), "点击了设置", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, SettingActivity.class);
            startActivity(intent);
        };
    }
}
