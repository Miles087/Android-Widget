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
import android.widget.TextView;
import android.widget.Toast;

import com.example.miles.project.adapter.AnimListCell;
import com.example.miles.project.adapter.ItemAnimListCellAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Server on 2018/4/9.
 */

@RequiresApi(api = Build.VERSION_CODES.M)
public class AnimationListViewActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

    private ListView lv_list = null;
    private LinearLayout ll_mask = null;
    private Context mContext = null;
    private Button btn_cummit = null;
    private TextView tv_more_text = null;
    private List<AnimListCell> list = null;
    private int iOffSite = 1;           //被选中listView的位置
    private ItemAnimListCellAdapter adapter = null;
    private boolean hasScroll = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_listview);
        mContext = this;
        lv_list = (ListView)findViewById(R.id.lv_list);
        ll_mask = (LinearLayout)findViewById(R.id.ll_mask);
        btn_cummit = (Button) findViewById(R.id.btn_cummit);
        tv_more_text = (TextView) findViewById(R.id.tv_more_text);

        lv_list.setOnScrollListener(this);
        lv_list.setOnItemClickListener(this);
        btn_cummit.setOnClickListener(this);
    }

    private void initListData(){
        AnimListCell cell1 = new AnimListCell();
        cell1.setStrTime("10:30:25");
        cell1.setStrContent("yoo");
        cell1.setStrText("【三无】站在舞台旋转 在灯光中沉沦\n天鹅绒幔布背景做陪衬\n【双笙】以诙谐的语言嘲弄着我的可悲\n油彩覆盖我泪湿的笑纹\n" +
                "【合】将浓妆艳抹灿艳假装做告慰\n在霓虹光点间流连的愚昧\n只是身在其中早已无所谓\n在这夜晚灯火迷离下沉醉\n来往穿梭无情的路人\n" +
                "谁来拯救我的可悲？");
        list.add(cell1);
        AnimListCell cell2 = new AnimListCell();
        cell2.setStrTime("10:30:38");
        cell2.setStrContent("Won’t stop running.running");
        cell2.setStrText("Do you know what's worth fighting for \nWhen it's not worth dying for?\nDoes it take your breath away\nAnd you feel yourself suffocating?\n"+
                "Does the pain weigh out the pride?\nAnd you look for a place to hide?\nDid someone break your heart inside?\nYou're in ruins");
        list.add(cell2);
        AnimListCell cell3 = new AnimListCell();
        cell3.setStrTime("10:31:06");
        cell3.setStrContent("Goodbye my friend its hard to die");
        cell3.setStrText("One' 21 guns\nLay down your arms\nOne' 21 guns\nThrow up your arms into the sky'\n"+
                "You and I\nWhen you're at the end of the road\nAnd you lost all sense of control\n" +
                "And your thoughts have taken their toll\\nWhen your mind breaks the spirit of your soul\n" +
                "Your faith walks on broken glass\nAnd the hangover doesn't pass\nNothing's ever built to last");
        list.add(cell3);
        AnimListCell cell4 = new AnimListCell();
        cell4.setStrTime("10:31:42");
        cell4.setStrContent("when all the birds are singing in the sky");
        cell4.setStrText("我竟然还记得几年前无聊背过的平假名\n" +
                "\n" +
                "但是并没有什么用\n" +
                "\n" +
                "能读出来不知道什么意思\n" +
                "\n" +
                "ひ吐着舌头笑的hihi\n" +
                "\n" +
                "や剪小\uD83D\uDC14\uD83D\uDC14发出ya~的叫声\n" +
                "\n" +
                "く横着看是哭ku的嘴型\n" +
                "\n" +
                "む停留一只乌鸦的十字架下躺着一个人的坟墓mu");
        list.add(cell4);
        AnimListCell cell5 = new AnimListCell();
        cell5.setStrTime("10:32:03");
        cell5.setStrContent("Think of me and I'll be there");
        cell5.setStrText("点开同学的推送。。啊\n" +
                "\n" +
                "后面那座楼，就是中传举办动画系毕业展的地方\n" +
                "\n" +
                "虽然我也只是去年去了一次吧。。");
        list.add(cell5);
        AnimListCell cell6 = new AnimListCell();
        cell6.setStrTime("10:32:47");
        cell6.setStrContent("We had joy. We had fun.");
        cell6.setStrText("-Aura- 更新了一个连载阶段\n" +
                "8个小时前\n" +
                "冗繁削尽留清瘦\n" +
                "\n" +
                "2018.05.16\n" +
                "\n" +
                "雷暴雨使我又快活又兴奋，\n" +
                "\n" +
                "停电却使我一秒丧失斗志。");
        list.add(cell6);
        AnimListCell cell7 = new AnimListCell();
        cell7.setStrTime("10:32:47");
        cell7.setStrContent("We had seasons in the sun");
        cell7.setStrText("最后一条，手写两行\n第二行");
        list.add(cell7);
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

    private void refreshActivity(){
        if (list.size() <= 1){
            setSelectViewPosition(0);
        } else {
            setSelectViewPosition(1);
        }
        adapter.notifyDataSetChanged();
    }

    //设置底部的text
    private void setButtomText(String strText){
        tv_more_text.setText(strText);
    }

    @Override
    protected void onResume() {
        super.onResume();

        list = new ArrayList<>();
        adapter = new ItemAnimListCellAdapter(mContext,list);
        lv_list.setAdapter(adapter);

        initListData();
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

    @Override
    public void onClick(View view) {
        switch (list.size()){
            case 0:{
                AnimListCell cell1 = new AnimListCell();
                cell1.setStrTime("10:30:25");
                cell1.setStrContent("yoo");
                cell1.setStrText("【三无】站在舞台旋转 在灯光中沉沦\n天鹅绒幔布背景做陪衬\n【双笙】以诙谐的语言嘲弄着我的可悲\n油彩覆盖我泪湿的笑纹\n" +
                        "【合】将浓妆艳抹灿艳假装做告慰\n在霓虹光点间流连的愚昧\n只是身在其中早已无所谓\n在这夜晚灯火迷离下沉醉\n来往穿梭无情的路人\n" +
                        "谁来拯救我的可悲？");
                list.add(cell1);
            }break;
            case 1:{
                AnimListCell cell2 = new AnimListCell();
                cell2.setStrTime("10:30:38");
                cell2.setStrContent("Won’t stop running.running");
                cell2.setStrText("Do you know what's worth fighting for \nWhen it's not worth dying for?\nDoes it take your breath away\nAnd you feel yourself suffocating?\n"+
                        "Does the pain weigh out the pride?\nAnd you look for a place to hide?\nDid someone break your heart inside?\nYou're in ruins");
                list.add(cell2);
            }break;
            case 2:{
                AnimListCell cell3 = new AnimListCell();
                cell3.setStrTime("10:31:06");
                cell3.setStrContent("Goodbye my friend its hard to die");
                cell3.setStrText("One' 21 guns\nLay down your arms\nOne' 21 guns\nThrow up your arms into the sky'\n"+
                        "You and I\nWhen you're at the end of the road\nAnd you lost all sense of control\n" +
                        "And your thoughts have taken their toll\\nWhen your mind breaks the spirit of your soul\n" +
                        "Your faith walks on broken glass\nAnd the hangover doesn't pass\nNothing's ever built to last");
                list.add(cell3);
            }break;
            case 3:{
                AnimListCell cell4 = new AnimListCell();
                cell4.setStrTime("10:31:42");
                cell4.setStrContent("when all the birds are singing in the sky");
                cell4.setStrText("我竟然还记得几年前无聊背过的平假名\n" +
                        "\n" +
                        "但是并没有什么用\n" +
                        "\n" +
                        "能读出来不知道什么意思\n" +
                        "\n" +
                        "ひ吐着舌头笑的hihi\n" +
                        "\n" +
                        "や剪小\uD83D\uDC14\uD83D\uDC14发出ya~的叫声\n" +
                        "\n" +
                        "く横着看是哭ku的嘴型\n" +
                        "\n" +
                        "む停留一只乌鸦的十字架下躺着一个人的坟墓mu");
                list.add(cell4);
            }break;
            case 4:{
                AnimListCell cell5 = new AnimListCell();
                cell5.setStrTime("10:32:03");
                cell5.setStrContent("Think of me and I'll be there");
                cell5.setStrText("点开同学的推送。。啊\n" +
                        "\n" +
                        "后面那座楼，就是中传举办动画系毕业展的地方\n" +
                        "\n" +
                        "虽然我也只是去年去了一次吧。。");
                list.add(cell5);
            }break;
            case 5:{
                AnimListCell cell6 = new AnimListCell();
                cell6.setStrTime("10:32:47");
                cell6.setStrContent("We had joy. We had fun.");
                cell6.setStrText("-Aura- 更新了一个连载阶段\n" +
                        "8个小时前\n" +
                        "冗繁削尽留清瘦\n" +
                        "\n" +
                        "2018.05.16\n" +
                        "\n" +
                        "雷暴雨使我又快活又兴奋，\n" +
                        "\n" +
                        "停电却使我一秒丧失斗志。");
                list.add(cell6);
            }break;
            case 6:{
                AnimListCell cell7 = new AnimListCell();
                cell7.setStrTime("10:32:47");
                cell7.setStrContent("We had seasons in the sun");
                cell7.setStrText("最后一条，手写两行\n第二行\n添加到第七条的时候，会清空listView");
                list.add(cell7);
            }break;
//                    case 7:{
//                        AnimListCell cell1 = new AnimListCell();
//                        cell1.setStrTime("10:30:25");
//                        cell1.setStrContent("77777777");
//                        list.add(cell1);
//                    }break;
            default:{
                list.clear();
            }break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        hasScroll = false;
        setButtomText(list.get(i).getStrText());
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

    @Override
    public void onScrollStateChanged(AbsListView absListView, int iScrollState) {
        // 1 滚动  0停止滚动
        if (iScrollState==SCROLL_STATE_IDLE){
            hasScroll = true;
            int iFirstVisiblePosition = lv_list.getFirstVisiblePosition();
            Log.i("Now",iFirstVisiblePosition + "");
            //TODO 判断当前显示的数据，哪个是被选中的，然后显示出其strText
            setButtomText(list.get(iFirstVisiblePosition+iOffSite).getStrText());
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        Log.i("ON SCROLL",firstVisibleItem + " " + visibleItemCount + " " + " " + totalItemCount + " ");
        if (null == list){
            return;
        }
        if (hasScroll) {
            setButtomText(list.get(firstVisibleItem + iOffSite).getStrText());
            hasScroll = false;
        }
    }
}


