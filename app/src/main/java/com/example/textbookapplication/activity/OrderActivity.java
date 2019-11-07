package com.example.textbookapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.textbookapplication.R;
import com.example.textbookapplication.entity.LoginUser;
import com.example.textbookapplication.entity.Order;
import com.example.textbookapplication.entity.TextBook;
import com.example.textbookapplication.widget.pullToRefresh.PullToRefreshLayout;
import com.example.textbookapplication.widget.pullToRefresh.PullableListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    @ViewInject(R.id.orderBack)
    private ImageButton orderBack;
    @ViewInject(R.id.order_refresh_view)
    private PullToRefreshLayout refresh_view;
    @ViewInject(R.id.order_content_view)
    private PullableListView content_view;
    private List<Order> orders;
    private ImageOptions imageOptions = new ImageOptions.Builder()
            .setLoadingDrawableId(R.mipmap.ic_launcher)
            .setFailureDrawableId(R.mipmap.ic_launcher).build();
    private Context context;
    private OrderAdapter orderAdapter;
    int page_count;
    boolean isloadmore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        x.view().inject(this);
        context = getApplicationContext();
        refresh_view.setOnRefreshListener(new MyListener());
        orderBack.setOnClickListener(view -> finish());
        getFirst();
    }
    private void getFirst(){
        orders = new ArrayList<>();
        page_count = 0;
        isloadmore = false;
        orderAdapter = new OrderAdapter(orders);
        content_view.setAdapter(orderAdapter);
        orders.clear();
        orderData();
    }

    private void orderData(){
        //https://www.lijinzhou.top:2020/Orders?pagecount=0&size=5&user_no=5
        RequestParams params = new RequestParams("https://www.lijinzhou.top:2020/Orders");
        params.addQueryStringParameter("pagecount", page_count);
        params.addQueryStringParameter("size", 10);
        params.addQueryStringParameter("user_no", LoginUser.getLoginUser(context).getUserNo());
        x.http().get(params, new Callback.CommonCallback<String>(){
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String content = jsonObject.getString("content");
                    if (content.equals("[]")){
                        if(isloadmore){
                            refresh_view.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                        }else{
                            refresh_view.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                        }
                        runOnUiThread(()-> Toast.makeText(context,"到底了",Toast.LENGTH_SHORT).show());
                    }
                    JSONArray jsonArray = new JSONArray(content);
                    for (int i = 0;i<jsonArray.length();i++){
                        jsonObject = jsonArray.getJSONObject(i);
                        Integer orderNo = jsonObject.getInt("orderNo");
                        JSONObject userJson = jsonObject.getJSONObject("user");
                        Integer userNo = userJson.getInt("userNo");
                        String userId = userJson.getString("userId");
                        Boolean isAdmin = userJson.getBoolean("isAdmin");
                        String userPassword = userJson.getString("userPassword");
                        String userIconPath = userJson.getString("userIconPath");
                        String userName = userJson.getString("userName");

                        Double money = userJson.getDouble("money");
                        String address = userJson.getString("address");
                        String major = userJson.getString("major");
                        String mail = userJson.getString("mail");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date user_startTime = sdf.parse(userJson.getString("startTime"));
                        Date birth = sdf.parse(userJson.getString("birth"));
                        Boolean isFemale = userJson.getBoolean("isFemale");
                        LoginUser user = new LoginUser(userNo,userId,isAdmin,userPassword,userIconPath,userName,money,address,major,mail,user_startTime,birth,isFemale);

                        JSONObject bookJson = jsonObject.getJSONObject("textBook");
                        Integer bookNo = bookJson.getInt("bookNo");
                        String bookName = bookJson.getString("bookName");
                        String bookPic = bookJson.getString("bookPic");
                        String author = bookJson.getString("author");
                        Double bookPrice = bookJson.getDouble("bookPrice");
                        Integer totalnum = bookJson.getInt("totalnum");
                        TextBook textBook = new TextBook(bookNo,bookName,bookPic,author,bookPrice,totalnum);

                        Integer bookNum = jsonObject.getInt("bookNum");
                        Double bookValues = jsonObject.getDouble("bookValues");
                        String startTime = jsonObject.getString("startTime");

                        Order order = new Order(orderNo,textBook,user,bookNum,bookValues,startTime);
                        orders.add(order);
                    }

                    page_count +=1;
                    orderAdapter.notifyDataSetChanged();
                    if(isloadmore){
                        refresh_view.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                    }else{
                        refresh_view.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }finally {
                    if(isloadmore){
                        refresh_view.loadmoreFinish(PullToRefreshLayout.FAIL);
                    }else{
                        refresh_view.refreshFinish(PullToRefreshLayout.FAIL);
                    }
                }

            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if(isloadmore){
                    refresh_view.loadmoreFinish(PullToRefreshLayout.FAIL);
                }else{
                    refresh_view.refreshFinish(PullToRefreshLayout.FAIL);
                }
            }
            @Override
            public void onCancelled(CancelledException cex) {}
            @Override
            public void onFinished() {}
        });
    }

    private void getNext(){
        isloadmore=true;
        orderData();
    }
    class MyListener implements PullToRefreshLayout.OnRefreshListener
    {
        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout)
        {
            // 下拉刷新操作
            getFirst();
        }
        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout)
        {
            // 加载操作
            getNext();
        }
    }

    class OrderAdapter extends BaseAdapter {
        private List<Order> orders;
        private final LayoutInflater mInflater = LayoutInflater.from(context);

        public OrderAdapter(List<Order> orders) {
            this.orders = orders;
        }
        @Override
        public int getCount() {return orders.size();}
        @Override
        public Order getItem(int i) {return orders.get(i);}
        @Override
        public long getItemId(int i) {return i;}
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            OrderViewHolder holder;
            if (view == null){
                view = mInflater.inflate(R.layout.order_item, viewGroup, false);
                holder = new OrderViewHolder();
                x.view().inject(holder, view);
                view.setTag(holder);
            }else{
                holder = (OrderViewHolder) view.getTag();
            }
            holder.order_img_pb.setProgress(0);
            Order order = getItem(i);
            // 设置图片
            x.image().bind(
                    holder.order_item_icon,
                    order.getTextBook().getBookPic(),imageOptions,
                    new CustomBitmapLoadCallBack(holder)
            );
            holder.order_textbook_name.setText(order.getTextBook().getBookName());
            holder.order_textbook_num.setText("数量："+order.getBookNum()+"");
            holder.order_book_values.setText("总价："+order.getBookValues()+"");
            return view;
        }
    }
    @ContentView(R.layout.order_item)
    class OrderViewHolder{
        @ViewInject(R.id.order_item_icon)
        public ImageView order_item_icon;
        @ViewInject(R.id.order_textbook_name)
        public TextView order_textbook_name;
        @ViewInject(R.id.order_textbook_num)
        public TextView order_textbook_num;
        @ViewInject(R.id.order_book_values)
        public TextView order_book_values;
        @ViewInject(R.id.order_img_pb)
        public ProgressBar order_img_pb;
    }

    class CustomBitmapLoadCallBack implements org.xutils.common.Callback.ProgressCallback<Drawable> {
        private final OrderViewHolder holder;
        public CustomBitmapLoadCallBack(OrderViewHolder holder) { this.holder = holder; }
        @Override
        public void onWaiting() { this.holder.order_img_pb.setProgress(0); }
        @Override
        public void onStarted() {}
        @Override
        public void onLoading(long total, long current, boolean isDownloading) {
            if (total > 0) { this.holder.order_img_pb.setProgress((int) (current * 100 / total)); }
        }
        @Override
        public void onSuccess(Drawable result) {
            this.holder.order_img_pb.setProgress(100);
        }
        @Override
        public void onError(Throwable ex, boolean isOnCallback) { }
        @Override
        public void onCancelled(CancelledException cex) { }
        @Override
        public void onFinished() { }
    }
}

