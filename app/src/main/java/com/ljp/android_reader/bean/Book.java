package com.ljp.android_reader.bean;

public class Book {
    private int imageId;
    private String bookName;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    public Book(int imageId, String bookName) {
        this.imageId = imageId;
        this.bookName = bookName;
    }

    public Book() {
    }

    public Book(String bookName, String type) {
        this.bookName = bookName;
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
