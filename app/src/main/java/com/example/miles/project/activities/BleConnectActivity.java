package com.example.miles.project.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.miles.project.R;

/**
 * Description: 低功耗蓝牙连接并发送、接收消息
 *              1，引入implementation 'com.clj.fastble:FastBleLib:2.3.4'
 *              2，Manifest里加入<uses-feature android:name="android.hardware.bluetooth_le" android:required="true" />
 *              Fork别人的地址：https://github.com/Miles087/FastBle
 * Author:      Created by Miles Wang
 * CreatedDate: 2021-03-08
 * Email:       icy-star@qq.com
 **/
public class BleConnectActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivy_ble_activity);
    }
}
