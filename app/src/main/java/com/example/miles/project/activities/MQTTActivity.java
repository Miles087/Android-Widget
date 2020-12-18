package com.example.miles.project.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.miles.project.R;

/**
 * Description: MQTT测试界面
 * Author:      Created by Miles Wang
 * CreatedDate: 2020-12-18 15:01
 * Email:       icy-star@qq.com
 **/

public class MQTTActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mqttservice);
    }
}