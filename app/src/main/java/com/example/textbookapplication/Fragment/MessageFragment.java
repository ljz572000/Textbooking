package com.example.textbookapplication.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.textbookapplication.R;
import com.example.textbookapplication.entity.LoginUser;
import com.example.textbookapplication.entity.Message;
import com.example.textbookapplication.widget.pullToRefresh.PullToRefreshLayout;
import com.example.textbookapplication.widget.pullToRefresh.PullableListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ContentView(R.layout.message_fragment)
public class MessageFragment extends BaseFragment {
    @ViewInject(R.id.message_refresh_view)
    private PullToRefreshLayout refresh_view;
    @ViewInject(R.id.message_content_view)
    private PullableListView content_view;

    private Context context;
    private MessListAdapter messListAdapter;
    private ArrayList<Message> messageArrayList;
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
        messageArrayList = new ArrayList<>();
        page_count = 0;
        isloadmore = false;
        messListAdapter = new MessListAdapter(messageArrayList, context,activity);
        content_view.setAdapter(messListAdapter);
        messageArrayList.clear();
        getMessData();
        messListAdapter.notifyDataSetChanged();
    }

    private void getNext() {
        isloadmore = true;
        getMessData();
    }

    private void getMessData() {
        //https://www.lijinzhou.top:2020/Messages?pagecount=0&size=30&user_no=20160750
        RequestParams params = new RequestParams("https://www.lijinzhou.top:2020/Messages");
        params.addQueryStringParameter("pagecount", 0);
        params.addQueryStringParameter("size", 30);
        params.addQueryStringParameter("user_no", LoginUser.getLoginUser(context).getUserNo());
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(result);
                    String content = jsonObject.getString("content");
                    if (content.equals("[]")) {
                        if (isloadmore) {
                            refresh_view.loadmoreFinish(PullToRefreshLayout.FAIL);
                        } else {
                            refresh_view.loadmoreFinish(PullToRefreshLayout.FAIL);
                        }
                        activity.runOnUiThread(() -> Toast.makeText(context, "到底了", Toast.LENGTH_SHORT).show());
                    }
                    JSONArray jsonArray = new JSONArray(content);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        Integer messNo = jsonObject.getInt("messNo");
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

                        String startTime = jsonObject.getString("startTime");
                        String Messcontent = jsonObject.getString("content");
                        Message message = new Message(messNo, user, startTime, Messcontent);
                        messageArrayList.add(message);
                    }
                    page_count += 1;
                    messListAdapter.notifyDataSetChanged();
                    if (isloadmore) {
                        refresh_view.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                    } else {
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

class MessListAdapter extends BaseAdapter {
    private final LayoutInflater mInflater;
    private List<Message> MessList;
    private Context context;
    private Activity activity;
    public MessListAdapter(ArrayList<Message> arrayList, Context context,Activity activity) {
        this.mInflater = LayoutInflater.from(context);
        this.MessList = arrayList;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return MessList.size();
    }

    @Override
    public Message getItem(int i) {
        return MessList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MessItemHolder messItemHolder;
        if (view == null) {
            view = mInflater.inflate(R.layout.message_item, viewGroup, false);
            messItemHolder = new MessItemHolder();
            x.view().inject(messItemHolder, view);
            view.setTag(messItemHolder);
        } else {
            messItemHolder = (MessItemHolder) view.getTag();
        }
        Message newMess = getItem(i);
        messItemHolder.messContent.setText(newMess.getContent());
        messItemHolder.delete_message.setOnClickListener(v->{
            final AlertDialog.Builder normalDialog =
                    new AlertDialog.Builder(context);
            normalDialog.setTitle("是否删除");
            normalDialog.setMessage("你确定要删除");
            normalDialog.setPositiveButton("确定",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteMess(newMess.getMessNo());
                        }
                    });
            normalDialog.setNegativeButton("关闭",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
            normalDialog.show();
        });
        return view;
    }

    private void deleteMess(Integer messNo)
    {
        //https://www.lijinzhou.top:2020/DeleteMessage?mess_no=2
        RequestParams params = new RequestParams("https://www.lijinzhou.top:2020/DeleteMessage");
        params.addQueryStringParameter("mess_no", messNo);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                activity.runOnUiThread(()->Toast.makeText(context,result,Toast.LENGTH_SHORT).show());
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


    @ContentView(R.layout.message_item)
    private class MessItemHolder {
        //        @ViewInject(R.id.mess_pic)
//        private ImageView messPic;
        @ViewInject(R.id.mess_title)
        private TextView messTitle;
        @ViewInject(R.id.mess_content)
        private TextView messContent;
        @ViewInject(R.id.delete_message)
        private Button delete_message;
    }
}

