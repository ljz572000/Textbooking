package com.example.textbookapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.textbookapplication.R;
import com.example.textbookapplication.entity.TextBook;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于home页面的List每一项的展示
 * BaseAdapter是Android中常用的适配器
 */

public class TextbookAdapter extends BaseAdapter {
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    private List<TextBook> textBooks;
    private final LayoutInflater mInflater;
    ImageOptions imageOptions;
    private static final String TAG = "TextbookAdapter";

    public TextbookAdapter(Context context,List<TextBook> list, ImageOptions imageOptions) {
        super();
        mInflater = LayoutInflater.from(context);
        this.textBooks = list;
        this.imageOptions = imageOptions;
    }

    @Override
    public int getCount() { return textBooks.size(); }

    @Override
    public TextBook getItem(int i) { return textBooks.get(i); }

    @Override
    public long getItemId(int i) { return i; }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        Log.i(TAG, "getView: ");
        if (view == null){
            view = mInflater.inflate(R.layout.textbook_item, viewGroup, false);
            holder = new ViewHolder();
            x.view().inject(holder, view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.progressBar.setProgress(0);
        TextBook textBook = getItem(i);
        // 设置图片
        x.image().bind(
                holder.bookPic,
                textBook.getBookPic(),imageOptions,
                new CustomBitmapLoadCallBack(holder)
        );
        holder.bookName.setText(textBook.getBookName());
        holder.bookAuthor.setText(textBook.getAuthor());
        holder.bookPrice.setText("定价："+textBook.getBookPrice()+"元");
        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: "+holder.bookName.getText());
            }
        });
        return view;
    }


    static class ViewHolder{
        @ViewInject(R.id.item_icon)
        public ImageView bookPic;
        @ViewInject(R.id.textbook_name)
        public TextView bookName;
        @ViewInject(R.id.textbook_author)
        public TextView bookAuthor;
        @ViewInject(R.id.textbook_price)
        public TextView bookPrice;
        @ViewInject(R.id.buy)
        public ImageView buy;
        @ViewInject(R.id.img_pb)
        public ProgressBar progressBar;
    }
}
class CustomBitmapLoadCallBack implements org.xutils.common.Callback.ProgressCallback<Drawable> {
    private final TextbookAdapter.ViewHolder holder;
    public CustomBitmapLoadCallBack(TextbookAdapter.ViewHolder holder) {
        this.holder = holder;
    }
    @Override
    public void onWaiting() {
        this.holder.progressBar.setProgress(0);
    }
    @Override
    public void onStarted() {}
    @Override
    public void onLoading(long total, long current, boolean isDownloading) {
        if (total > 0) {
            this.holder.progressBar.setProgress((int) (current * 100 / total));
        }
    }
    @Override
    public void onSuccess(Drawable result) {
        this.holder.progressBar.setProgress(100);
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
}