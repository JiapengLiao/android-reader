package com.ljp.android_reader;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ljp.android_reader.adapter.MapperBookAdapter;
import com.ljp.android_reader.bean.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link scannerBookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class scannerBookFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public scannerBookFragment() {
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
    public static scannerBookFragment newInstance(String param1, String param2) {
        scannerBookFragment fragment = new scannerBookFragment();
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

        ListView scanBook = view.findViewById(R.id.scanBook);
        MapperBookAdapter scanBookAdapter = new MapperBookAdapter(getContext(), getFilesByType(getContext()));
        scanBook.setAdapter(scanBookAdapter);
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
            Log.e("数量", "getFilesByType: " + c.getCount());
            int columnIndexOrThrow_MIME_TYPE = c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MIME_TYPE);
            int columnIndexOrThrow_DATA = c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA);
            int columnIndexOrThrow_SIZE = c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE);
            int columnIndexOrThrow_NAME = c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME);
            int tempId = 0;
            while (c.moveToNext()) {
                String path = c.getString(columnIndexOrThrow_DATA);
                String minType = c.getString(columnIndexOrThrow_MIME_TYPE);
                String name = c.getString(columnIndexOrThrow_NAME);
//                Log.e("FileManager ", "name:" + name + "; type: " + minType);

                int position_do = name.lastIndexOf(".");
                String fileNameLast, fileNameFirst;
                if (position_do == -1) {
                    continue;
                }
                else {
                    fileNameLast = name.substring(position_do + 1);
                    fileNameFirst = name.substring(0, position_do);
//                    Log.e("文件类型", "getFilesByType: " + fileNameFirst);
                    for (int i = 0; i < needType.size(); i++) {
                        if (fileNameLast.equals(needType.get(i))){
//                            Log.e("TAG", "getFilesByType: " + fileNameLast + fileNameFirst);
                            files.add(new Book(fileNameFirst, fileNameLast));
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