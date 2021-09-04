package com.ljp.android_reader.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.room.Room;

import com.ljp.android_reader.AddBookActivity;
import com.ljp.android_reader.R;
import com.ljp.android_reader.ScannerBookFragment;
import com.ljp.android_reader.bean.Book;
import com.ljp.android_reader.bean.Other;
import com.ljp.android_reader.dao.BookDao;
import com.ljp.android_reader.database.AppDatabase;

import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

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
        //书架数据库
        AppDatabase db_book = Room.databaseBuilder(mContext, AppDatabase.class, "book.db").build();
        BookDao bookDao = db_book.bookDao();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.mapper_book_item, null);
        TextView bookName = view.findViewById(R.id.book_name);
        TextView bookType = view.findViewById(R.id.book_type);
        CheckBox cb = view.findViewById(R.id.cb_choose);
        TextView tv = view.findViewById(R.id.tv_choose);
        bookName.setText(data.get(position).getBookName());
        bookType.setText(data.get(position).getType());
        cb.setTag(data.get(position).getBookId());
        Other other = new Other();

//        tv.setVisibility(View.VISIBLE);
//        cb.setVisibility(View.INVISIBLE);
//
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (bookDao.getBookByBookId(Integer.parseInt(cb.getTag().toString())) != null){
                    other.setStatus(bookDao.getBookByBookId(Integer.parseInt(cb.getTag().toString())).isBookShelf());
                    ((Activity)mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (other.isStatus()){
                                tv.setVisibility(View.VISIBLE);
                                cb.setVisibility(View.INVISIBLE);
                            }
                            else {
                                tv.setVisibility(View.INVISIBLE);
                                cb.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                }
            }
        }).start();

//        if (other.isStatus()){
//            tv.setVisibility(View.VISIBLE);
//            cb.setVisibility(View.INVISIBLE);
//        }
//        else {
//            tv.setVisibility(View.INVISIBLE);
//            cb.setVisibility(View.VISIBLE);
//        }

        return view;
    }
}
