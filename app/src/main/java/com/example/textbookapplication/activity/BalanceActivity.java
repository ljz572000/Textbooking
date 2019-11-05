package com.example.textbookapplication.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.textbookapplication.R;
import com.example.textbookapplication.entity.LoginUser;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_balance)
public class BalanceActivity extends AppCompatActivity {
    @ViewInject(R.id.balanceBack)
    private ImageButton balanceBack;
    @ViewInject(R.id.money_value)
    private TextView money_value;
    @ViewInject(R.id.submit_charge)
    private Button submit_charge;
    @ViewInject(R.id.charge_values)
    private EditText charge_values;
    private LoginUser user;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        context = getApplicationContext();
        user = LoginUser.getLoginUser(context);
        money_value.setText("当前余额："+user.getMoney()+"元");
        balanceBack.setOnClickListener(view -> finish());
        submit_charge.setOnClickListener(chargeMoney());
    }

    private View.OnClickListener chargeMoney(){
        //https://www.lijinzhou.top:2020/chargeMoney?user_no=5&money=2000
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(BalanceActivity.this);

        return view ->{
            double values = user.getMoney()+Double.parseDouble(charge_values.getText().toString());
            normalDialog.setTitle("充值金额");
            normalDialog.setMessage("您是否充值" + charge_values.getText().toString() + "元");
            normalDialog.setPositiveButton("确定",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            RequestParams params = new RequestParams("https://www.lijinzhou.top:2020/chargeMoney");
                            params.addQueryStringParameter("user_no", user.getUserNo());
                            params.addQueryStringParameter("money", values);
                            x.http().post(params, new Callback.CommonCallback<String>() {
                                @Override
                                public void onSuccess(String result) {
                                    updatePersonMess();
                                    runOnUiThread(()->money_value.setText("当前余额："+values+"元"));
                                }
                                @Override
                                public void onError(Throwable ex, boolean isOnCallback) {}
                                @Override
                                public void onCancelled(CancelledException cex) {}
                                @Override
                                public void onFinished() {}
                            });
                        }
                    });
            normalDialog.setNegativeButton("关闭",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
            normalDialog.show();
        };
    }

    private void updatePersonMess(){
//        https://www.lijinzhou.top:2020/login?userId=20160750
        RequestParams params = new RequestParams("https://www.lijinzhou.top:2020/login");
        params.addQueryStringParameter("userId", user.getUserId());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LoginUser loginUser = new LoginUser(result,context);
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {}
            @Override
            public void onCancelled(CancelledException cex) {}
            @Override
            public void onFinished() {}
        });
    }
}
