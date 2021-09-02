package com.ljp.android_reader;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.ljp.android_reader.adapter.BookListAdapter;
import com.ljp.android_reader.bean.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookShelfFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookShelfFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BookShelfFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookShelfFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookShelfFragment newInstance(String param1, String param2) {
        BookShelfFragment fragment = new BookShelfFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    View view;

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
        List<Book> data = new ArrayList<>();
        data.add(new Book(R.drawable.wzry01, "朝花夕拾"));
        data.add(new Book(R.drawable.wzry02, "道德经"));
        data.add(new Book(R.drawable.wzry03, "钢铁是怎样炼成的"));
        data.add(new Book(R.drawable.wzry01, "老人与海"));
        data.add(new Book(R.drawable.wzry02, "斗破苍穹"));
        ListAdapter bookListAdapter = new BookListAdapter(getContext(), data);

        view = inflater.inflate(R.layout.fragment_book_shelf, container, false);
        TextView tvTest = view.findViewById(R.id.tvBookShelf);
        tvTest.setTextColor(Color.RED);
        GridView bookList = view.findViewById(R.id.bookList);
        bookList.setAdapter(bookListAdapter);

        // Inflate the layout for this fragment
        return view;
    }
}