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

import androidx.fragment.app.Fragment;

import com.example.textbookapplication.R;

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

    private ShoppingListAdapter shoppingListAdapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            arrayList.add("1");
        }
        shoppingListAdapter = new ShoppingListAdapter(context, arrayList);
        listView.setAdapter(shoppingListAdapter);
    }
}

class ShoppingListAdapter extends BaseAdapter {
    public ShoppingListAdapter(Context context, List<String> fruitList) {
        this.mInflater = LayoutInflater.from(context);
        this.fruitList = fruitList;
    }

    private final LayoutInflater mInflater;
    private List<String> fruitList;

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
        ShoppingItemHolder shoppingItemHolder;
        if (view == null) {
            view = mInflater.inflate(R.layout.shopping_item, viewGroup, false);
            shoppingItemHolder = new ShoppingItemHolder();
            x.view().inject(shoppingItemHolder, view);
            view.setTag(shoppingItemHolder);
        } else {
            shoppingItemHolder = (ShoppingItemHolder) view.getTag();
        }
        //...
        //...
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
        private TextView buy_now;
    }
}
