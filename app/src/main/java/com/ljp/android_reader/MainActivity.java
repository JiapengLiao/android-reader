package com.ljp.android_reader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentContainer;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ljp.android_reader.adapter.BookListAdapter;
import com.ljp.android_reader.bean.Book;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
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

//        List<Book> data = new ArrayList<>();
//        data.add(new Book(R.drawable.wzry01, "朝花夕拾"));
//        data.add(new Book(R.drawable.wzry02, "道德经"));
//        data.add(new Book(R.drawable.wzry03, "钢铁是怎样炼成的"));
//        data.add(new Book(R.drawable.wzry01, "老人与海"));
//        data.add(new Book(R.drawable.wzry02, "斗破苍穹"));
//        bookListAdapter = new BookListAdapter(this, data);
//        bookList = findViewById(R.id.bookList);
//        bookList.setAdapter(bookListAdapter);

    }

    //添加书籍菜单
    public void showPopUp(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.add_book);
        popup.show();
    }
    //点击事件
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.myPhone:
                Toast.makeText(this, "本机书籍", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, AddBookActivity.class);
                startActivity(intent);
                return true;
            case R.id.wlan:
                Toast.makeText(this, "wlan传书", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.myBook:
                Toast.makeText(this, "我的书籍", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
//        getSupportActionBar().hide();//隐藏标题栏
        setContentView(R.layout.activity_main);

        bnv = findViewById(R.id.bottomMenu);
        bnv.setOnNavigationItemSelectedListener(menuItemClick);

        requestMyPermissions();
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

    /**
     * 处理权限
     * */
    private void requestMyPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //没有授权，编写申请权限代码
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        } else {
            Log.d("TAG", "requestMyPermissions: 有写SD权限");
        }
    }
    /**
     * 处理权限结束
     * */
}