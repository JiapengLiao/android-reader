package com.ljp.android_reader.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ljp.android_reader.bean.Book;

import java.util.List;

@Dao
public interface BookDao {
    @Insert
    void insert(Book... books);

    @Delete
    void delete(Book... books);

    @Update
    public void updateBooks(Book... books);

    @Query("SELECT * FROM book")
    List<Book> getAll();

    @Query("SELECT * FROM book WHERE book_id in (:bookIds)")
    List<Book> getAllBookByBookIds(int[] bookIds);

    @Query("SELECT * FROM book WHERE book_id = (:bookId)")
    Book getBookByBookId(int bookId);

    @Query("SELECT * FROM book WHERE book_shelf = (:shelf)")
    List<Book> getBooksByShelf(boolean shelf);
}
