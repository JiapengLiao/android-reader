package com.ljp.android_reader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentContainer;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ljp.android_reader.adapter.BookListAdapter;
import com.ljp.android_reader.bean.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private View fragmentView;//装fragment的容器
    private BottomNavigationView bnv;//底部导航栏组件
    private SearchView sv;//搜索框
    private ListView lv;//搜索结果
    private GridView bookList;//书架所有书籍
    private ImageView bookImage;//书籍封面
    private TextView bookName;//书籍名字
    BaseAdapter bookListAdapter;

    @Override
    protected void onStart() {
        super.onStart();

        //OnCreate时还无法相互获取组件
        sv = findViewById(R.id.searchView);

        //修改搜索框字体样式
        int idSearch = sv.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView tvSearch = (TextView) findViewById(idSearch);
        tvSearch.setTextColor(Color.WHITE);

//        lv = findViewById(R.id.listView);
//        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, str);
//        lv.setAdapter(adapter1);
//        lv.setTextFilterEnabled(true);

        //设置搜索文本监听
//        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            //点击时触发
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
////            文本改变时触发
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                if (!TextUtils.isEmpty(newText)){
//                    lv.setFilterText(newText);
//                }
//                else {
//                    lv.clearTextFilter();
//                }
//                return false;
//            }
//        });

        List<Book> data = new ArrayList<>();
        data.add(new Book(R.drawable.wzry01, "朝花夕拾"));
        data.add(new Book(R.drawable.wzry02, "道德经"));
        data.add(new Book(R.drawable.wzry03, "钢铁是怎样炼成的"));
        data.add(new Book(R.drawable.wzry01, "老人与海"));
        data.add(new Book(R.drawable.wzry02, "斗破苍穹"));
        bookListAdapter = new BookListAdapter(getApplicationContext(), data);
        bookList = findViewById(R.id.bookList);
        bookList.setAdapter(bookListAdapter);

        //设置书籍显示格式
        bookImage = (ImageView) findViewById(R.id.bookImage);
        int parentWidth = bookList.getWidth();
        int bookWidth = (parentWidth - 20*2)/3;
//        bookImage.setMaxWidth(bookWidth);
//        bookImage.setMaxHeight((int)(bookWidth * 0.64 + 0.5));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        getSupportActionBar().hide();//隐藏标题栏
        setContentView(R.layout.activity_main);

        bnv = findViewById(R.id.bottomMenu);
        bnv.setOnNavigationItemSelectedListener(menuItemClick);
    }

    //底部导航点击监听
    BottomNavigationView.OnNavigationItemSelectedListener menuItemClick = item -> {
        fragmentView = findViewById(R.id.fragment_view);
        switch (item.getItemId()){
            case R.id.navigation_market:
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_view, BookMarketFragment.class, null)
                        .commit();
                return true;
            case R.id.navigation_shelf:
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_view, BookShelfFragment.class, null)
                        .commit();
                return true;
            case R.id.navigation_user:
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_view, UserFragment.class, null)
                        .commit();
                return true;
        }
        return false;
    };
}