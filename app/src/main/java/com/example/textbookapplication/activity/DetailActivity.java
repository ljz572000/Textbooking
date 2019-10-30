package com.example.textbookapplication.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.textbookapplication.R;
import com.example.textbookapplication.entity.LoginUser;
import com.example.textbookapplication.entity.TextBook;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
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

        back.setOnClickListener(backToMain());

        TextBook textBook = getData();

        textbookName.setText(textBook.getBookName());

        textbookPrice.setText("价格：" + textBook.getBookPrice());
        textbookAuthor.setText("作者：" + textBook.getAuthor());
        total.setText("剩余数量: " + textBook.getTotalnum());

        x.image().bind(pic, textBook.getBookPic(), imageOptions);

        add.setOnClickListener(order(textBook.getBookNo(), textBook.getBookName(),num.getText().toString(),textBook.getBookPrice()));
        buy.setOnClickListener(order(textBook.getBookNo(), textBook.getBookName(),num.getText().toString(),textBook.getBookPrice()));

        add_num.setOnClickListener(changNum(textBook.getTotalnum()));
        sub_num.setOnClickListener(changNum(textBook.getTotalnum()));


    }

    private View.OnClickListener order(Integer bookNo,String bookName,String num,Double bookPrice) {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(DetailActivity.this);
        return view -> {
            switch (view.getId()) {
                case R.id.buy:
                    normalDialog.setTitle("立即购买");
                    normalDialog.setMessage("你确定要购买《" + bookName + "》");
                    normalDialog.setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String content ="已购买" + num + "本" + bookName + "加入购物车";
                                    sendMessage(content);
                                }
                            });
                    normalDialog.setNegativeButton("关闭",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    normalDialog.show();
                    break;
                case R.id.add:
                    normalDialog.setTitle("加入购物车");
                    normalDialog.setMessage("你确定要预定《" + bookName + "》");
                    normalDialog.setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String content = "已将" + num + "本" + bookName + "加入购物车";
                                    sendMessage(content);
                                    addShoppingCart(bookNo,bookPrice,Integer.parseInt(num));
                                }
                            });
                    normalDialog.setNegativeButton("关闭",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    normalDialog.show();
                    break;
                    default:
                        break;
            }
        };
    }

    private View.OnClickListener changNum(Integer totalnum) {
        return view -> {
            int textbookNum = Integer.parseInt(num.getText().toString());
            switch (view.getId()) {
                case R.id.add_num:
                    textbookNum++;
                    if (textbookNum > totalnum) {
                        Toast.makeText(getApplicationContext(), "sorry, 超过了剩余数量", Toast.LENGTH_SHORT).show();
                        textbookNum--;
                    }
                    num.setText("" + textbookNum);
                    break;
                case R.id.sub_num:
                    textbookNum--;
                    if (textbookNum < 1) {
                        Toast.makeText(getApplicationContext(), "sorry, 最小的订购数量为1", Toast.LENGTH_SHORT).show();
                        textbookNum++;
                    }
                    num.setText("" + textbookNum);
                    break;
                default:
                    break;
            }
        };
    }

    private View.OnClickListener backToMain() {
        return view -> finish();
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

    private void addShoppingCart(Integer book_no,Double book_price,Integer num) {
        //https://www.lijinzhou.top:2020/addShoppingCart?book_no=1&book_num=1&book_values=100.0&user_no=20160751
        Double book_values = book_price * num;
        RequestParams params = new RequestParams("https://www.lijinzhou.top:2020/addShoppingCart");
        params.addQueryStringParameter("book_no", book_no);
        params.addQueryStringParameter("book_num", num);
        params.addQueryStringParameter("book_values", book_values);
        params.addQueryStringParameter("user_no", LoginUser.loginUser.getUserId());
        x.http().get(params, new Callback.CommonCallback<String>(){

            @Override
            public void onSuccess(String result) {}
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {}
            @Override
            public void onCancelled(CancelledException cex) {}
            @Override
            public void onFinished() {}
        });
    }

    private void buytextbook(String bookNo, String bookName, String num) {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(DetailActivity.this);

    }


    private void sendMessage(String content){
        RequestParams params = new RequestParams("https://www.lijinzhou.top:2020/NewMessage");
        params.addQueryStringParameter("content", content);
        params.addQueryStringParameter("user_no", LoginUser.loginUser.getUserId());
        x.http().get(params, new Callback.CommonCallback<String>(){
            @Override
            public void onSuccess(String result) {
                runOnUiThread( ()->Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT).show());
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
