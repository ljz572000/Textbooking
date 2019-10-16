package com.example.textbookapplication.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class MyFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fs;
    public MyFragmentAdapter(FragmentManager fm,List<Fragment> fs) {
        super(fm);
        this.fs = fs;
    }

    @Override
    public Fragment getItem(int position) {
        return fs.get(position);
    }

    @Override
    public int getCount() {
        return fs.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
