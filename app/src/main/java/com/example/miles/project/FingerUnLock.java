package com.example.miles.project;

import android.Manifest;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miles.project.widget.FingerPrintUiHelper;

/**
 * Created by Server on 2018/4/18.
 */

public class FingerUnLock extends Activity {

    private Button button;
    private TextView tv_text;
    private Context mContext;

    private FingerprintManager mFingerprintManager;
    private KeyguardManager mKeyguardManager;

    private final int REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS = 0;
    private FingerPrintUiHelper fingerPrintUiHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_unlock);
        mContext = this;
        tv_text = (TextView)findViewById(R.id.tv_text);
        button = (Button) findViewById(R.id.button);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            initFingerPrint();
            try{
                mFingerprintManager = (FingerprintManager) mContext.getSystemService(Context.FINGERPRINT_SERVICE);
                mKeyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
            } catch (NoClassDefFoundError e){
                tv_text.setText("抱歉，您的手机暂不支持该功能");
            } catch (Exception e){

            }
        } else {
            tv_text.setText("该版本暂不支持指纹解锁");
        }
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                boolean bCanCheck = true;
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    tv_text.setText("权限检测时发现您并没有申请或打开指纹权限");
                    return;
                }
                if (!mFingerprintManager.isHardwareDetected()) {
                    // 判断硬件是否支持
//                    Toast.makeText(mContext, "Fingerprint hardward unavailable", Toast.LENGTH_LONG).show();
                    bCanCheck = false;
                    tv_text.setText("硬件不支持");
                    return;
                }
                if (!mKeyguardManager.isKeyguardSecure()) {
                    // 判断用户是已经开启锁屏密码
//                    Toast.makeText(mContext, "Secure lock screen hasn't set up.\n"  + "Go to 'Settings -> Security -> Screenlock' to set up a lock screen", Toast.LENGTH_LONG).show();
                    bCanCheck = false;
                    tv_text.setText("用户未开启锁屏密码");
                    return;
                }
                if (!mFingerprintManager.hasEnrolledFingerprints()) {
                    // 判断是否有已经录入的指纹
//                    Toast.makeText(mContext, "Go to 'Settings -> Security -> Fingerprint' and register at least one fingerprint", Toast.LENGTH_LONG).show();
                    bCanCheck = false;
                    tv_text.setText("用户并未录入指纹");
                    return;
                }
                if (bCanCheck){
                    jumpToGesturePassCheck();
                }
            }
        });
    }

    /**
     * 跳转到手势密码校验界面
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void jumpToGesturePassCheck() {
        KeyguardManager keyguardManager =
                (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        Intent intent =
                keyguardManager.createConfirmDeviceCredentialIntent("finger", "测试指纹识别");
        fingerPrintUiHelper.stopsFingerPrintListen();
        startActivityForResult(intent, REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS);
    }


    /**
     * 初始化
     */
    private void initFingerPrint() {
        fingerPrintUiHelper = new FingerPrintUiHelper(this);
        fingerPrintUiHelper.startFingerPrintListen(new FingerprintManagerCompat.AuthenticationCallback() {
            /**
             * Called when a fingerprint is recognized.
             *
             * @param result An object containing authentication-related data
             */
            @Override
            public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                tv_text.setText("指纹识别成功");
            }

            /**
             * Called when a fingerprint is valid but not recognized.
             */
            @Override
            public void onAuthenticationFailed() {
                tv_text.setText("指纹识别失败");
            }

            /**
             * Called when a recoverable error has been encountered during authentication. The help
             * string is provided to give the user guidance for what went wrong, such as
             * "Sensor dirty, please clean it."
             *
             * @param helpMsgId  An integer identifying the error message
             * @param helpString A human-readable string that can be shown in UI
             */
            @Override
            public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                Toast.makeText(mContext, helpString, Toast.LENGTH_SHORT).show();
            }

            /**
             * Called when an unrecoverable error has been encountered and the operation is complete.
             * No further callbacks will be made on this object.
             *
             * @param errMsgId  An integer identifying the error message
             * @param errString A human-readable error string that can be shown in UI
             */
            @Override
            public void onAuthenticationError(int errMsgId, CharSequence errString) {
                //但多次指纹密码验证错误后，进入此方法；并且，不能短时间内调用指纹验证
//                Toast.makeText(mContext, "errMsgId=" + errMsgId + "|" + errString, Toast.LENGTH_SHORT).show();
                if (errMsgId == 7) { //出错次数过多（小米5测试是5次）
                    tv_text.setText("失败次数超出限制");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS) {
            // Challenge completed, proceed with using cipher
            if (resultCode == RESULT_OK) {
                tv_text.setText("识别成功了");
            } else {
                tv_text.setText("识别失败了");
            }
        }
    }
}
