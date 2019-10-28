package com.example.textbookapplication.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.textbookapplication.R;
import com.example.textbookapplication.entity.TextBook;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_detail)
public class DetailActivity extends AppCompatActivity {
    @ViewInject(R.id.back_to_home)
    private ImageButton back;
    @ViewInject(R.id.detail_pic)
    private ImageView pic;
    @ViewInject(R.id.detail_textbookName)
    private TextView textbookName;
    @ViewInject(R.id.detail_textbookPrice)
    private TextView textbookPrice;
    @ViewInject(R.id.detail_textbookAuthor)
    private TextView textbookAuthor;
    @ViewInject(R.id.add)
    private Button add;
    @ViewInject(R.id.buy)
    private Button buy;
    @ViewInject(R.id.total)
    private TextView total;
    @ViewInject(R.id.add_num)
    private ImageButton add_num;
    @ViewInject(R.id.sub_num)
    private ImageButton sub_num;
    @ViewInject(R.id.num)
    private TextView num;

    private static final String TAG = "DetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        x.view().inject(this);

        ImageOptions imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(-1000), DensityUtil.dip2px(-3000))
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                .setFailureDrawableId(R.mipmap.ic_launcher).build();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });




        TextBook textBook = getData();

        textbookName.setText(textBook.getBookName());

        textbookPrice.setText("价格：" + textBook.getBookPrice());
        textbookAuthor.setText("作者：" + textBook.getAuthor());
        total.setText("剩余数量: " + textBook.getTotalnum());

        x.image().bind(pic, textBook.getBookPic(), imageOptions);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addShoppingCart(textBook.getBookNo().toString(), textBook.getBookName(),num.getText().toString());
            }
        });
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buytextbook(textBook.getBookNo().toString(),textBook.getBookName(),num.getText().toString());
            }
        });

        add_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int textbookNum = Integer.parseInt(num.getText().toString());
                    textbookNum++;
                if (textbookNum>textBook.getTotalnum()){
                    Toast.makeText(getApplicationContext(),"sorry, 超过了剩余数量",Toast.LENGTH_SHORT).show();
                    textbookNum--;
                }
                num.setText(""+textbookNum);
            }
        });

        sub_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int textbookNum = Integer.parseInt(num.getText().toString());
                textbookNum--;
                if (textbookNum<1){
                    Toast.makeText(getApplicationContext(),"sorry, 最小的订购数量为1",Toast.LENGTH_SHORT).show();
                    textbookNum++;
                }
                num.setText(""+textbookNum);
            }
        });
    }

    private TextBook getData() {
        Intent intent = getIntent();
        String textbook_item = intent.getStringExtra("textbook_item");
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

    private void addShoppingCart(String bookNo, String bookName,String num) {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(DetailActivity.this);
        normalDialog.setTitle("加入购物车");
        normalDialog.setMessage("你确定要预定《" + bookName + "》");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "已将"+num+"本"+bookName+"加入购物车", Toast.LENGTH_SHORT).show();
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        normalDialog.show();
    }

    private void buytextbook(String bookNo, String bookName,String num) {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(DetailActivity.this);
        normalDialog.setTitle("立即购买");
        normalDialog.setMessage("你确定要购买《" + bookName + "》");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "已购买"+num+"本"+bookName+"加入购物车", Toast.LENGTH_SHORT).show();
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        normalDialog.show();
    }
}
