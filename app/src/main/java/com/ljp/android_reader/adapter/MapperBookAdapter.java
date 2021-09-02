package com.ljp.android_reader.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.ljp.android_reader.R;
import com.ljp.android_reader.bean.Book;

import java.util.List;

public class MapperBookAdapter extends BaseAdapter {
    private Context mContext;
    private List<Book> data;

    public MapperBookAdapter(Context mContext, List<Book> data) {
        this.mContext = mContext;
        this.data = data;
    }

    public MapperBookAdapter() {
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.mapper_book_item, null);
        TextView bookName = view.findViewById(R.id.book_name);
        TextView bookType = view.findViewById(R.id.book_type);
        bookName.setText(data.get(position).getBookName());
        bookType.setText(data.get(position).getType());
        return view;

//        TextView tv = new TextView(mContext);
//        tv.setText("你好，世界！！！");
//        tv.setTextColor(Color.RED);
//        return tv;
    }
}
