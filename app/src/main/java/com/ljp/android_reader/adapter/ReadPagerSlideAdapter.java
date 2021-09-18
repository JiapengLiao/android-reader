package com.ljp.android_reader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ljp.android_reader.fragment.PageFragment;
import com.ljp.android_reader.funtions.FileOperation;

import java.util.zip.Inflater;

public class ReadPagerSlideAdapter extends FragmentStateAdapter {
    int count = 0;
    String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public ReadPagerSlideAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ReadPagerSlideAdapter(@NonNull FragmentActivity fragmentActivity, int count){
        super(fragmentActivity);
        this.count = count;
    }

    private View view;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        PageFragment fragment = new PageFragment("我是第" + position + "个页面", str);
        view = fragment.getView();
        return fragment;
    }

    @Override
    public int getItemCount() {
        return count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
