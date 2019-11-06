package com.example.textbookapplication.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.textbookapplication.R;
import com.example.textbookapplication.activity.DetailActivity;
import com.example.textbookapplication.adapter.TextbookAdapter;
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

@ContentView(R.layout.home_fragement)
public class HomeFragment extends BaseFragment {
    @ViewInject(R.id.refresh_view)
    private PullToRefreshLayout refresh_view;
    @ViewInject(R.id.content_view) //ls_show
    private PullableListView content_view;

    private List<TextBook> textBooks;
    private Context context;
    private int size =10;
    private int page_count;
    private TextbookAdapter adapter;
    ImageOptions imageOptions;
    private boolean isloadmore;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    private void Init(){
        refresh_view.setOnRefreshListener(new MyListener());
        getFirst();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Init();
    }
    private void getFirst(){
        /*
        设置懒加载，
         */
        textBooks = new ArrayList<>();
        imageOptions = new ImageOptions.Builder()
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                .setFailureDrawableId(R.mipmap.ic_launcher).build();
        page_count = 0;
        isloadmore = false;
        refresh_view.setOnRefreshListener(new MyListener());
        adapter = new TextbookAdapter(context,textBooks,imageOptions);
        content_view.setAdapter(adapter);
        textBooks.clear();
        textbookdata();

        content_view.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(context, DetailActivity.class);
            TextBook textBook = textBooks.get(i);
            intent.putExtra("textbook_item",textBook.toString());
            startActivity(intent);
        });
    }
    private void getNext(){
        isloadmore=true;
        textbookdata();
    }

private void textbookdata(){
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
                    return ;
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