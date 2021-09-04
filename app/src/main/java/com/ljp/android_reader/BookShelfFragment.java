package com.ljp.android_reader;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.ljp.android_reader.adapter.BookListAdapter;
import com.ljp.android_reader.bean.Book;
import com.ljp.android_reader.dao.BookDao;
import com.ljp.android_reader.database.AppDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

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
        final List<Book> data = new ArrayList<>();

        //书架数据库
        AppDatabase db_book = Room.databaseBuilder(getContext(), AppDatabase.class, "book.db").build();
        BookDao bookDao = db_book.bookDao();

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Book> books = bookDao.getBooksByShelf(true);
                for (int i = 0; i < books.size(); i++) {
                    data.add(bookDao.getBooksByShelf(true).get(i));
                }
            }
        }).start();

        ListAdapter bookListAdapter = new BookListAdapter(getContext(), data);

        view = inflater.inflate(R.layout.fragment_book_shelf, container, false);
        TextView tvTest = view.findViewById(R.id.tvBookShelf);

        tvTest.setTextColor(Color.RED);
        GridView bookList = view.findViewById(R.id.bookList);
        bookList.setAdapter(bookListAdapter);

        bookList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                CardView cv = view.findViewById(R.id.cv_clear);
                cv.setVisibility(View.VISIBLE);
                View view1 = view;

                cv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        view1.setVisibility(View.INVISIBLE);

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Book book = bookDao.getBookByBookId(Integer.parseInt(view1.getTag().toString()));
                                book.setBookShelf(false);
                                bookDao.updateBooks(book);

                                List<Book> books = bookDao.getBooksByShelf(true);
                                ListAdapter adapter2 = new BookListAdapter(getContext(), books);
                                ((Activity)getContext()).runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        bookList.setAdapter(adapter2);
                                    }
                                });

//                                for (int i = 0; i < books.size(); i++) {
//                                    data.add(bookDao.getBooksByShelf(true).get(i));
//                                }
                            }
                        }).start();
                    }
                });
                return true;
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}