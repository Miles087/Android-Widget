package com.example.miles.project.realwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.miles.project.R;

/**
 * Created by Miles on 2018/1/23.
 */

public class MyWidgetProvider extends AppWidgetProvider {

    public static final String CLICK_ACTION = "com.example.server.touchpull.realwidget.MyWidgetProvider.action.CLICK"; // 点击事件的广播ACTION

    /**
     * 桌面小控件被点击时
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (CLICK_ACTION.equals(intent.getAction())) {
            Toast.makeText(context, "Hello there!", Toast.LENGTH_SHORT).show();



        }
    }

    /**
     * 每次窗口小部件被更新都调用一次该方法
     * @param context
     * @param appWidgetManager
     * @param appWidgetIds
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        Intent intent = new Intent(CLICK_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, R.id.doge_imageView, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.doge_imageView, pendingIntent);

        for (int appWidgetId : appWidgetIds) {
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }

    }

    /**
     * 每次删除桌面小控件时调用
     * @param context
     * @param appWidgetIds
     */
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    /**
     * 当该窗口小部件第一次添加到桌面时调用该方法
     * @param context
     */
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    /**
     * 最后一个小控件被删除时调用
     * @param context
     */
    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

    /**
     * 当小部件大小改变时
     * @param context
     * @param appWidgetManager
     * @param appWidgetId
     * @param newOptions
     */
    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
    }

    /**
     * 当小部件从备份恢复时调用该方法
     * @param context
     * @param oldWidgetIds
     * @param newWidgetIds
     */
    @Override
    public void onRestored(Context context, int[] oldWidgetIds,
                           int[] newWidgetIds) {
        super.onRestored(context, oldWidgetIds, newWidgetIds);
    }
}
