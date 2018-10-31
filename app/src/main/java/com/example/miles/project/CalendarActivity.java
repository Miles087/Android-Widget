package com.example.miles.project;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miles.project.widget.horizontalListView.HorizontalListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarActivity extends Activity {

    private TextView tv_year;
    private Context mContext;
    private MonthAdapter adapter;
    private GridView gv_date;                       //日期
    private HorizontalListView hlv_month;          //月份
    private List<MonthText> monthList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_calendar);

        mContext = this;
        tv_year = findViewById(R.id.tv_year);
        hlv_month = findViewById(R.id.hlv_month);
        gv_date = findViewById(R.id.gv_date);


        tv_year.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                int year, monthOfYear, dayOfMonth, hourOfDay, minute;

                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                monthOfYear = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);


                /**
                 * 实例化一个DatePickerDialog的对象
                 * 第二个参数是一个DatePickerDialog.OnDateSetListener匿名内部类，当用户选择好日期点击done会调用里面的onDateSet方法
                 */
                DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        Toast.makeText(mContext, "日期：" + year + "-" + (monthOfYear + 1) + "-" + dayOfMonth, Toast.LENGTH_SHORT).show();
                    }
                }, year, monthOfYear, dayOfMonth);

                datePickerDialog.show();
            }
        });


        Calendar CD = Calendar.getInstance();
        int YY = CD.get(Calendar.YEAR) ;
        int MM = CD.get(Calendar.MONTH)+1;





        initMonthData(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 初始化某月的日期
     * @param month
     */
    private void initMonthData(int month){
        Calendar CD = Calendar.getInstance();
        if (month > 0) {
            CD.set(CD.MONTH,month-1);
        }
        int YY = CD.get(CD.YEAR);
        int MM = CD.get(CD.MONTH) + 1;
        if (month != 0) {
            MM = month;
        }
        CD.set(Calendar.DAY_OF_MONTH, 1);  //指定日
        int dayOfWeek = CD.get(CD.DAY_OF_WEEK);
        int weekDay = 0;
        switch (dayOfWeek) {
            case 1:{
                weekDay = 7;
            }break;
            case 2:{
                weekDay = 1;
            }break;
            case 3:{
                weekDay = 2;
            }break;
            case 4:{
                weekDay = 3;
            }break;
            case 5:{
                weekDay = 4;
            }break;
            case 6:{
                weekDay = 6;
            }break;
            case 7:{
                weekDay = 6;
            }break;
        }

        initMonth(weekDay, getDaysOfMonth(YY, MM));
    }

    /**
     * 初始化月份容器  12个月的item
     * @param iweekday 1号是周几
     * @param iEndDay 这月几天
     */
    private void initMonth(int iweekday, int iEndDay) {

        monthList = new ArrayList<>();
        for(int i=0;i<12;i++){
            monthList.add(new MonthText(i+1+"月"));
        }
        adapter = new MonthAdapter(mContext,monthList);
        hlv_month.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        List<RepaymentDate> list = new ArrayList<>();
        for(int i = 1; i < 50; i ++){
            if (i <= 7) {
                if (i == 1){
                    list.add(new RepaymentDate("周7"));
                } else {
                    list.add(new RepaymentDate("周" + (i-1)));
                }
            } else if (i > 7 && i <= iweekday+7) {
                list.add(new RepaymentDate(""));
            } else if (i > iweekday+7 && i <= iweekday+7+iEndDay) {
                list.add(new RepaymentDate(i-7-iweekday+""));
            } else {
                list.add(new RepaymentDate(""));
            }
        }


        //TODO 测试数据
        for (RepaymentDate date : list) {
            if ("12".equals(date.getStrDate())){
                date.setStrType("1");
            } else if ("15".equals(date.getStrDate())) {
                date.setStrType("2");
            }
        }

        DateAdapter ad = new DateAdapter(mContext,list);
        gv_date.setAdapter(ad);
        ad.notifyDataSetChanged();
    }

    /**
     * 月份类
     */
    class MonthText {
        private String strMonth = "";
        private boolean isSelect = false;

        MonthText(String month) {
            strMonth = month;
        }

        public String getStrMonth() {
            return strMonth;
        }

        public void setStrMonth(String strMonth) {
            this.strMonth = strMonth;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }
    }

    /**
     * 月份adapter
     */
    class MonthAdapter extends BaseAdapter{

        private List<MonthText> monthList;
        private Context mContext;

        MonthAdapter(Context c, List<MonthText> list){
            monthList = list;
            mContext = c;
        }

        @Override
        public int getCount() {
            return monthList.size();
        }

        @Override
        public Object getItem(int i) {
            return monthList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            final ViewHolder holder;
            if (view == null) {
                view = LayoutInflater.from(mContext).inflate(R.layout.v3_item_calender_month,null);
                holder = new ViewHolder();
                holder.tvMonth = (TextView)view.findViewById(R.id.tv_month);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            final MonthText item = monthList.get(i);
            holder.tvMonth.setText(item.getStrMonth());
            if (item.isSelect) {
                holder.tvMonth.setTextColor(getColor(R.color.version3_home_red));
            } else {
                holder.tvMonth.setTextColor(getColor(R.color.black));
            }
            holder.tvMonth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for(MonthText m : monthList){
                        m.setSelect(false);
                    }
                    monthList.get(i).setSelect(true);
                    adapter.notifyDataSetChanged();

                    initMonthData(i+1);
                }
            });
            return view;
        }

        class ViewHolder{
            TextView tvMonth;
        }
    }

    /**
     * 回款日期类
     */
    class RepaymentDate{
        private String strDate;
        private String strType = "0";     //  0-无数据 1-未回款  2-已回款

        RepaymentDate(String date){
            strDate = date;
        }

        public String getStrDate() {
            return strDate;
        }

        public void setStrDate(String strDate) {
            this.strDate = strDate;
        }

        public String getStrType() {
            return strType;
        }

        public void setStrType(String strType) {
            this.strType = strType;
        }
    }

    /**
     * 回款日期adapter
     */
    class DateAdapter extends BaseAdapter{

        private List<RepaymentDate> dateList;
        private Context mContext;

        DateAdapter(Context c, List<RepaymentDate> list){
            dateList = list;
            mContext = c;
        }

        @Override
        public int getCount() {
            return dateList.size();
        }

        @Override
        public Object getItem(int i) {
            return dateList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            final ViewHolder holder;
            if (view == null) {
                view = LayoutInflater.from(mContext).inflate(R.layout.v3_item_calendar,null);
                holder = new ViewHolder();
                holder.tv_text = view.findViewById(R.id.tv_text);
                holder.iv_hui = view.findViewById(R.id.iv_hui);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            final RepaymentDate item = dateList.get(i);
            String strDate = "";
            String string = item.getStrDate();
            if (string.contains("周")){
                string = string.substring(1,2);
                switch (string){
                    case "1":{
                        strDate = "一";
                    }break;
                    case "2":{
                        strDate = "二";
                    }break;
                    case "3":{
                        strDate = "三";
                    }break;
                    case "4":{
                        strDate = "四";
                    }break;
                    case "5":{
                        strDate = "五";
                    }break;
                    case "6":{
                        strDate = "六";
                    }break;
                    case "7":{
                        strDate = "日";
                    }break;
                }
            } else {
                strDate = item.getStrDate().toString();
            }

            holder.tv_text.setText(strDate);

            if ("1".equals(item.getStrType())) {
                //显示红色
                holder.iv_hui.setVisibility(View.VISIBLE);
                holder.iv_hui.setBackgroundResource(R.drawable.v3_icon_calendar_now);
            } else if ("2".equals(item.getStrType())) {
                //显示灰色
                holder.iv_hui.setVisibility(View.VISIBLE);
                holder.iv_hui.setBackgroundResource(R.drawable.v3_icon_calendar_past);
            } else {
                holder.iv_hui.setVisibility(View.GONE);
            }


            return view;
        }

        class ViewHolder{
            TextView tv_text;
            ImageView iv_hui;
        }
    }

    /**
     * 获取某月有几天
     * @param iYear
     * @param iMonth
     * @return
     */
    private int getDaysOfMonth(int iYear, int iMonth){
        switch (iMonth) {
            case 1:{}
            case 3:{}
            case 5:{}
            case 7:{}
            case 8:{}
            case 10:{}
            case 12:{
                return 31;
            }
            case 2:{
                if (isLeapYear(iYear)){
                    return 29;
                } else {
                    return 28;
                }
            }
            case 4:{}
            case 6:{}
            case 9:{}
            case 11:{
                return 30;
            }
        }
        return 0;
    }

    /**
     * 判断闰年
     * @param iYear
     * @return
     */
    private boolean isLeapYear(int iYear) {
        if (iYear % 100 == 0) {
            if (iYear % 400 == 0) {
                return true;
            }
        } else {
            if (iYear % 4 == 0) {
                return true;
            }
        }
        return false;
    }
}
