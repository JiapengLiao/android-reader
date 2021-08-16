package com.ljp.android_reader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainer;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    View fragmentView;//装fragment的容器
    BottomNavigationView bnv;//底部导航栏组件

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
                System.out.println("11111111111111111111111111111111111");
                return true;
            case R.id.navigation_shelf:
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_view, BookShelfFragment.class, null)
                        .commit();
                System.out.println("222222222222222222222222222");
                return true;
            case R.id.navigation_user:
                System.out.println("33333333333333333333333333333333");
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_view, UserFragment.class, null)
                        .commit();
                return true;
        }
        return false;
    };
}