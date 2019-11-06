package com.example.textbookapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.textbookapplication.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class OrderActivity extends AppCompatActivity {
    @ViewInject(R.id.orderBack)
    private ImageButton orderBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        x.view().inject(this);
        orderBack.setOnClickListener(view -> finish());
    }
}
