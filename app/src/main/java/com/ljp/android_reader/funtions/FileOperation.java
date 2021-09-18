package com.ljp.android_reader.funtions;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.ljp.android_reader.bean.Book;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class FileOperation {
    /**
     * 根据txt的文件id获取范围内的字符串
     * @param id  书籍id
     * @param start 指针位置，即读取第start+1个字节
     * @param len  读取长度
     * */
    public byte[] getStringById(int id, int start, int len, Context context) {
        Book book = this.getBookInfoById(id, context);
        byte[] b = new byte[len];
        /**
         * 小知识：关于文本长度
         *      每行末尾都有 /r/n 两个字节长度，不是只有空行才有
         * */
        if (book != null){
            try {
                RandomAccessFile raf = new RandomAccessFile(book.getPath(), "r");
                raf.seek(start);//指针跳到第pos个字节，即都第pos+1个字节
                raf.read(b);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  b;
        }
        else {
            return null;
        }
    }

    public RandomAccessFile getRandomAccessFileById(int id, Context context){
        Book book = this.getBookInfoById(id, context);
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(book.getPath(), "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return raf;
    }

    /**
     * 根据id获得书籍类型，没有这id则返回null
     * 只能获取有后缀的文件
     * */
     public Book getBookInfoById(int id, Context context){
         Book book = null;

         Cursor c = null;
         ContentResolver contentResolver = context.getContentResolver();
         c = contentResolver.query(MediaStore.Files.getContentUri("external"), null, null, null, null);

         int columnIndexOrThrow_ID = c.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID);
         int columnIndexOrThrow_NAME = c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME);
         int columnIndexOrThrow_DATA = c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA);

         while (c.moveToNext()){
             String lastName, firstName, path;
             String bookName = c.getString(columnIndexOrThrow_NAME);
             int position_do = bookName.lastIndexOf(".");
             if (position_do == -1){
                 continue;
             }
             else{
                 int bookId = Integer.parseInt(c.getString(columnIndexOrThrow_ID));
                 if (id == bookId){
                     firstName = bookName.substring(0, position_do);
                     lastName = bookName.substring(position_do + 1);
                     path = c.getString(columnIndexOrThrow_DATA);
                     book = new Book(Integer.toString(bookId), firstName, lastName, path);
                 }
             }
         }
         return book;
     }
}
