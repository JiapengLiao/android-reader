package com.ljp.android_reader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ljp.android_reader.bean.Book;
import com.ljp.android_reader.dao.BookDao;
import com.ljp.android_reader.database.AppDatabase;

import java.util.List;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Button add, del, query;

        add = findViewById(R.id.add);
        del = findViewById(R.id.del);
        query = findViewById(R.id.query);

        //书架数据库
        AppDatabase db_book = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "book.db").build();
        BookDao bookDao = db_book.bookDao();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        User user1 = new User(1, "ljp", "12ddddv3");
//                        User user2 = new User(2, "ppp", "123333333333");
//                        User user3 = new User(3, "acd", "123adafa");
//                        userDao.insertAll(user1, user2, user3);

                        Book book1 = new Book("1","11","111", true);
                        Book book2 = new Book("2", "22", "222", true);
                        bookDao.insert(book1, book2);

                        Log.e("onClick: ", "添加");
                    }
                }).start();
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("onClick: ", "删除");
            }
        });

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        List<User> users = userDao.getAll();
                        Book book = bookDao.getBookByBookId(39);
//                        Log.e("onClick: ", "查询" + users.get(0).getFirstName());
//                        for (int i = 0; i < books.size(); i++) {
                            Log.e("run: ", "查询" + book);
//                        }
                    }
                }).start();
            }
        });
    }
}