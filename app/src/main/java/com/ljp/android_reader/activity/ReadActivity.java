package com.ljp.android_reader.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ljp.android_reader.R;
import com.ljp.android_reader.adapter.ReadPagerSlideAdapter;
import com.ljp.android_reader.bean.ContentView;
import com.ljp.android_reader.bean.Other;
import com.ljp.android_reader.fragment.PageFragment;
import com.ljp.android_reader.funtions.FileOperation;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ReadActivity extends FragmentActivity {
    private int NUM_PAGES;
    private ViewPager2 viewPager;
    private ReadPagerSlideAdapter pagerAdapter;
    private RandomAccessFile raf;
    private ContentView cv;
    byte[] b;
    private int length;
    private int pos;

    public void changeScreen(int pos){
//        if (b.length == 0){
//            b = new byte[length];
//            try {
//                raf.read(b);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        String str = null;
        try {
            int prevPointer = (int)raf.getFilePointer();
            raf.read(b);
            str = new String(b);
            raf.seek(pos + prevPointer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        pagerAdapter.setStr(str);
        pagerAdapter.notifyDataSetChanged();
//        Log.e("changeScreen: ",  pos + "");

//        byte[] b1 = new byte[b.length - pos];
//        Log.e("changeScreen: ", pos + "sb");
//        System.arraycopy(b, pos, b1, 0, b.length - pos);
//
//        b = b1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        Intent main = getIntent();
        int id = main.getIntExtra("bookId", 2);
        raf = new FileOperation().getRandomAccessFileById(id, getApplicationContext());
        length = 40000;
        NUM_PAGES = 10;
        b = new byte[length];
        viewPager = findViewById(R.id.read_pager);
        pagerAdapter = new ReadPagerSlideAdapter(this, NUM_PAGES);


        try {
            raf.read(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = new String(b);
        pagerAdapter.setStr(str);
        viewPager.setAdapter(pagerAdapter);

        RecyclerView rv = (RecyclerView) viewPager.getChildAt(0);
        rv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                View view = rv.getLayoutManager().findViewByPosition(0);
                cv = view.findViewById(R.id.content);

                pagerAdapter.setCount(10);
                pagerAdapter.notifyDataSetChanged();

//                Log.e("onGlobalLayout: ", cv.getTotalWord() + "");
            }
        });


        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

//                if (position == 0){
//                    pos = 0;
//                    while (pos < 20){
//                        try {
//                            raf.read(b);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                        if (b == null){
//                            break;
//                        }
//
//                        String str = new String(b);
//                        pagerAdapter.setStr(str);

//                        RecyclerView rv = (RecyclerView) viewPager.getChildAt(0);
//                        View view = rv.getLayoutManager().findViewByPosition(pos-1);
//                        cv = view.findViewById(R.id.content);
//
//                        View viewPos = rv.getLayoutManager().findViewByPosition(pos);
//                        ContentView cvPos = viewPos.findViewById(R.id.content);
//                        if (pos == 0){
//                            cvPos.setTag(cvPos.getTotalWord());
//                        }
//                        else{
//
//                            View viewPrev = rv.getLayoutManager().findViewByPosition(pos - 1);
//                            ContentView cvPrev = viewPos.findViewById(R.id.content);
//
//                            cvPos.setTag(cvPos.getTotalWord() + (int)cvPrev.getTag());
//                        }
//                        try {
//                            raf.seek((long)((int)cvPos.getTag()));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                        pos++;
//
//                        pagerAdapter.setCount(pagerAdapter.getCount() + 1);
//                        pagerAdapter.notifyDataSetChanged();
//                    }
//                }

//                RecyclerView rv = (RecyclerView) viewPager.getChildAt(0);
//                View view = rv.getLayoutManager().findViewByPosition(position);
//                cv = view.findViewById(R.id.content);
//
//                changeScreen(cv.getTotalWord());
//                try {
//                    Log.e("onPageSelected: ", (int)raf.getFilePointer() + "");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

//                pagerAdapter.setCount(viewPager.getCurrentItem() + 2);
//                pagerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

//        RecyclerView rv = (RecyclerView) viewPager.getChildAt(0);
//        View view = rv.getLayoutManager().findViewByPosition(0);
//        cv = view.findViewById(R.id.content);
    }
}