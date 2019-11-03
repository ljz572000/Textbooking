package com.example.textbookapplication.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.textbookapplication.R;
import com.example.textbookapplication.activity.RepairActivity;
import com.example.textbookapplication.activity.SettingActivity;
import com.example.textbookapplication.entity.LoginUser;

import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.admin_fragment)
public class AdminFragment extends BaseFragment {
    @ViewInject(R.id.setting)
    private RelativeLayout setting;
    @ViewInject(R.id.repair)
    private RelativeLayout repair;
    @ViewInject(R.id.admin_avator)
    private ImageView admin_avator;
    @ViewInject(R.id.admin_name)
    private TextView admin_name;
    @ViewInject(R.id.admin_id)
    private TextView admin_id;
    private Context context;
    private LoginUser user;
    private ImageOptions imageOptions = new ImageOptions.Builder()
            .setLoadingDrawableId(R.mipmap.ic_launcher)
            .setFailureDrawableId(R.mipmap.ic_launcher).build();
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        user = LoginUser.getLoginUser(context);

        setting.setOnClickListener(goSetting());
        repair.setOnClickListener(goRepair());

        admin_name.setText(user.getUserName());
        admin_id.setText(user.getUserId());
        x.image().bind(admin_avator, user.getUserIconPath(), imageOptions);
    }
    private View.OnClickListener goRepair(){
        return view -> {
            Intent intent = new Intent(context, RepairActivity.class);
            startActivity(intent);
        };
    }
    private View.OnClickListener goSetting() {
        return view -> {
            Intent intent = new Intent(context, SettingActivity.class);
            startActivity(intent);
        };
    }

}
