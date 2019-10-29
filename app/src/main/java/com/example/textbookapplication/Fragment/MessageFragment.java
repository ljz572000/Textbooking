package com.example.textbookapplication.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.textbookapplication.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.message_fragment)
public class MessageFragment extends BaseFragment {
    @ViewInject(R.id.list_view)
    private ListView listView;

    private Context context;
    private MessListAdapter messListAdapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i=0;i<100;i++){
            arrayList.add("1");
        }

        messListAdapter = new MessListAdapter(arrayList,context);
        listView.setAdapter(messListAdapter);
    }
}

class MessListAdapter extends BaseAdapter {
    private final LayoutInflater mInflater;
    private List<String> fruitList;

    public MessListAdapter(ArrayList arrayList,Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.fruitList = arrayList;
    }

    @Override
    public int getCount() {
        return fruitList.size();
    }

    @Override
    public Object getItem(int i) {
        return fruitList.get(i);
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
        //...
        //...
        return view;
    }
    @ContentView(R.layout.message_item)
    private class MessItemHolder{
        @ViewInject(R.id.mess_pic)
        private ImageView messPic;
        @ViewInject(R.id.mess_title)
        private TextView messTitle;
        @ViewInject(R.id.mess_content)
        private TextView messContent;
    }
}

