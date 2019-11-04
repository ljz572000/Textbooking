package com.example.textbookapplication.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.textbookapplication.activity.OrderActivity;
import com.example.textbookapplication.R;
import com.example.textbookapplication.activity.BalanceActivity;
import com.example.textbookapplication.activity.RepairActivity;
import com.example.textbookapplication.activity.SettingActivity;
import com.example.textbookapplication.entity.LoginUser;

import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.me_fragment)
public class MeFragment extends BaseFragment {
    @ViewInject(R.id.setting)
    private RelativeLayout setting;
    @ViewInject(R.id.order)
    private RelativeLayout order;
    @ViewInject(R.id.money)
    private RelativeLayout money;
    @ViewInject(R.id.repair)
    private RelativeLayout repair;
    @ViewInject(R.id.user_name)
    private TextView user_name;
    @ViewInject(R.id.user_id)
    private TextView user_id;
    @ViewInject(R.id.user_avator)
    private ImageView user_avator;
    private LoginUser user;
    private Context context;
    private ImageOptions imageOptions = new ImageOptions.Builder()
            .setLoadingDrawableId(R.mipmap.ic_launcher)
                .setFailureDrawableId(R.mipmap.ic_launcher).build();
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        user = LoginUser.getLoginUser(context);
        user_name.setText(user.getUserName());
        user_id.setText(user.getUserId());
        x.image().bind(user_avator, user.getUserIconPath(), imageOptions);
        setting.setOnClickListener(goSetting());
        order.setOnClickListener(goOrder());
        money.setOnClickListener(goBalance());
        repair.setOnClickListener(goRepair());
    }
    private View.OnClickListener goBalance(){
        return v-> {
            Intent intent = new Intent(context, BalanceActivity.class);
            startActivity(intent);
        };
    }
    private View.OnClickListener goOrder() {
        return v-> {
            Intent intent = new Intent(context, OrderActivity.class);
            startActivity(intent);
        };
    }
    private View.OnClickListener goSetting() {
        return view -> {
            Intent intent = new Intent(context, SettingActivity.class);
            startActivity(intent);
        };
    }

    private View.OnClickListener goRepair(){
        return view -> {
            Intent intent = new Intent(context, RepairActivity.class);
            startActivity(intent);
        };
    }
}
