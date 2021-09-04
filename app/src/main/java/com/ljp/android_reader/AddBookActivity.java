package com.ljp.android_reader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ljp.android_reader.adapter.MapperBookAdapter;
import com.ljp.android_reader.bean.Book;
import com.ljp.android_reader.dao.BookDao;
import com.ljp.android_reader.database.AppDatabase;

public class AddBookActivity extends AppCompatActivity {
    private Spinner addBook;
    private ListView scanBook;
    private MapperBookAdapter scanBookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        Toolbar addBookToolBar = findViewById(R.id.addBookToolBar);
        setSupportActionBar(addBookToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.add_book, ScannerBookFragment.class, null)
                .commit();

        addBook = findViewById(R.id.spinnerAddBook);
        addBook.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(AddBookActivity.this, Integer.toString(position), Toast.LENGTH_SHORT).show();
                switch (position){
                    case 0:
                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.add_book, ScannerBookFragment.class, null)
                                .commit();
                        break;

                    case 1:
                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.add_book, PhoneMenuFragment.class, null)
                                .commit();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//                Toast.makeText(AddBookActivity.this, "什么也没选", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 复选框监听事件
     * */
    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        /**
         * 1.item
         * 2.ListView
         * 3.ListView的父亲布局
         * */
        View parent = (View) view.getParent().getParent().getParent();
        TextView tvCount = parent.findViewById(R.id.tv2);

        /**
         * 书架数据库
         *
         * */
        //书架数据库
        AppDatabase db_book = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "book.db").build();
        BookDao bookDao = db_book.bookDao();

        int count = Integer.parseInt(tvCount.getText().toString());
        if (checked){
            //原本是未选中
            tvCount.setText(Integer.toString(count+1));
//            Log.e("onCheckboxClicked: ", view.getTag().toString());

            /**
             * 选中状态会将书籍消息加入数据库，点击添加到书架按钮会将书架状态改为true
             * */
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int id = Integer.parseInt(view.getTag().toString());
                    Book book = bookDao.getBookByBookId(id);
                    if (book == null){
                        // 数据库没有这本书，要将这本书加入数据库
                        Cursor c = null;

                        ContentResolver mContentResolver = getApplicationContext().getContentResolver();
                        c = mContentResolver.query(MediaStore.Files.getContentUri("external"), null, null, null, null);

                        int columnIndexOrThrow_ID = c.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID);
                        int columnIndexOrThrow_NAME = c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME);

                        while(c.moveToNext()){
                            String bookId = c.getString(columnIndexOrThrow_ID);

                            if (Integer.parseInt(bookId) == id){
                                String bookName = c.getString(columnIndexOrThrow_NAME);
                                int position_do = bookName.lastIndexOf(".");
                                String firstName, lastName;
                                if (position_do == -1){
                                    continue;
                                }
                                else{
                                    lastName = bookName.substring(position_do + 1);
                                    firstName = bookName.substring(0, position_do);
                                }

                                Book book1 = new Book(bookId, firstName, lastName, false);
                                bookDao.insert(book1);

                                break;
                            }
                        }
                    }
                    else {
                        //数据库有这本书，暂时不做处理
//                        Log.e("123", "getFilesByType: " + book.getBookName());
                    }
                }
            }).start();
        }
        else {
            //由选中变为未选中
            tvCount.setText(Integer.toString(count-1));
        }
    }
}