package com.example.textbookapplication.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.textbookapplication.R;
import com.example.textbookapplication.activity.AddTextBookActivity;
import com.example.textbookapplication.activity.DetailActivity;
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

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.manage_fragment)
public class ManageFragment extends BaseFragment {
    @ViewInject(R.id.refresh_view)
    private PullToRefreshLayout refresh_view;
    @ViewInject(R.id.content_view) //ls_show
    private PullableListView content_view;
    private List<TextBook> textBooks;
    private Context context;
    private int size =10;
    private int page_count;
    private ManageAdapter adapter;
    ImageOptions imageOptions = new ImageOptions.Builder()
            .setLoadingDrawableId(R.mipmap.ic_launcher)
            .setFailureDrawableId(R.mipmap.ic_launcher).build();
    private boolean isloadmore;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        refresh_view.setOnRefreshListener(new MyListener());
        getFirst();

    }

    private void getFirst(){
        textBooks = new ArrayList<>();
        page_count = 0;
        isloadmore = false;
        adapter = new ManageAdapter(textBooks);
        content_view.setAdapter(adapter);
        textBooks.clear();
        textBookData();

        content_view.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(context, AddTextBookActivity.class);
            TextBook textBook = textBooks.get(i);
            intent.putExtra("manage_item",textBook.toString());
            startActivity(intent);
        });
    }

    private void  getNext(){
        isloadmore=true;
        textBookData();
    }

    private void textBookData(){
        RequestParams params = new RequestParams("https://www.lijinzhou.top:2020/Textbooks");
        params.addQueryStringParameter("pagecount", page_count);
        params.addQueryStringParameter("size", size);
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
                        new Thread(()-> Toast.makeText(context,"到底了",Toast.LENGTH_SHORT).show());
                    }
                    JSONArray jsonArray = new JSONArray(content);
                    for (int i = 0;i<jsonArray.length();i++){
                        jsonObject = jsonArray.getJSONObject(i);
                        Integer bookNo = jsonObject.getInt("bookNo");
                        String bookName = jsonObject.getString("bookName");
                        String bookPic = jsonObject.getString("bookPic");
                        String author = jsonObject.getString("author");
                        Double bookPrice= jsonObject.getDouble("bookPrice");
                        Integer totalnum = jsonObject.getInt("totalnum");
                        TextBook textBook = new TextBook(bookNo,bookName,bookPic,author,bookPrice,totalnum);
                        textBooks.add(textBook);
                    }
                    page_count +=1;
                    adapter.notifyDataSetChanged();
                    if(isloadmore){
                        refresh_view.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                    }else{
                        refresh_view.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
    }

    class ManageAdapter extends BaseAdapter{
        private List<TextBook> textBooks;
        private final LayoutInflater mInflater = LayoutInflater.from(context);

        public ManageAdapter(List<TextBook> textBooks) {
            this.textBooks = textBooks;
        }
        @Override
        public int getCount() { return textBooks.size(); }
        @Override
        public TextBook getItem(int i) { return textBooks.get(i); }
        @Override
        public long getItemId(int i) {
            return i;
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            OrderViewHolder holder;
            if (view == null){
                view = mInflater.inflate(R.layout.order_item, viewGroup, false);
                holder = new OrderViewHolder();
                x.view().inject(holder, view);
                view.setTag(holder);
            }else{
                holder =(OrderViewHolder) view.getTag();
            }
            holder.order_img_pb.setProgress(0);
            TextBook textBook = getItem(i);
            // 设置图片
            x.image().bind(
                    holder.item_icon,
                    textBook.getBookPic(),imageOptions,
                    new CustomBitmapLoadCallBack(holder)
            );
            holder.textbook_name.setText(textBook.getBookName());
            holder.textbook_total_num.setText("剩余数量："+textBook.getTotalnum()+"");
            holder.book_price.setText("单价："+textBook.getBookPrice()+"");
            return view;
        }

    }

    @ContentView(R.layout.order_item)
    class OrderViewHolder{
        @ViewInject(R.id.order_item_icon)
        public ImageView item_icon;
        @ViewInject(R.id.order_textbook_name)
        public TextView textbook_name;
        @ViewInject(R.id.order_textbook_num)
        public TextView textbook_total_num;
        @ViewInject(R.id.order_book_values)
        public TextView book_price;
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
}
