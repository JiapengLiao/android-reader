package com.ljp.android_reader.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ljp.android_reader.R;
import com.ljp.android_reader.adapter.ReadPagerSlideAdapter;
import com.ljp.android_reader.bean.Other;
import com.ljp.android_reader.fragment.PageFragment;
import com.ljp.android_reader.funtions.FileOperation;

public class ReadActivity extends FragmentActivity {
    private int NUM_PAGES = 2;
    private ViewPager2 viewPager;
    private ReadPagerSlideAdapter pagerAdapter;

    public void changeScreen(Other other, int id){
        if (other.getBytesForReadActivity().length == 0){
            other.setBytesForReadActivity(new FileOperation().getStringById(id, 0, other.getLengthForReadActivity(), getApplicationContext()));
        }
        String str = new String(other.getBytesForReadActivity(), 0, other.getLengthForReadActivity());
        pagerAdapter.setStr(str);
        pagerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        Intent main = getIntent();
        int id = main.getIntExtra("bookId", 2);

        Other other = new Other();
        other.setLengthForReadActivity(40000);
        other.setBytesForReadActivity(new byte[0]);

        viewPager = findViewById(R.id.read_pager);
        pagerAdapter = new ReadPagerSlideAdapter(this, NUM_PAGES);
        this.changeScreen(other, id);
        viewPager.setAdapter(pagerAdapter);

//        viewPager.setCurrentItem(5);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                pagerAdapter.setCount(viewPager.getCurrentItem() + 2);
                pagerAdapter.notifyDataSetChanged();

//                Class cl = viewPager.getChildAt(0).getClass();
//                try {
//                    Object ob = cl.newInstance();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (InstantiationException e) {
//                    e.printStackTrace();
//                }
//                Log.e("onPageSelected: ", cl.toString());

                changeScreen(other, id);
//                int j = 0;
//                while (b.length >= 0){
//                    int len = 300 - 3 * j;
//                    j++;
//                    String str = new String(b, 0, len);
//                    byte[] b1 = new byte[len];
//                    System.arraycopy(b, (i+1)*len, b1, 0, length-((i+1)*len));
//                    b = b1;
//                    Log.e("onCreate: ", b.length + "\r\n" + str);
//                }
//                Log.e("onPageSelected: ", Integer.toString(position));
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                //Log.e("onPageScrolled: ", position + "  " + positionOffset  + "  " + positionOffsetPixels);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
//                Log.e("onPageScrollStateChanged: ", Integer.toString(state));
            }
        });

        pagerAdapter.notifyDataSetChanged();

        int length = 10000;
        byte[] b = new FileOperation().getStringById(id, 0, length, getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}