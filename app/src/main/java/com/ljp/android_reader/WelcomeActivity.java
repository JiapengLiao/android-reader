package com.ljp.android_reader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
    private Intent mainIntent;
    CardView btnSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
//        getSupportActionBar().hide();//隐藏标题栏
        setContentView(R.layout.activity_welcome);

        btnSkip = findViewById(R.id.skip);
        mainIntent = new Intent(this, MainActivity.class);
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mainIntent);
            }
        });

        new CountDownTimer(4000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                TextView tv = findViewById(R.id.skipText);
                tv.setText("跳过 " + millisUntilFinished/1000);
            }
            @Override
            public void onFinish() {
                startActivity(mainIntent);
            }
        }.start();
    }

    private static Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            return false;
        }
    });
}