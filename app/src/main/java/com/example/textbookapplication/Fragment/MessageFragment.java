package com.example.textbookapplication.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.textbookapplication.R;
import com.example.textbookapplication.entity.LoginUser;
import com.example.textbookapplication.entity.Message;
import com.example.textbookapplication.entity.TextBook;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.message_fragment)
public class MessageFragment extends BaseFragment {
    @ViewInject(R.id.list_view)
    private ListView listView;

    private Context context;
    private MessListAdapter messListAdapter;
    private ArrayList<Message> arrayList;
    private static final String TAG = "MessageFragment";
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        arrayList = new ArrayList<>();
        messListAdapter = new MessListAdapter(arrayList,context);
        listView.setAdapter(messListAdapter);
        getMessData();
        messListAdapter.notifyDataSetChanged();
    }

    private void getMessData(){
        //https://www.lijinzhou.top:2020/Messages?pagecount=0&size=30&user_no=20160750
        RequestParams params = new RequestParams("https://www.lijinzhou.top:2020/Messages");
        params.addQueryStringParameter("pagecount", 0);
        params.addQueryStringParameter("size", 30);
        params.addQueryStringParameter("user_no",LoginUser.getLoginUser(context).getUserNo());
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(result);
                    String content = jsonObject.getString("content");
                    JSONArray jsonArray = new JSONArray(content);
                    for (int i = 0;i<jsonArray.length();i++){
                        jsonObject = jsonArray.getJSONObject(i);
                        Integer messNo = jsonObject.getInt("messNo");
                        JSONObject userJson = jsonObject.getJSONObject("user");
                        Integer userNo = userJson.getInt("userNo");
                        String userId = userJson.getString("userId");
                        Boolean isAdmin = userJson.getBoolean("isAdmin");
                        String userPassword = userJson.getString("userPassword");
                        String userIconPath = userJson.getString("userIconPath");
                        String userName = userJson.getString("userName");
                        LoginUser user = new LoginUser(userNo,userId,isAdmin,userPassword,userIconPath,userName);
                        String startTime =  jsonObject.getString("startTime");
                        String Messcontent = jsonObject.getString("content");
                        Message message = new Message(messNo,user,startTime,Messcontent);
                        arrayList.add(message);
                    }
                    messListAdapter.notifyDataSetChanged();
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

class MessListAdapter extends BaseAdapter {
    private final LayoutInflater mInflater;
    private List<Message> MessList;

    public MessListAdapter(ArrayList<Message> arrayList,Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.MessList = arrayList;
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
        if (view == null){
            view = mInflater.inflate(R.layout.message_item,viewGroup,false);
            messItemHolder = new MessItemHolder();
            x.view().inject(messItemHolder,view);
            view.setTag(messItemHolder);
        }else {
            messItemHolder =(MessItemHolder) view.getTag();
        }
        Message newMess = getItem(i);
        messItemHolder.messContent.setText(newMess.getContent());
        return view;
    }
    @ContentView(R.layout.message_item)
    private class MessItemHolder{
//        @ViewInject(R.id.mess_pic)
//        private ImageView messPic;
        @ViewInject(R.id.mess_title)
        private TextView messTitle;
        @ViewInject(R.id.mess_content)
        private TextView messContent;
    }
}

