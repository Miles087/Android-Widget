package com.example.miles.project;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.miles.project.adapter.ItemActivity;
import com.example.miles.project.adapter.ItemMainCellAdapter;
import com.example.miles.project.widget.CacheManager;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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
                switch (item.getStrTag()){
                    case "pull":{
                        Intent jumper = new Intent(mContext,DragPullActivity.class);
                        startActivity(jumper);
                    }break;
                    case "volume":{
                        Intent jumper = new Intent(mContext,VolumeActivity.class);
                        startActivity(jumper);
                    }break;
                    case "fragmentValueActivity":{
                        Intent jumper = new Intent(mContext,HomeActivity.class);
                        startActivity(jumper);
                    }break;
                    case "myCamera":{
                        Intent jumper = new Intent(mContext,MyCameraActivity.class);
                        startActivity(jumper);
                    }break;
                    case "rollPictures":{
                        Intent jumper = new Intent(mContext,RollImages.class);
                        startActivity(jumper);
                    }break;
                    case "dragView":{
                        Intent jumper = new Intent(mContext,TouchView.class);
                        startActivity(jumper);
                    }break;
                }
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
    }
}
