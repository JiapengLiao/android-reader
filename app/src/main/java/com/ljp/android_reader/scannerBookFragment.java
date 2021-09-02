package com.ljp.android_reader;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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


        List<Book> data = new ArrayList<>();
        data.add(new Book("朝花夕拾", "TXT"));
        data.add(new Book("道德经", "TXT"));
        data.add(new Book("钢铁是怎样炼成的", "PDF"));
        data.add(new Book("老人与海", "EPUB"));
        data.add(new Book("朝花夕拾", "TXT"));
        data.add(new Book("道德经", "TXT"));
        data.add(new Book("钢铁是怎样炼成的", "PDF"));
        data.add(new Book("老人与海", "EPUB"));
        data.add(new Book("朝花夕拾", "TXT"));
        data.add(new Book("道德经", "TXT"));
        data.add(new Book("钢铁是怎样炼成的", "PDF"));
        data.add(new Book("老人与海", "EPUB"));
        data.add(new Book("朝花夕拾", "TXT"));
        data.add(new Book("道德经", "TXT"));
        data.add(new Book("钢铁是怎样炼成的", "PDF"));
        data.add(new Book("老人与海", "EPUB"));

        ListView scanBook = view.findViewById(R.id.scanBook);
        MapperBookAdapter scanBookAdapter = new MapperBookAdapter(getContext(), data);
        scanBook.setAdapter(scanBookAdapter);
        // Inflate the layout for this fragment
        return view;
    }
}