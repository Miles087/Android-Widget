package com.example.server.touchpull.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Server on 2018/3/5.
 */

public class ScreenUtil {

    private ScreenUtil(){
        throw new UnsupportedOperationException("can not instantiated");
    }

    /**
     * 获取屏幕宽度
     * @param mContext
     * @return
     */
    public static int getScreenWidth(Context mContext){
        WindowManager windowManager = (WindowManager) mContext.getSystemService(mContext.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获取屏幕高度
     * @param mContext
     * @return
     */
    public static int getScreenHeight(Context mContext){
        WindowManager windowManager = (WindowManager) mContext.getSystemService(mContext.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 获得状态栏高度
     * @param mContext
     * @return
     */
    public static int getStatusHeight(Context mContext){
        int iStatusHeight = -1;
        try{
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int iHeight = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            iStatusHeight = mContext.getResources().getDimensionPixelSize(iStatusHeight);
        }catch (Exception e){
            e.printStackTrace();
        }
        return iStatusHeight;
    }

    /**
     * 获取当前屏幕截图，包含状态栏
     * @param activity
     * @return
     */
    public static Bitmap snapShotWithStatusBar(Activity activity){
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        int iWidth = getScreenWidth(activity);
        int iHeight = getScreenHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bitmap,0,0,iWidth,iHeight);
        view.destroyDrawingCache();
        return bp;
    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     * @param activity
     * @return
     */
    public static Bitmap snapShotWithoutStatusBar(Activity activity){
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int iStatusBarheight = rect.top;

        int iWidth = getScreenWidth(activity);
        int iHeight = getScreenHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bitmap,0,iStatusBarheight,iWidth,iHeight-iStatusBarheight);
        view.destroyDrawingCache();
        return bp;
    }

}
