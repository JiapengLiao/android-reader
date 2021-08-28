package com.ljp.android_reader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljp.android_reader.R;
import com.ljp.android_reader.bean.Book;

import java.util.List;

public class BookListAdapter extends BaseAdapter {
    private List<Book> data;
    private Context mContext;
    public BookListAdapter(Context mContext, List<Book> data) {
        super();
        this.data = data;
        this.mContext = mContext;
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
        View view = inflater.inflate(R.layout.book_list_item, null);
        ImageView image = view.findViewById(R.id.bookImage);
        TextView name = view.findViewById(R.id.bookName);
        image.setImageResource(data.get(position).getImageId());
        name.setText(data.get(position).getBookName());

        return view;
    }
}
