package com.ljp.android_reader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentContainer;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private View fragmentView;//装fragment的容器
    private BottomNavigationView bnv;//底部导航栏组件
    private SearchView sv;//搜索框
    private String[] str = {"廖家鹏","廖国熊","廖哲浩","李彪","梁政","凌贤淼"};
    private ListView lv;//搜索结果
    ArrayAdapter<String> adapter1;

    @Override
    protected void onStart() {
        super.onStart();

        //OnCreate时还无法相互获取组件
        sv = findViewById(R.id.searchView);
        lv = findViewById(R.id.listView);
        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, str);
        lv.setAdapter(adapter1);
        lv.setTextFilterEnabled(true);

        //设置搜索文本监听
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //点击时触发
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

//            文本改变时触发
            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)){
                    lv.setFilterText(newText);
                }
                else {
                    lv.clearTextFilter();
                }
                return false;
            }
        });
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