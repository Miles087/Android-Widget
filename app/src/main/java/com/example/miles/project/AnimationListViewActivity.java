package com.example.miles.project;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.miles.project.adapter.AnimListCell;
import com.example.miles.project.adapter.ItemAnimListCellAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Server on 2018/4/9.
 */

@RequiresApi(api = Build.VERSION_CODES.M)
public class AnimationListViewActivity extends Activity {

    ListView lv_list;
    LinearLayout ll_mask;
    Context mContext;
    Button btn_cummit;

    List<AnimListCell> list;

    private int iOffSite = 0;

    ItemAnimListCellAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_listview);
        mContext = this;
        lv_list = (ListView)findViewById(R.id.lv_list);
        ll_mask = (LinearLayout)findViewById(R.id.ll_mask);
        btn_cummit = (Button) findViewById(R.id.btn_cummit);

        lv_list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int iScrollState) {
                // 1 滚动  0停止滚动
                if (iScrollState==SCROLL_STATE_IDLE){
                    Log.i("Now",lv_list.getFirstVisiblePosition() + "");
                    lv_list.setSelection(iOffSite);
                } else {
                    iOffSite = 0;
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.i("ON SCROLL",firstVisibleItem + " " + visibleItemCount + " " + " " + totalItemCount + " ");
                iOffSite = firstVisibleItem;
            }
        });

        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    lv_list.setSelection(i - 1);
                    if (i == list.size() - 2){
                        setSelectViewPosition(2);
                    } else if (i == list.size() - 1) {
                        setSelectViewPosition(3);
                    } else {
                        setSelectViewPosition(1);
                    }
                } else {
                    setSelectViewPosition(0);
                }
            }
        });

        btn_cummit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (list.size()){
                    case 0:{
                        AnimListCell cell1 = new AnimListCell();
                        cell1.setStrTime("10:30:25");
                        cell1.setStrContent("yoo");
                        list.add(cell1);
                    }break;
                    case 1:{
                        AnimListCell cell2 = new AnimListCell();
                        cell2.setStrTime("10:30:38");
                        cell2.setStrContent("Won’t stop running.running");
                        list.add(cell2);
                    }break;
                    case 2:{
                        AnimListCell cell3 = new AnimListCell();
                        cell3.setStrTime("10:31:06");
                        cell3.setStrContent("Goodbye my friend its hard to die");
                        list.add(cell3);
                    }break;
                    case 3:{
                        AnimListCell cell4 = new AnimListCell();
                        cell4.setStrTime("10:31:42");
                        cell4.setStrContent("when all the birds are singing in the sky");
                        list.add(cell4);
                    }break;
                    case 4:{
                        AnimListCell cell5 = new AnimListCell();
                        cell5.setStrTime("10:32:03");
                        cell5.setStrContent("Think of me and I'll be there");
                        list.add(cell5);
                    }break;
                    case 5:{
                        AnimListCell cell6 = new AnimListCell();
                        cell6.setStrTime("10:32:47");
                        cell6.setStrContent("We had joy. We had fun.");
                        list.add(cell6);
                    }break;
                    case 6:{
                        AnimListCell cell7 = new AnimListCell();
                        cell7.setStrTime("10:32:47");
                        cell7.setStrContent("We had seasons in the sun");
                        list.add(cell7);
                    }break;
                    case 7:{
                        AnimListCell cell1 = new AnimListCell();
                        cell1.setStrTime("10:30:25");
                        cell1.setStrContent("77777777");
                        list.add(cell1);
                    }break;
                    default:{
                        list.clear();
                    }break;
                }
                refreshActivity();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        list = new ArrayList<>();
        adapter = new ItemAnimListCellAdapter(mContext,list);
        lv_list.setAdapter(adapter);



        AnimListCell cell1 = new AnimListCell();
        cell1.setStrTime("10:30:25");
        cell1.setStrContent("yoo");
        list.add(cell1);
        AnimListCell cell2 = new AnimListCell();
        cell2.setStrTime("10:30:38");
        cell2.setStrContent("Won’t stop running.running");
        list.add(cell2);
        AnimListCell cell3 = new AnimListCell();
        cell3.setStrTime("10:31:06");
        cell3.setStrContent("Goodbye my friend its hard to die");
        list.add(cell3);
        AnimListCell cell4 = new AnimListCell();
        cell4.setStrTime("10:31:42");
        cell4.setStrContent("when all the birds are singing in the sky");
        list.add(cell4);
        AnimListCell cell5 = new AnimListCell();
        cell5.setStrTime("10:32:03");
        cell5.setStrContent("Think of me and I'll be there");
        list.add(cell5);
        AnimListCell cell6 = new AnimListCell();
        cell6.setStrTime("10:32:47");
        cell6.setStrContent("We had joy. We had fun.");
        list.add(cell6);
        AnimListCell cell7 = new AnimListCell();
        cell7.setStrTime("10:32:47");
        cell7.setStrContent("We had seasons in the sun");
        list.add(cell7);

        refreshActivity();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void refreshActivity(){
        if (list.size() <= 1){
            setSelectViewPosition(0);
        } else {
            setSelectViewPosition(1);
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 设置选中框的位置
     * @param i 0~3
     */
    private void setSelectViewPosition(int i){
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ll_mask.getLayoutParams());
        if (list.size() <= 1){
            params.setMargins(0,0,0,0);
        } else {
            params.setMargins(params.leftMargin, (int) getResources().getDimension(R.dimen.list_cell_height) * i,params.rightMargin,params.bottomMargin);
        }
        ll_mask.setLayoutParams(params);
    }

}
