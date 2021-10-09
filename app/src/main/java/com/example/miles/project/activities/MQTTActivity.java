package com.example.miles.project.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.miles.project.R;
import com.example.miles.project.service.MQTTService;

/**
 * Description: MQTT测试界面
 * Author:      Created by Miles Wang
 * CreatedDate: 2020-12-18 15:01
 * Email:       icy-star@qq.com
 **/

public class MQTTActivity extends Activity {

    private EditText etAddress;
    private EditText etUserName;
    private EditText etPassword;
    private EditText etClientId;
    private Button btnConnect;
    private EditText etPublishTopic;
    private EditText etResponseToic;
    private Button btnSendSth;
    private Context mContext;

    private Intent mqttService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mqttservice);
        mContext = this;
        initView();
        setDefaultValue();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        etAddress = findViewById(R.id.et_address);
        etUserName = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_pwd);
        etClientId = findViewById(R.id.et_client_id);
        btnConnect = findViewById(R.id.btnConnect);
        etPublishTopic = findViewById(R.id.etPublishTopic);
        etResponseToic = findViewById(R.id.etResponseToic);
        btnSendSth = findViewById(R.id.btnSendSth);
        btnConnect.setOnClickListener(connectListener);
        btnSendSth.setOnClickListener(sendMsgListener);
    }

    /**
     * 设置默认值
     */
    private void setDefaultValue() {
        etAddress.setText("tcp://8.140.133.144:1883");
        etUserName.setText("admin");
        etPassword.setText("public");
        etClientId.setText("122444"+System.currentTimeMillis());
        etPublishTopic.setText("282287811");
        etResponseToic.setText("2822878112");
    }

    /**
     * 连接MQTT
     */
    private View.OnClickListener connectListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            /** 设置所需参数 **/
            MQTTService.HOST = etAddress.getText().toString();
            MQTTService.USERNAME = etUserName.getText().toString();
            MQTTService.PASSWORD = etPassword.getText().toString();
            MQTTService.CLIENTID = etClientId.getText().toString();
            MQTTService.publishTopic = etPublishTopic.getText().toString();
            MQTTService.responseTopic = etResponseToic.getText().toString();
            /** 开启服务 **/
            mqttService = new Intent(mContext,MQTTService.class);
            mContext.startService(mqttService);
        }
    };

    /** 发送消息 **/
    private View.OnClickListener sendMsgListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MQTTService.publish("消息内容");
        }
    };
}