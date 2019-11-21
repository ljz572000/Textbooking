package com.example.textbookapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.example.textbookapplication.R;
import com.example.textbookapplication.encryption.BCrypt;
import com.example.textbookapplication.entity.LoginUser;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.Calendar;

public class RegisteredActivity extends AppCompatActivity {
    @ViewInject(R.id.back_to_login)
    private ImageButton back_to_login;
    @ViewInject(R.id.register_id)
    private EditText register_id;
    @ViewInject(R.id.register_pwd)
    private EditText register_pwd;
    @ViewInject(R.id.register_pwd_2)
    private EditText register_pwd_2;
    @ViewInject(R.id.register_username)
    private EditText register_username;
    @ViewInject(R.id.major)
    private EditText major;
    @ViewInject(R.id.address)
    private EditText address;
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
    @ViewInject(R.id.submit_register)
    private Button submit_register;
    @ViewInject(R.id.btnMan)
    private RadioButton btnMan;
    private Calendar ca = Calendar.getInstance();
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        x.view().inject(this);
        context = getApplicationContext();
        edit_year.setText(""+ca.get(Calendar.YEAR));
        edit_month.setText(""+(ca.get(Calendar.MONTH)+1));
        edit_day.setText(""+ca.get(Calendar.DAY_OF_MONTH));

        year_add.setOnClickListener(changeBirth());
        year_sub.setOnClickListener(changeBirth());
        month_add.setOnClickListener(changeBirth());
        month_sub.setOnClickListener(changeBirth());
        day_add.setOnClickListener(changeBirth());
        day_sub.setOnClickListener(changeBirth());
        back_to_login.setOnClickListener(v -> finish());

        submit_register.setOnClickListener(register());
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
                    default:break;
            }
        };
    }
    private View.OnClickListener register() {
        return v -> {
            if(check()){
                RequestParams params = new RequestParams("https://www.lijinzhou.top:2020/api/insertAUser");
                params.addQueryStringParameter("userId", register_id.getText().toString());
                params.addQueryStringParameter("isAdmin", false);
                params.addQueryStringParameter("userPassword", BCrypt.hashpw(register_pwd.getText().toString(), BCrypt.gensalt()));
                params.addQueryStringParameter("userIconPath", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1572716673727&di=b245301eaef365c8623ce611c26b44b0&imgtype=0&src=http%3A%2F%2Fwww.itmop.com%2Fupload%2F2017-9%2F15046867122689390.jpeg");
                params.addQueryStringParameter("userName", register_username.getText());
                params.addQueryStringParameter("major", major.getText());
                params.addQueryStringParameter("address", address.getText());
                params.addQueryStringParameter("mail", mail.getText());
                params.addQueryStringParameter("birth",
                        edit_year.getText().toString()+"-"+edit_month.getText().toString()
                                +"-"+edit_day.getText().toString());
                params.addQueryStringParameter("isFemale", !btnMan.isChecked());
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        runOnUiThread(()->Toast.makeText(context,result,Toast.LENGTH_SHORT).show());
                        finish();
                    }
                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                    }
                    @Override
                    public void onCancelled(CancelledException cex) {
                    }
                    @Override
                    public void onFinished() {
                    }
                });
            }else {
             Toast.makeText(context,"信息未填写完整",Toast.LENGTH_SHORT).show();
            }
        };
    }
    private Boolean check(){
        if (
                register_id.getText().toString().equals("")||
                register_pwd.getText().toString().equals("")||
                        register_pwd_2.getText().toString().equals("")||
                        register_username.getText().toString().equals("")||
                        major.getText().toString().equals("")||
                        address.getText().toString().equals("")||
                        mail.getText().toString().equals("")
        ){
            return false;
        }else return true;
    }
}
