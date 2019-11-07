package com.example.textbookapplication.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;

import com.example.textbookapplication.R;
import com.example.textbookapplication.activity.DetailActivity;
import com.example.textbookapplication.activity.OrderActivity;
import com.example.textbookapplication.entity.LoginUser;
import com.example.textbookapplication.entity.ShoppingCart;
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

@ContentView(R.layout.shopping_cart_fragment)
public class ShoppingCartFragment extends BaseFragment {
    //    @ViewInject(R.id.list_view)
//    private ListView listView;
    @ViewInject(R.id.shopping_cart_refresh_view)
    private PullToRefreshLayout refresh_view;
    @ViewInject(R.id.shopping_cart_content_view)
    private PullableListView content_view;

    private Context context;
    private ArrayList<ShoppingCart> shoppingCarts;
    private ShoppingListAdapter shoppingListAdapter;
    private Activity activity;
    int page_count;
    boolean isloadmore;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        refresh_view.setOnRefreshListener(new MyListener());
        activity = getActivity();
        getFirst();
    }

    private void getFirst() {
        shoppingCarts = new ArrayList<>();
        page_count = 0;
        isloadmore = false;
        shoppingListAdapter = new ShoppingListAdapter(context, shoppingCarts, activity);
        content_view.setAdapter(shoppingListAdapter);
        shoppingCarts.clear();
        getShoppingCart();
    }

    private void getNext() {
        isloadmore=true;
        getShoppingCart();
    }

    private void getShoppingCart() {
        //https://www.lijinzhou.top:2020/ShoppingCarts?pagecount=0&size=5&userNo=20160750
        RequestParams params = new RequestParams("https://www.lijinzhou.top:2020/ShoppingCarts");
        params.addQueryStringParameter("pagecount", page_count);
        params.addQueryStringParameter("size", 30);
        params.addQueryStringParameter("user_no", LoginUser.getLoginUser(context).getUserNo());
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(result);
                    String content = jsonObject.getString("content");
                    if (content.equals("[]")){
                        if(isloadmore){
                            refresh_view.loadmoreFinish(PullToRefreshLayout.FAIL);
                        }else{
                            refresh_view.loadmoreFinish(PullToRefreshLayout.FAIL);
                        }
                        activity.runOnUiThread(()-> Toast.makeText(context,"到底了",Toast.LENGTH_SHORT).show());
                    }
                    JSONArray jsonArray = new JSONArray(content);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        Integer shoppingCartNo = jsonObject.getInt("shoppingCartNo");
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
                        LoginUser user = new LoginUser(userNo, userId, isAdmin, userPassword, userIconPath, userName, money, address, major, mail, user_startTime, birth, isFemale);


                        JSONObject bookJson = jsonObject.getJSONObject("book");
                        Integer bookNo = bookJson.getInt("bookNo");
                        String bookName = bookJson.getString("bookName");
                        String bookPic = bookJson.getString("bookPic");
                        String author = bookJson.getString("author");
                        Double bookPrice = bookJson.getDouble("bookPrice");
                        Integer totalnum = bookJson.getInt("totalnum");
                        TextBook textBook = new TextBook(bookNo, bookName, bookPic, author, bookPrice, totalnum);

                        Integer bookNum = jsonObject.getInt("bookNum");
                        Double bookValues = jsonObject.getDouble("bookValues");
                        String startTime = jsonObject.getString("startTime");

                        ShoppingCart shoppingCart = new ShoppingCart(shoppingCartNo, user, textBook, bookNum, bookValues, startTime);
                        shoppingCarts.add(shoppingCart);
                    }
                    page_count +=1;
                    shoppingListAdapter.notifyDataSetChanged();
                    if(isloadmore){
                        refresh_view.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                    }else{
                        refresh_view.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
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
    }

    class MyListener implements PullToRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            // 下拉刷新操作
            getFirst();
        }

        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
            // 加载操作
            getNext();
        }
    }

}

class ShoppingListAdapter extends BaseAdapter {
    public ShoppingListAdapter(Context context, ArrayList<ShoppingCart> shoppingCarts, Activity activity) {
        this.mInflater = LayoutInflater.from(context);
        this.shoppingCarts = shoppingCarts;
        this.context = context;
        this.activity = activity;
    }

