package com.example.miles.project;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.miles.project.adapter.ItemActivity;
import com.example.miles.project.adapter.ItemMainCellAdapter;
import com.example.miles.project.widget.CacheManager;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    /**
     *  mediaPlayer.setDataSource("https://api.live.bilibili.com/api/playurl?platform=h5&cid=3742025");
     *  mediaPlayer.setDataSource("rtmp://203.207.99.19:1935/live/CCTV5");
     *  mediaPlayer.setDataSource("http://www.360doc.com/rtsp://2.itvitv.com/tvbmov.saqjsdfdtwn");
     *  mediaPlayer.setDataSource("http://58.30.32.130:8090/live/C89B590F4F8C4fbc960028B35665DA39/index.m3u8");
     *  mediaPlayer.setDataSource("rtsp://58.200.131.2/cctv-6-hd");
     *  mediaPlayer.setDataSource("rtmp://203.207.99.19:1935/live/CCTV5");
     */



    ListView list_activity_item;
    Context mContext;
    ItemMainCellAdapter adapter;

    final List<ItemActivity> list = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        list_activity_item = (ListView)findViewById(R.id.list_activity_item);

        //设置默认缓存路径
        CacheManager.setSysCachePath(getCacheDir().getPath());
    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter = new ItemMainCellAdapter(mContext,list);
        list_activity_item.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        initViewData();
        list_activity_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ItemActivity item = list.get(i);
                Intent jumper = null;
                switch (item.getStrTag()){
                    case "pull":{
                        jumper = new Intent(mContext,DragPullActivity.class);
                    }break;
                    case "volume":{
                        jumper = new Intent(mContext,VolumeActivity.class);
                    }break;
                    case "fragmentValueActivity":{
                        jumper = new Intent(mContext,HomeActivity.class);
                    }break;
                    case "myCamera":{
                        jumper = new Intent(mContext,MyCameraActivity.class);
                    }break;
                    case "rollPictures":{
                        jumper = new Intent(mContext,RollImages.class);
                    }break;
                    case "dragView":{
                        jumper = new Intent(mContext,TouchView.class);
                    }break;
                    case "flowLayout":{
                        jumper = new Intent(mContext,FlowActivity.class);
                    }break;
                    case "listView":{
                        jumper = new Intent(mContext,AnimationListViewActivity.class);
                    }break;
                    case "fingerUnLock":{
                        jumper = new Intent(mContext,FingerUnLock.class);
                    }break;
                    case "open_xpd":{
                        String strPackageName = "com.要打开的APP";
                        PackageManager packageManager = getPackageManager();
                        if (checkPackInfo(strPackageName)) {
                            Intent intent = packageManager.getLaunchIntentForPackage(strPackageName);
                            startActivity(intent);
                        } else {
                            Toast.makeText(mContext,"没有安装小平贷",Toast.LENGTH_SHORT).show();
                        }
                        return;
                    }
                    case "more_font_textview":{
                        jumper = new Intent(mContext,MoreFontTextView.class);
                    }break;
                    case "fingerUnLockWithMyActivity":{
                        jumper = new Intent(mContext,MyFingerPrintActivity.class);
                    }break;
                }
                startActivity(jumper);
            }
        });
    }

    void initViewData(){
        list.clear();
        ItemActivity item = new ItemActivity();
        item.setStrName(getString(R.string.drag_pull_activity));
        item.setStrTag("pull");
        list.add(item);
        ItemActivity item1 = new ItemActivity();
        item1.setStrName(getString(R.string.volume_activity));
        item1.setStrTag("volume");
        list.add(item1);
        ItemActivity item2 = new ItemActivity();
        item2.setStrName(getString(R.string.fragment_value_activity));
        item2.setStrTag("fragmentValueActivity");
        list.add(item2);
        ItemActivity item3 = new ItemActivity();
        item3.setStrName(getString(R.string.my_camera));
        item3.setStrTag("myCamera");
        list.add(item3);
        ItemActivity item4 = new ItemActivity();
        item4.setStrName(getString(R.string.roll_pictures));
        item4.setStrTag("rollPictures");
        list.add(item4);
        ItemActivity item5 = new ItemActivity();
        item5.setStrName(getString(R.string.drag_view));
        item5.setStrTag("dragView");
        list.add(item5);
        ItemActivity item6 = new ItemActivity();
        item6.setStrName(getString(R.string.flow_layout));
        item6.setStrTag("flowLayout");
        list.add(item6);
        ItemActivity item7 = new ItemActivity();
        item7.setStrName(getString(R.string.animation_listview));
        item7.setStrTag("listView");
        list.add(item7);
        ItemActivity item8 = new ItemActivity();
        item8.setStrName(getString(R.string.fingerUnLock));
        item8.setStrTag("fingerUnLock");
        list.add(item8);
        ItemActivity item9 = new ItemActivity();
        item9.setStrName(getString(R.string.open_xpd));
        item9.setStrTag("open_xpd");
        list.add(item9);
        ItemActivity item10 = new ItemActivity();
        item10.setStrName(getString(R.string.more_font_textview));
        item10.setStrTag("more_font_textview");
        list.add(item10);
        ItemActivity item11 = new ItemActivity();
        item11.setStrName(getString(R.string.fingerUnLockWithMyActivity));
        item11.setStrTag("fingerUnLockWithMyActivity");
        list.add(item11);
    }

    /**
     * 检查包是否存在
     * @param packName
     * @return
     */
    private boolean checkPackInfo(String packName){
        PackageInfo packageInfo = null;
        try{
            packageInfo = getPackageManager().getPackageInfo(packName, 0);
        }catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }
}
