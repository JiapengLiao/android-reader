package com.ljp.android_reader.fragment;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.ljp.android_reader.activity.MainActivity;
import com.ljp.android_reader.R;
import com.ljp.android_reader.adapter.MapperBookAdapter;
import com.ljp.android_reader.bean.Book;
import com.ljp.android_reader.dao.BookDao;
import com.ljp.android_reader.database.AppDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScannerBookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScannerBookFragment extends Fragment {
    private static final String TAG = "scannerBookFragment";
    CheckBox checkBox;
    public TextView allChoose, chooseCount;
    ListView scanBook;
    Button btnAddToShelf;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ScannerBookFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment scannerBookFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScannerBookFragment newInstance(String param1, String param2) {
        ScannerBookFragment fragment = new ScannerBookFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scanner_book, container, false);
        scanBook = view.findViewById(R.id.scanBook);
        allChoose = view.findViewById(R.id.tv4);
        chooseCount = view.findViewById(R.id.tv2);
        MapperBookAdapter scanBookAdapter = new MapperBookAdapter(getContext(), getFilesByType(getContext()));
        scanBook.setAdapter(scanBookAdapter);
        /**
         * 书架数据库
         *
         * */
        //书架数据库
        AppDatabase db_book = Room.databaseBuilder(getContext(), AppDatabase.class, "book.db").build();
        BookDao bookDao = db_book.bookDao();

        /**
         * 全选
         * */
        allChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = allChoose.getText().toString();
                int itemCount = scanBook.getChildCount();
                if (str.equals("全选")){
                    allChoose.setText("全不选");
                    int count = 0;
                    for (int i = 0; i < itemCount; i++) {
                        View view1 = scanBook.getChildAt(i);
                        checkBox = view1.findViewById(R.id.cb_choose);
                        if(checkBox.getVisibility() == View.VISIBLE){
                            checkBox.setChecked(true);
                            count++;
                        }
                    }
                    chooseCount.setText(Integer.toString(count));
                }
                if (str.equals("全不选")){
                    allChoose.setText("全选");
                    chooseCount.setText("0");
                    for (int i = 0; i < itemCount; i++) {
                        View view1 = scanBook.getChildAt(i);
                        checkBox = view1.findViewById(R.id.cb_choose);
                        checkBox.setChecked(false);
                    }
                }
            }
        });


        /**
         * 添加到书架监听事件
         * */
        btnAddToShelf = view.findViewById(R.id.btn1);
        btnAddToShelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListView lv = view.findViewById(R.id.scanBook);
                int itemCount = lv.getChildCount();
                for (int i = 0; i < itemCount; i++) {
                    View item = lv.getChildAt(i);
                    CheckBox cb = item.findViewById(R.id.cb_choose);
                    if (cb.isChecked()){
                        int id = Integer.parseInt(cb.getTag().toString());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Book book = bookDao.getBookByBookId(id);
                                book.setBookShelf(true);
                                bookDao.updateBooks(book);
                            }
                        }).start();
                    }
                }
                Intent mainActivity = new Intent(getContext(), MainActivity.class);
                startActivity(mainActivity);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    /**
     * 获取文件
     **/
    public static List<Book> getFilesByType(Context context) {
        List<String> needType = new ArrayList<>();
        needType.add("txt");
        needType.add("epub");
        needType.add("pdf");
        List<Book> files = new ArrayList<>();
        // 扫描files文件库
        Cursor c = null;
        try {
            ContentResolver mContentResolver = context.getContentResolver();
            c = mContentResolver.query(MediaStore.Files.getContentUri("external"), null, null, null, null);
//            Log.e("数量", "getFilesByType: " + c.getCount());
            int columnIndexOrThrow_ID = c.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID);
            int columnIndexOrThrow_MIME_TYPE = c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MIME_TYPE);
            int columnIndexOrThrow_DATA = c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA);
            int columnIndexOrThrow_SIZE = c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE);
            int columnIndexOrThrow_NAME = c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME);
            int tempId = 0;
            while (c.moveToNext()) {
                String id = c.getString(columnIndexOrThrow_ID);
                String path = c.getString(columnIndexOrThrow_DATA);
                String minType = c.getString(columnIndexOrThrow_MIME_TYPE);
                String name = c.getString(columnIndexOrThrow_NAME);

                int position_do = name.lastIndexOf(".");
                String fileNameLast, fileNameFirst;
//                Log.e(TAG, "getFilesByType: " + name);
                if (position_do == -1) {
                    continue;
                }
                else {
                    fileNameLast = name.substring(position_do + 1);
                    fileNameFirst = name.substring(0, position_do);
                    for (int i = 0; i < needType.size(); i++) {
                        if (fileNameLast.equals(needType.get(i))){
//                            Log.e(TAG, "getFilesByType: " + name);
                            files.add(new Book(id, fileNameFirst, fileNameLast));
                            /**
                             * 检查数据库，判断书籍是否在书架
                             *
                             * */
                            //书架数据库
//                            AppDatabase db_book = Room.databaseBuilder(context, AppDatabase.class, "book.db").build();
//                            BookDao bookDao = db_book.bookDao();
//                            new Thread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    Book book = bookDao.getBookByBookId(Integer.parseInt(id));
//                                    if (book == null){
//                                        Log.e("123", "getFilesByType: " + "没有此id");
//                                    }
//                                    else {
//                                        Log.e("123", "getFilesByType: " + book.getBookName());
//                                    }
//                                }
//                            }).start();
//                            Log.e("123", "getFilesByType: " + id + " " + name);
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return files;
    }
}