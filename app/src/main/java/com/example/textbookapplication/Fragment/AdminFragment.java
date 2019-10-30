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

@ContentView(R.layout.admin_fragment)
public class AdminFragment extends BaseFragment {
    @ViewInject(R.id.setting)
    private RelativeLayout setting;

    private Context context;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        setting.setOnClickListener(goSetting());
    }

    private View.OnClickListener goSetting() {
        return view -> {
            Intent intent = new Intent(context, SettingActivity.class);
            startActivity(intent);
        };
    }
}
