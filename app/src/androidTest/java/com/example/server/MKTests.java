package com.example.server;

import android.app.Instrumentation;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;
import android.view.KeyEvent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * Created by Server on 2018/6/11.
 */


@RunWith(AndroidJUnit4.class)
public class MKTests {
    public Instrumentation mInstrumentation;
    public UiDevice mUiDevice;

    @Before
    public void setUp(){




        mInstrumentation = InstrumentationRegistry.getInstrumentation();
        mUiDevice = UiDevice.getInstance(mInstrumentation);
    }

    //上面的是套路代码

    /**
     * 从首页进入 更多出借 雪花精选 选择ljf002 投资500元
     */
    @Test
    public void HomePageToMoreTenderToSnowFlakeTendljf002() throws RemoteException, InterruptedException {
        //滑动解锁
//        mUiDevice.swipe(507,1714,507,507,10); //10个steps 就是 50毫秒
        //桌面上找小平贷的图标并打开APP
        mUiDevice.findObject(By.res("com.meizu.flyme.launcher:id/app_icon")).click();
        //延时5秒 等待打开APP
        Thread.sleep(5000);
        //点击更多出借
        mUiDevice.findObject(By.res("com.xiaopingdai.studio:id/radio_more")).click();
        if (IsLoading()){ } else {
            //查看雪花的列表
            mUiDevice.findObject(By.text("雪花精选")).click();
        }
        if (IsLoading()){ } else {
            // 立即出借
            mUiDevice.findObject(By.res("com.xiaopingdai.studio:id/btn_submit")).click();
        }
        if (IsLoading()){ } else {
            //全投
            mUiDevice.findObject(By.res("com.xiaopingdai.studio:id/tv_all")).click();
            //提交
            mUiDevice.findObject(By.res("com.xiaopingdai.studio:id/btn_submit")).click();}
        if (IsLoading()){ } else {
            // 风险提示
            Thread.sleep(2000);
        mUiDevice.findObject(By.res("com.xiaopingdai.studio:id/tv_conform")).clickAndWait(Until.newWindow(),5000); }
        if (IsLoading()){ } else {
            Thread.sleep(1000);
            mUiDevice.swipe(94, 1276, 94, 351, 30); //10个steps 就是 50毫秒
        }



//        UiObject2 parentView = mUiDevice.findObject(By.desc("密码"));
//        List<UiObject2> etPWDs = parentView.getParent().getChildren();
//        for (UiObject2 pwd : etPWDs){
//            if (pwd.isClickable()){
//                pwd.setText("123456");
//            }
//        }
//        if (!mUiDevice.findObject(By.clazz("android.widget.CheckBox")).isChecked()){
//            mUiDevice.findObject(By.clazz("android.widget.CheckBox")).click();
//        }

        // 点击并输入密码
        mUiDevice.click(527,1251);
        mUiDevice.pressKeyCode(KeyEvent.KEYCODE_1);
        mUiDevice.pressKeyCode(KeyEvent.KEYCODE_2);
        mUiDevice.pressKeyCode(KeyEvent.KEYCODE_3);
        mUiDevice.pressKeyCode(KeyEvent.KEYCODE_4);
        mUiDevice.pressKeyCode(KeyEvent.KEYCODE_5);
        mUiDevice.pressKeyCode(KeyEvent.KEYCODE_6);

        mUiDevice.pressBack();

        Thread.sleep(1000);
        //点击同意协议
        mUiDevice.click(60,1427);
        //点击提交
        mUiDevice.click(551,1829);
0


    }

    /**
     *
     * @return TRUE 载入中 FALSE 没有载入
     */
    private boolean IsLoading(){
        boolean bIsLoading = true;
       while(bIsLoading){
           UiObject2 loadingScene = mUiDevice.findObject(By.res("com.xiaopingdai.studio:id/ll_loading_scene"));
           if (null == loadingScene) {
               bIsLoading = false;
           } else {
               bIsLoading = true;
           }
       }
        return bIsLoading;
    }
}
