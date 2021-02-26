package com.example.miles.project.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Description: MQTT客户端订阅者同时满足如下条件时，会变成持久订阅者
 * 1、cleanSession为false
 * 2、clientId不为空
 * 3、mqttClient.subscribe(destination,{1});
 * Author:      Created by Miles Wang
 * CreatedDate: 2020-11-30 11:16
 * Email:       icy-star@qq.com
 *
 * · AndroidManifest.xml中需要以下权限：
 * <uses-permission android:name="android.permission.WAKE_LOCK" />
 * · AndroidManifest.xml中需要注册以下服务
 * <service android:name="org.eclipse.paho.android.service.MqttService" />
 * <service android:name=".service.MQTTService" android:enabled="true" />
 * · 消息发送：
 * MQTTService.publish("消息内容");
 * 
 *
 **/

public class MQTTService extends Service {

    private final String TAG = "MY_MQTT_SERVICE";
    private static MqttAndroidClient mqttAndroidClient;
    private MqttConnectOptions mMqttConnectOptions;
    static Integer qos = 2;
    public static String HOST = "";//服务器地址（协议+地址+端口号）例如:tcp://192.168.2.50:1883
    public static String USERNAME = "";//用户名
    public static String PASSWORD = "";//密码
    public static String CLIENTID = "";//客户端ID，一般以客户端唯一标识符表示，这里用设备序列号表示
    public static String publishTopic = "";
    public static String responseTopic = "";




    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        init();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 发布 （模拟其他客户端发布消息）
     *
     * @param message 消息
     */
    public static void publish(String message) {
        String topic = publishTopic;
        Boolean retained = false;
        try {
            if (null != mqttAndroidClient) {
                //参数分别为：主题、消息的字节数组、服务质量、是否在服务器保留断开连接后的最后一条消息
                mqttAndroidClient.publish(topic, message.getBytes(), qos.intValue(), retained.booleanValue());
            }
        } catch (MqttException e) {
            e.printStackTrace();
            Log.i("MQ_err", "由于某些原因导致的错误，重新初始化一下");
        }
        Log.i("MY_MQTT_SERVICE", "在:[" + publishTopic + "]发布了:{" + message + "}消息");
    }

    /**
     * 响应 （收到其他客户端的消息后，响应给对方告知消息已到达或者消息有问题等）
     *
     * @param message 消息
     */
    public void response(String message) {
        String topic = responseTopic;
        Boolean retained = false;
//        try {
//            //参数分别为：主题、消息的字节数组、服务质量、是否在服务器保留断开连接后的最后一条消息
//            mqttAndroidClient.publish(topic, message.getBytes(), qos.intValue(), retained.booleanValue());
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 初始化
     */
    private void init() {
        String serverURI = HOST; //服务器地址（协议+地址+端口号）
        mqttAndroidClient = new MqttAndroidClient(this, serverURI, CLIENTID);
        mqttAndroidClient.setCallback(mqttCallback); //设置监听订阅消息的回调
        mMqttConnectOptions = new MqttConnectOptions();
        mMqttConnectOptions.setCleanSession(true); //设置是否清除缓存
        mMqttConnectOptions.setConnectionTimeout(10); //设置超时时间，单位：秒
        mMqttConnectOptions.setKeepAliveInterval(20); //设置心跳包发送间隔，单位：秒
        mMqttConnectOptions.setUserName(USERNAME); //设置用户名
        mMqttConnectOptions.setPassword(PASSWORD.toCharArray()); //设置密码

        // last will message
        boolean doConnect = true;
        String message = "CLIENTID:" + CLIENTID + "离线了";
        String topic = publishTopic;
        Boolean retained = false;
        if ((!message.equals("")) || (!topic.equals(""))) {
            // 最后的遗嘱
            try {
                mMqttConnectOptions.setWill(topic, message.getBytes(), qos.intValue(), retained.booleanValue());
            } catch (Exception e) {
                Log.i(TAG, "Exception Occured", e);
                doConnect = false;
                iMqttActionListener.onFailure(null, e);
            }
        }
        if (doConnect) {
            doClientConnection();
        }
    }

    /**
     * 连接MQTT服务器
     */
    private void doClientConnection() {
        try {

            if (mqttAndroidClient != null) {
                if (!mqttAndroidClient.isConnected() && isConnectIsNomarl()) {
                    try {
                        mqttAndroidClient.connect(mMqttConnectOptions, null, iMqttActionListener);
                        Log.i(TAG, "正在连接MQTT服务器");
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            Log.i("MQ_err", "由于某些原因导致的错误(可能是连不上MQ地址)，重新初始化一下");
            init();
        }
    }

    /**
     * 判断网络是否连接
     */
    private boolean isConnectIsNomarl() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            String name = info.getTypeName();
            Log.i(TAG, "当前网络名称：" + name);
            return true;
        } else {
            Log.i(TAG, "没有可用网络");
            /*没有可用网络的时候，延迟3秒再尝试重连*/
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doClientConnection();
                }
            }, 3000);
            return false;
        }
    }

    //MQTT是否连接成功的监听
    private IMqttActionListener iMqttActionListener = new IMqttActionListener() {
        @Override
        public void onSuccess(IMqttToken arg0) {
            Toast.makeText(getApplicationContext(),"连接成功",Toast.LENGTH_LONG).show();
            Log.i(TAG, "连接成功,当前PublishTopic为：" + publishTopic + "\n 当前的ResponseTopic是：" + responseTopic);
            try {
                if (null == mqttAndroidClient) {
                    return;
                }
                mqttAndroidClient.subscribe(responseTopic, qos);//订阅主题，参数：主题、服务质量
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(IMqttToken arg0, Throwable arg1) {
//            arg1.printStackTrace();
            Toast.makeText(getApplicationContext(),"连接失败",Toast.LENGTH_LONG).show();
            Log.i(TAG, "连接失败 ");
            doClientConnection();//连接失败，重连（可关闭服务器进行模拟）
        }
    };

    //订阅主题的回调
    private MqttCallback mqttCallback = new MqttCallback() {

        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {
            Log.i(TAG, "收到消息： " + new String(message.getPayload()));
//            //收到消息，这里弹出Toast表示。如果需要更新UI，可以使用广播或者EventBus进行发送
            Toast.makeText(getApplicationContext(), "messageArrived: " + new String(message.getPayload()), Toast.LENGTH_LONG).show();
//            //收到其他客户端的消息后，响应给对方告知消息已到达或者消息有问题等
//            response("message arrived");
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken arg0) {

        }

        @Override
        public void connectionLost(Throwable arg0) {
            Log.i(TAG, "连接断开 ");
            doClientConnection();//连接断开，重连
        }
    };

    @Override
    public void onDestroy() {
        try {
            mqttAndroidClient.disconnect(); //断开连接
            mqttAndroidClient.unregisterResources();
        } catch (MqttException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}