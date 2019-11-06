package com.example.textbookapplication.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.textbookapplication.R;
import com.example.textbookapplication.entity.TextBook;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class AddTextBookActivity extends AppCompatActivity {
    @ViewInject(R.id.back_to_manage)
    private ImageButton back_to_manage;
    @ViewInject(R.id.item_pic)
    private ImageView item_pic;
    @ViewInject(R.id.textbook_name)
    private TextView textbook_name;
    @ViewInject(R.id.textbook_price)
    private EditText textbook_price;
    @ViewInject(R.id.add_total_num)
    private ImageButton add_total_num;
    @ViewInject(R.id.sub_total_num)
    private ImageButton sub_total_num;
    @ViewInject(R.id.total_num)
    private EditText total_num;
    @ViewInject(R.id.submit_change_totalNum)
    private Button submit_change_totalNum;
    private ImageOptions imageOptions = new ImageOptions.Builder()
            .setLoadingDrawableId(R.mipmap.ic_launcher)
            .setFailureDrawableId(R.mipmap.ic_launcher).build();
    private TextBook textBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_text_book);
        x.view().inject(this);
        textBook = getData();
        back_to_manage.setOnClickListener(view -> finish());
        x.image().bind(
                item_pic,
                textBook.getBookPic(), imageOptions
        );
        textbook_name.setText("书名：" + textBook.getBookName());
        textbook_price.setText(""+ textBook.getBookPrice());
        total_num.setText(""+textBook.getTotalnum());
        add_total_num.setOnClickListener(view -> {
            int totalnum = Integer.parseInt(total_num.getText().toString());
            totalnum++;
            total_num.setText("" + totalnum);
        });
        sub_total_num.setOnClickListener(view -> {
            int totalnum = Integer.parseInt(total_num.getText().toString());
            totalnum--;
            total_num.setText("" + totalnum);
        });

        submit_change_totalNum.setOnClickListener(
               view -> {
                   final AlertDialog.Builder normalDialog =
                           new AlertDialog.Builder(AddTextBookActivity.this);
                   normalDialog.setTitle("保存修改");
                   normalDialog.setMessage("你确定要修改《" + textBook.getBookName() + "》");
                   normalDialog.setPositiveButton("确定",
                           (dialog, which) -> updateTextBookNum(textBook.getBookNo(),Integer.parseInt(total_num.getText().toString())));
                   normalDialog.setNegativeButton("关闭",
                           (dialog, which) -> {});
                   normalDialog.show();
               }

        );
    }
    private void updateTextBookNum(Integer book_no, Integer num) {
        //https://www.lijinzhou.top:2020/ChangeTextBook?changNum=1000&price=100.0&bookNo=2
        RequestParams params = new RequestParams("https://www.lijinzhou.top:2020/ChangeTextBook");
        params.addQueryStringParameter("changNum", num);
        params.addQueryStringParameter("price", textbook_price.getText().toString());
        params.addQueryStringParameter("bookNo", book_no);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                runOnUiThread(()->Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show());
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {}
            @Override
            public void onCancelled(CancelledException cex) {}
            @Override
            public void onFinished() {}
        });
    }
    private TextBook getData() {
        Intent intent = getIntent();
        String textbook_item = intent.getStringExtra("manage_item");
        TextBook textBook = null;
        try {
            JSONObject jsonObject = new JSONObject(textbook_item);
            int bookNo = jsonObject.getInt("bookNo");
            String bookName = jsonObject.getString("bookName");
            String bookPic = jsonObject.getString("bookPic");
            String author = jsonObject.getString("author");
            Double bookPrice = jsonObject.getDouble("bookPrice");
            int totalnum = jsonObject.getInt("totalnum");
            textBook = new TextBook(bookNo, bookName, bookPic, author, bookPrice, totalnum);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return textBook;
    }
}
