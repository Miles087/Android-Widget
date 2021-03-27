package com.example.miles.project.activities;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;

import com.example.miles.project.R;
import com.example.miles.project.view.BlurImageView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Description: 毛玻璃效果的界面
 * Author:      Created by Miles Wang
 * CreatedDate: 2021-03-27
 * Email:       icy-star@qq.com
 **/
public class BlurGlassActivity extends Activity {

    private BlurImageView blurImageView;
    private ImageView mBackGround;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur_glass);
        mBackGround = findViewById(R.id.iv_bg);
        blurImageView = findViewById(R.id.biv);

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                blurImageView.refreshBG(mBackGround);
            }
        };
        final Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        };
        timer.schedule(task, 0, 1000);

        blurImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blurImageView.setVisibility(View.GONE);
            }
        });

        mBackGround.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blurImageView.setVisibility(View.VISIBLE);
            }
        });
    }
}
