package com.example.miles.project;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by Server on 2018/7/12.
 */

public class MyFingerPrintActivity extends Activity {

    ImageView iv_fin;           //指纹图片
    TextView tv_log;            //状态
    TextView tv_re_login;

    private FingerprintManagerCompat manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_finger);

        iv_fin = (ImageView) findViewById(R.id.iv_fin);
        tv_log = (TextView) findViewById(R.id.tv_log);
        tv_re_login = (TextView) findViewById(R.id.tv_re_login);


        // 获取一个FingerPrintManagerCompat的实例
        manager = FingerprintManagerCompat.from(this);

    }

    @Override
    protected void onResume() {
        super.onResume();



        /**
         * 开始验证，什么时候停止由系统来确定，如果验证成功，那么系统会关系sensor，如果失败，则允许
         * 多次尝试，如果依旧失败，则会拒绝一段时间，然后关闭sensor，过一段时候之后再重新允许尝试
         *
         * 第四个参数为重点，需要传入一个FingerprintManagerCompat.AuthenticationCallback的子类
         * 并重写一些方法，不同的情况回调不同的函数
         */
        manager.authenticate(null, 0, null, new MyCallBack(), null);
    }


    public class MyCallBack extends FingerprintManagerCompat.AuthenticationCallback {
        private static final String TAG = "MyCallBack";

        // 当出现错误的时候回调此函数，比如多次尝试都失败了的时候，errString是错误信息
        @Override
        public void onAuthenticationError(int errMsgId, CharSequence errString) {
            tv_log.setText("onAuthenticationError: " + errString);
        }

        // 当指纹验证失败的时候会回调此函数，失败之后允许多次尝试，失败次数过多会停止响应一段时间然后再停止sensor的工作
        @Override
        public void onAuthenticationFailed() {
            tv_log.setText("onAuthenticationFailed: " + "验证失败");
        }

        @Override
        public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
            tv_log.setText("onAuthenticationHelp: " + helpString);
        }

        // 当验证的指纹成功时会回调此函数，然后不再监听指纹sensor
        @Override
        public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult
                                                      result) {
            tv_log.setText("onAuthenticationSucceeded: " + "验证成功");
        }
    }


}
