package com.ljp.android_reader.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ljp.android_reader.bean.Book;
import com.ljp.android_reader.dao.BookDao;

@Database(entities = {Book.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BookDao bookDao();
}
