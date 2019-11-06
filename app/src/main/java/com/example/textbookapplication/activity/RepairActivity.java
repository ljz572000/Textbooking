package com.example.textbookapplication.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.textbookapplication.R;
import com.example.textbookapplication.entity.LoginUser;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.Calendar;

public class RepairActivity extends AppCompatActivity {
    @ViewInject(R.id.repairBack)
    private ImageButton repairBack;
    @ViewInject(R.id.address)
    private EditText address;
    @ViewInject(R.id.major)
    private EditText major;
    @ViewInject(R.id.mail)
    private EditText mail;
    @ViewInject(R.id.year_add)
    private ImageButton year_add;
    @ViewInject(R.id.edit_year)
    private EditText edit_year;
    @ViewInject(R.id.year_sub)
    private ImageButton year_sub;
    @ViewInject(R.id.month_add)
    private ImageButton month_add;
    @ViewInject(R.id.edit_month)
    private EditText edit_month;
    @ViewInject(R.id.month_sub)
    private ImageButton month_sub;
    @ViewInject(R.id.day_add)
    private ImageButton day_add;
    @ViewInject(R.id.edit_day)
    private EditText edit_day;
    @ViewInject(R.id.day_sub)
    private ImageButton day_sub;
    @ViewInject(R.id.submit)
    private Button submit;
    private Context context;
    private LoginUser user;
    private Calendar ca = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair);
        x.view().inject(this);
        context = getApplicationContext();

        user = LoginUser.getLoginUser(context);
        address.setText(user.getAddress());
        major.setText(user.getMajor());
        mail.setText(user.getMail());

        ca.setTime(user.getBirth());
        edit_year.setText(""+ca.get(Calendar.YEAR));
        edit_month.setText(""+(ca.get(Calendar.MONTH)+1));
        edit_day.setText(""+ca.get(Calendar.DAY_OF_MONTH));

        year_add.setOnClickListener(changeBirth());
        year_sub.setOnClickListener(changeBirth());
        month_add.setOnClickListener(changeBirth());
        month_sub.setOnClickListener(changeBirth());
        day_add.setOnClickListener(changeBirth());
        day_sub.setOnClickListener(changeBirth());

        submit.setOnClickListener(changeData());

        repairBack.setOnClickListener(view -> finish());
    }

    private View.OnClickListener changeBirth(){
        return view -> {
            int mYear = Integer.parseInt(edit_year.getText().toString());
            int mMonth = Integer.parseInt(edit_month.getText().toString());
            int mDay = Integer.parseInt(edit_day.getText().toString());

            switch (view.getId()){
                case R.id.year_add:
                    if (mYear>=Calendar.getInstance().get(Calendar.YEAR)){
                        Toast.makeText(context,"不能大于"+Calendar.getInstance().get(Calendar.YEAR),Toast.LENGTH_SHORT).show();
                    }else {
                    mYear++;}
                    edit_year.setText(""+mYear);
                    break;
                case R.id.year_sub:
                    if (mYear<=ca.get(Calendar.YEAR)-120){
                        Toast.makeText(context,"不能小于120岁",Toast.LENGTH_SHORT).show();
                    }else {
                    mYear--;}
                    edit_year.setText(""+mYear);
                    break;
                case R.id.month_add:
                    if (mMonth>=12){
                        Toast.makeText(context,"不能大于12",Toast.LENGTH_SHORT).show();
                    }else {
                    mMonth++;}edit_month.setText(""+mMonth);
                    break;
                case R.id.month_sub:
                    if (mMonth<=1){
                        Toast.makeText(context,"不能小于1",Toast.LENGTH_SHORT).show();
                    }else {
                    mMonth--;}edit_month.setText(""+mMonth);
                    break;
                case R.id.day_add:
                    if (mDay>30){
                        Toast.makeText(context,"不能大于31",Toast.LENGTH_SHORT).show();
                    }else {
                        mDay++;}edit_day.setText(""+mDay);
                    break;
                case R.id.day_sub:
                    if (mDay<=1){
                        Toast.makeText(context,"不能小于1",Toast.LENGTH_SHORT).show();
                    }else {
                    mDay--;}edit_day.setText(""+mDay);
                    break;
                default:
                    break;
            }
        };
    }

    private View.OnClickListener changeData(){

        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(RepairActivity.this);
        //https://www.lijinzhou.top:2020/changeData?userNo=5&address=上海电力学院&major=计算机科学与技术&mail=2373861592@qq.com&birth=1997-05-16
        return view -> {
            normalDialog.setTitle("修改个人资料");
            normalDialog.setMessage("您是否保存当前信息");
            normalDialog.setPositiveButton("确定",
                    (dialog, which) -> {
                        RequestParams params = new RequestParams("https://www.lijinzhou.top:2020/changeData");
                        params.addQueryStringParameter("userNo", user.getUserNo());
                        params.addQueryStringParameter("address",address.getText().toString());
                        params.addQueryStringParameter("major", major.getText().toString());
                        params.addQueryStringParameter("mail", mail.getText().toString());
                        params.addQueryStringParameter("birth",
                                edit_year.getText().toString()+"-"+edit_month.getText().toString()
                                        +"-"+edit_day.getText().toString());
                        x.http().post(params, new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                runOnUiThread(()->Toast.makeText(context,result,Toast.LENGTH_SHORT).show());
                                LoginUser.updatePersonMess(user,context);
                            }
                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {
                            }
                            @Override
                            public void onCancelled(CancelledException cex) {
                            }
                            @Override
                            public void onFinished() {}
                        });
                });
        normalDialog.setNegativeButton("关闭",
                (dialog, which) -> {});
        normalDialog.show();
    };
    }
}
