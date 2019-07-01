package com.example.miles.project.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miles.project.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 用途：
 * 作者：Created by Miles Wang on 2019/1/24
 * 邮箱：icy-star@qq.com
 **/
public class GetApplicationListActivity extends Activity {

    ListView lv_list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_app_list);


        lv_list = findViewById(R.id.lv_list);

        ArrayList<AppInfo> appList = new ArrayList<>();
        List<PackageInfo> packageInfos = getPackageManager().getInstalledPackages(PackageManager.GET_ACTIVITIES);
        for (PackageInfo packageInfo : packageInfos) {
            AppInfo appInfo = new AppInfo();
            appInfo.appName = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
            appInfo.packageName = packageInfo.packageName;
            appInfo.versionName = packageInfo.versionName;
            appInfo.versionCode = packageInfo.versionCode + "";
            appInfo.icon = packageInfo.applicationInfo.loadIcon(getPackageManager());
            appList.add(appInfo);
        }

        AppAdapter adapter = new AppAdapter(this,appList);
        lv_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    /**
     * APP信息
     */
    private class AppInfo {
        String appName;
        String packageName;
        String versionName;
        String versionCode;
        Drawable icon;
    }

    /**
     * 用于展示在界面上的APP列表适配器
     */
    private class AppAdapter extends BaseAdapter {

        List<AppInfo> list;
        Context mContext;

        public AppAdapter(Context c, List<AppInfo> l) {
            list = l;
            mContext = c;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_app,null);
                holder = new ViewHolder();
                holder.imageView = convertView.findViewById(R.id.iv_image);
                holder.tv_name = convertView.findViewById(R.id.tv_name);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final AppInfo info = list.get(position);
            holder.imageView.setBackground(info.icon);
            holder.tv_name.setText(info.appName);
            holder.tv_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,info.packageName,Toast.LENGTH_SHORT).show();

                    String strPackageName = info.packageName;
                    PackageManager packageManager = getPackageManager();
                    Intent intent = packageManager.getLaunchIntentForPackage(strPackageName);
                    startActivity(intent);

                }
            });

            return convertView;
        }

        class ViewHolder {
            ImageView imageView;
            TextView tv_name;
        }
    }
}