    private Activity activity;
    private final LayoutInflater mInflater;
    private List<ShoppingCart> shoppingCarts;
    private ImageOptions imageOptions = new ImageOptions.Builder()
            .setLoadingDrawableId(R.mipmap.ic_launcher)
            .setFailureDrawableId(R.mipmap.ic_launcher).build();
    //    private static final String TAG = "ShoppingListAdapter";
    private Context context;

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
        shoppingItemHolder.textbook_num.setText("数量： " + shoppingCart.getBookNum());
        shoppingItemHolder.book_values.setText("总价： " + shoppingCart.getBookValues());
        shoppingItemHolder.buy_now.setOnClickListener(view1 -> buynow(
                shoppingCart.getBook().getBookName(),
                shoppingCart.getBookNum(),
                shoppingCart.getBook().getBookNo(),
                shoppingCart.getBook().getBookPrice(), shoppingCart.getUser(),shoppingCart.getShoppingCartNo()
        ));
        shoppingItemHolder.delete_shoppingcart.setOnClickListener(v ->
                delete_shoppingcart(shoppingCart.getBook().getBookName(), shoppingCart.getBookNum(), shoppingCart.getShoppingCartNo()));
        return view;
    }

    private void delete_shoppingcart(String bookName, Integer textNum, Integer shopping_cart_no) {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        normalDialog.setTitle("是否删除");
        normalDialog.setMessage("你确定要删除《" + bookName + "》");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String content = "已从购物车中删除" + textNum + "本" + bookName;
                        sendMessage(content);
                        sendDeleteShoppingCart(shopping_cart_no);
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

    private void sendDeleteShoppingCart(Integer shopping_cart_no) {
        //https://localhost:8080/deleteShoppingCarts?shopping_cart_no=1
        RequestParams params = new RequestParams("https://www.lijinzhou.top:2020/deleteShoppingCarts");
        params.addQueryStringParameter("shopping_cart_no", shopping_cart_no);
        params.addQueryStringParameter("user_no", LoginUser.getLoginUser(context).getUserNo());
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                activity.runOnUiThread(() -> Toast.makeText(context, result, Toast.LENGTH_SHORT).show());
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
    }

    private void buynow(String bookName,
                        Integer textNum,
                        Integer bookNo,
                        Double bookPrice,
                        LoginUser user,Integer shopping_cart_no) {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        normalDialog.setTitle("立即购买");
        normalDialog.setMessage("你确定要购买《" + bookName + "》");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String content = "已购买" + textNum + "本" + bookName;
                        sendMessage(content);
                        buytextbook(bookNo, bookPrice, textNum);
                        updateTextBookNum(bookNo, textNum);
                        updateUserMoney(bookPrice, textNum, user);
                        sendDeleteShoppingCart(shopping_cart_no);
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

    private void sendMessage(String content) {
        RequestParams params = new RequestParams("https://www.lijinzhou.top:2020/NewMessage");
        params.addQueryStringParameter("content", content);
        params.addQueryStringParameter("user_no", LoginUser.getLoginUser(context).getUserNo());
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                activity.runOnUiThread(() -> Toast.makeText(context, content, Toast.LENGTH_SHORT).show());
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
    }

    private void buytextbook(Integer book_no, Double book_price, Integer num) {
        //https://www.lijinzhou.top:2020/AddOrders?book_no=1&book_num=1&book_values=100&user_no=20160750
        Double book_values = book_price * num;
        RequestParams params = new RequestParams("https://www.lijinzhou.top:2020/AddOrders");
        params.addQueryStringParameter("book_no", book_no);
        params.addQueryStringParameter("book_num", num);
        params.addQueryStringParameter("book_values", book_values);
        params.addQueryStringParameter("user_no", LoginUser.getLoginUser(context).getUserNo());
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
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
    }

    private void updateTextBookNum(Integer book_no, Integer num) {
        //https://localhost:8080/UpdateTextBookNum?buyNum=10&bookNo=1
        RequestParams params = new RequestParams("https://www.lijinzhou.top:2020/UpdateTextBookNum");
        params.addQueryStringParameter("buyNum", num);
        params.addQueryStringParameter("bookNo", book_no);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
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
    }

    private void updateUserMoney(Double book_price, Integer num, LoginUser user) {
        Double book_values = book_price * num;
        RequestParams params = new RequestParams("https://www.lijinzhou.top:2020/chargeMoney");
        params.addQueryStringParameter("user_no", user.getUserNo());
        params.addQueryStringParameter("money", user.getMoney() - book_values);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LoginUser.updatePersonMess(user, context);
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
        @ViewInject(R.id.delete_shoppingcart)
        private Button delete_shoppingcart;
    }
}


