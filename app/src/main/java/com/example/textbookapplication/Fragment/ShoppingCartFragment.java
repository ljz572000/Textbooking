package com.example.textbookapplication.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.textbookapplication.R;
import com.example.textbookapplication.entity.LoginUser;
import com.example.textbookapplication.entity.ShoppingCart;
import com.example.textbookapplication.entity.TextBook;

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

@ContentView(R.layout.shopping_cart_fragment)
public class ShoppingCartFragment extends BaseFragment {
    @ViewInject(R.id.list_view)
    private ListView listView;

    private Context context;
    private  ArrayList<ShoppingCart> shoppingCarts;
    private ShoppingListAdapter shoppingListAdapter;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        shoppingCarts = new ArrayList<>();
        shoppingListAdapter = new ShoppingListAdapter(context, shoppingCarts);
        listView.setAdapter(shoppingListAdapter);
        getShoppingCart();
    }

    private Integer getLoginUser(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        final String userinfo = sharedPreferences.getString("User","");
        JSONObject jsonObject = null;
        Integer userId = null;
        try {
            jsonObject = new JSONObject(userinfo);
            userId = jsonObject.getInt("userId");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return userId;
    }
    private void getShoppingCart(){
        //https://www.lijinzhou.top:2020/ShoppingCarts?pagecount=0&size=5&userNo=20160750
        RequestParams params = new RequestParams("https://www.lijinzhou.top:2020/ShoppingCarts");
        params.addQueryStringParameter("pagecount", 0);
        params.addQueryStringParameter("size", 30);
        params.addQueryStringParameter("userNo", getLoginUser());
        x.http().get(params, new Callback.CommonCallback<String>(){
            @Override
            public void onSuccess(String result) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(result);
                    String content = jsonObject.getString("content");
                    JSONArray jsonArray = new JSONArray(content);
                    for (int i = 0;i<jsonArray.length();i++){
                        jsonObject = jsonArray.getJSONObject(i);
                        Integer shoppingCartNo = jsonObject.getInt("shoppingCartNo");

                        JSONObject userJson = jsonObject.getJSONObject("user");
                        Integer userId = userJson.getInt("userId");
                        Boolean isAdmin = userJson.getBoolean("isAdmin");
                        String userPassword = userJson.getString("userPassword");
                        String userIconPath = userJson.getString("userIconPath");
                        String userName = userJson.getString("userName");
                        LoginUser user = new LoginUser(userId,isAdmin,userPassword,userIconPath,userName);

                        JSONObject bookJson = jsonObject.getJSONObject("book");
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

                        ShoppingCart shoppingCart = new ShoppingCart(shoppingCartNo,user,textBook,bookNum,bookValues,startTime);

                        shoppingCarts.add(shoppingCart);
                    }
                    shoppingListAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

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

class ShoppingListAdapter extends BaseAdapter {
    public ShoppingListAdapter(Context context, ArrayList<ShoppingCart> shoppingCarts) {
        this.mInflater = LayoutInflater.from(context);
        this.shoppingCarts = shoppingCarts;
    }

    private final LayoutInflater mInflater;
    private List<ShoppingCart> shoppingCarts;
    private ImageOptions imageOptions = new ImageOptions.Builder()
            .setLoadingDrawableId(R.mipmap.ic_launcher)
            .setFailureDrawableId(R.mipmap.ic_launcher).build();
    private static final String TAG = "ShoppingListAdapter";
    @Override
    public int getCount() {
        return shoppingCarts.size();
    }

    @Override
    public ShoppingCart getItem(int i) {
        return shoppingCarts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ShoppingItemHolder shoppingItemHolder;
        if (view == null) {
            view = mInflater.inflate(R.layout.shopping_item, viewGroup, false);
            shoppingItemHolder = new ShoppingItemHolder();
            x.view().inject(shoppingItemHolder, view);
            view.setTag(shoppingItemHolder);
        } else {
            shoppingItemHolder = (ShoppingItemHolder) view.getTag();
        }
        ShoppingCart shoppingCart = getItem(i);
        x.image().bind(shoppingItemHolder.shoppingPic, shoppingCart.getBook().getBookPic(), imageOptions);
        shoppingItemHolder.textbook_name.setText(shoppingCart.getBook().getBookName());
        shoppingItemHolder.textbook_num.setText("数量： "+shoppingCart.getBookNum());
        shoppingItemHolder.book_values.setText("总价： "+shoppingCart.getBookValues());
        shoppingItemHolder.buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: "+"立即购买");
            }
        });
        return view;
    }

    @ContentView(R.layout.shopping_item)
    private class ShoppingItemHolder {
        @ViewInject(R.id.item_icon)
        private ImageView shoppingPic;
        @ViewInject(R.id.textbook_name)
        private TextView textbook_name;
        @ViewInject(R.id.textbook_num)
        private TextView textbook_num;
        @ViewInject(R.id.book_values)
        private TextView book_values;
        @ViewInject(R.id.buy_now)
        private Button buy_now;
    }
}
