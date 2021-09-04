package com.ljp.android_reader.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Book {
    //42 斗破苍穹.txt

    @PrimaryKey
    public int bid;

    @ColumnInfo(name = "book_id")
    public String bookId;


    @ColumnInfo(name = "book_name")
    public String bookName;

    @ColumnInfo(name = "book_type")
    public String type;

    @ColumnInfo(name = "book_shelf")
    public boolean bookShelf;

    private int imageId;

    public Book() {
    }

    @Ignore
    public Book(String bookId, String bookName, String type) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.type = type;
    }

    @Ignore
    public Book(String bookName, String type) {
        this.bookName = bookName;
        this.type = type;
    }

    @Ignore
    public Book(String bookId, String bookName, String type, boolean bookShelf) {
        this.bid = Integer.parseInt(bookId);
        this.bookId = bookId;
        this.bookName = bookName;
        this.type = type;
        this.bookShelf = bookShelf;
    }

    @Ignore
    public Book(int imageId, String bookName) {
        this.imageId = imageId;
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "Book{" +
                ", bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", type='" + type + '\'' +
                ", bookShelf=" + bookShelf +
                '}';
    }

    public boolean isBookShelf() {
        return bookShelf;
    }

    public void setBookShelf(boolean bookShelf) {
        this.bookShelf = bookShelf;
    }

    public String bookId() {
        return bookId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
