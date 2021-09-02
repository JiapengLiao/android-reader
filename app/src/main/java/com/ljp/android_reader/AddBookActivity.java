package com.ljp.android_reader;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ljp.android_reader.adapter.MapperBookAdapter;
import com.ljp.android_reader.bean.Book;

import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

public class AddBookActivity extends AppCompatActivity {
    private Spinner addBook;
    private ListView scanBook;
    private MapperBookAdapter scanBookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        Toolbar addBookToolBar = findViewById(R.id.addBookToolBar);
        setSupportActionBar(addBookToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.add_book, scannerBookFragment.class, null)
                .commit();

        addBook = findViewById(R.id.spinnerAddBook);
        addBook.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(AddBookActivity.this, Integer.toString(position), Toast.LENGTH_SHORT).show();
                switch (position){
                    case 0:
                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.add_book, scannerBookFragment.class, null)
                                .commit();
                        break;

                    case 1:
                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.add_book, phoneMenuFragment.class, null)
                                .commit();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(AddBookActivity.this, "什么也没选", Toast.LENGTH_SHORT).show();
            }
        });
    }
}