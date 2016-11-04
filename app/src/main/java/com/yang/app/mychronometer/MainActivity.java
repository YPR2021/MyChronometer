package com.yang.app.mychronometer;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {

    int second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取计时器组件
        final Chronometer ch = (Chronometer) findViewById(R.id.chronometer);
        findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ch.stop();
                ch.setText(FormatMiss(second));
            }
        });
        findViewById(R.id.btn_continue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ch.start();
            }
        });
        findViewById(R.id.btn_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ch.stop();
                second = 0;
                ch.setText(FormatMiss(second));
            }
        });


        ch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {

            @Override
            public void onChronometerTick(Chronometer chronometer) {
                second++;
                chronometer.setText(FormatMiss(second));
            }
        });
    }

    //格式转换为HH:MM:SS
    public static String FormatMiss(int second) {
        if (second == 0) {
            return "00:00:00";
        }
        String hh = second / 3600 > 9 ? second / 3600 + "" : "0" + second / 3600;
        String mm = (second % 3600) / 60 > 9 ? (second % 3600) / 60 + "" : "0" + (second % 3600) / 60;
        String ss = (second % 3600) % 60 > 9 ? (second % 3600) % 60 + "" : "0" + (second % 3600) % 60;
        return hh + ":" + mm + ":" + ss;
    }
}
