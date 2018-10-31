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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarActivity extends Activity {

    private ImageView iv_year_left;
    private ImageView iv_year_right;
    private ImageView iv_month_left;
    private ImageView iv_month_right;

    private TextView tv_year;
    private TextView tv_month;

    private Context mContext;
    private GridView gv_date;                       //日期

    private int iYear = 0;
    private int iMonth = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_calendar);

        mContext = this;
        tv_year = findViewById(R.id.tv_year);
        tv_month = findViewById(R.id.tv_month);
        gv_date = findViewById(R.id.gv_date);

        iv_year_left = findViewById(R.id.iv_year_left);
        iv_year_right = findViewById(R.id.iv_year_right);
        iv_month_left = findViewById(R.id.iv_month_left);
        iv_month_right = findViewById(R.id.iv_month_right);


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

        resetDate();


        iv_year_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iYear > 0) {
                    iYear -= 1;
                    initMonthData();
                }
            }
        });
        iv_year_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iYear += 1;
                initMonthData();

            }
        });

        iv_month_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iMonth > 1) {
                    iMonth -= 1;
                    initMonthData();
                } else {
                    iYear -= 1;
                    iMonth = 12;
                    initMonthData();
                }
            }
        });
        iv_month_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iMonth < 12) {
                    iMonth += 1;
                    initMonthData();
                } else {
                    iYear += 1;
                    iMonth = 1;
                    initMonthData();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 当月
     */
    private void resetDate() {
        Calendar CD = Calendar.getInstance();
        iYear = CD.get(Calendar.YEAR);
        iMonth = CD.get(Calendar.MONTH) + 1;
        initMonthData();
    }

    /**
     * 初始化某月的日期
     */
    private void initMonthData() {
        tv_year.setText(iYear + "年");
        tv_month.setText(iMonth + "月");
        Calendar calendar = Calendar.getInstance();
        if (iYear > 0) {
            calendar.set(Calendar.YEAR, iYear);
        }
        if (iMonth > 0) {
            int temp = iMonth - 1;
            calendar.set(Calendar.MONTH, temp);
        }
        calendar.set(Calendar.DAY_OF_MONTH, 1);  //指定日
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int weekDay = 0;
        switch (dayOfWeek) {
            case 1: {
                weekDay = 7;
            }
            break;
            case 2: {
                weekDay = 1;
            }
            break;
            case 3: {
                weekDay = 2;
            }
            break;
            case 4: {
                weekDay = 3;
            }
            break;
            case 5: {
                weekDay = 4;
            }
            break;
            case 6: {
                weekDay = 6;
            }
            break;
            case 7: {
                weekDay = 6;
            }
            break;
        }

        initMonth(weekDay, getDaysOfMonth(iYear, iMonth));
    }

    /**
     * 初始化月份容器  12个月的item
     *
     * @param iweekday 1号是周几
     * @param iEndDay  这月几天
     */
    private void initMonth(int iweekday, int iEndDay) {

        List<RepaymentDate> list = new ArrayList<>();
        for (int i = 1; i < 50; i++) {
            if (iweekday == 7) {
                if (i <= 7) {
                    if (i == 1) {
                        list.add(new RepaymentDate("周7"));
                    } else {
                        list.add(new RepaymentDate("周" + (i - 1)));
                    }
                } else if (i > iweekday + 7 && i <= iweekday + 7 + iEndDay) {
                    list.add(new RepaymentDate(i - 7 - iweekday + ""));
                }
            } else {
                if (i <= 7) {
                    if (i == 1) {
                        list.add(new RepaymentDate("周7"));
                    } else {
                        list.add(new RepaymentDate("周" + (i - 1)));
                    }
                } else if (i > 7 && i <= iweekday + 7) {
                    list.add(new RepaymentDate(""));
                } else if (i > iweekday + 7 && i <= iweekday + 7 + iEndDay) {
                    list.add(new RepaymentDate(i - 7 - iweekday + ""));
                }
            }
        }


        //TODO 测试数据
        for (RepaymentDate date : list) {
            if ("12".equals(date.getStrDate())) {
                date.setStrType("1");
            } else if ("15".equals(date.getStrDate())) {
                date.setStrType("2");
            }
        }

        DateAdapter ad = new DateAdapter(mContext, list);
        gv_date.setAdapter(ad);
        ad.notifyDataSetChanged();
    }

    /**
     * 回款日期类
     */
    class RepaymentDate {
        private String strDate;
        private String strType = "0";     //  0-无数据 1-未回款  2-已回款

        RepaymentDate(String date) {
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
    class DateAdapter extends BaseAdapter {

        private List<RepaymentDate> dateList;
        private Context mContext;

        DateAdapter(Context c, List<RepaymentDate> list) {
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
                view = LayoutInflater.from(mContext).inflate(R.layout.v3_item_calendar, null);
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
            if (string.contains("周")) {
                string = string.substring(1, 2);
                switch (string) {
                    case "1": {
                        strDate = "一";
                    }
                    break;
                    case "2": {
                        strDate = "二";
                    }
                    break;
                    case "3": {
                        strDate = "三";
                    }
                    break;
                    case "4": {
                        strDate = "四";
                    }
                    break;
                    case "5": {
                        strDate = "五";
                    }
                    break;
                    case "6": {
                        strDate = "六";
                    }
                    break;
                    case "7": {
                        strDate = "日";
                    }
                    break;
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

        class ViewHolder {
            TextView tv_text;
            ImageView iv_hui;
        }
    }

    /**
     * 获取某月有几天
     *
     * @param iYear 年份
     * @param iMonth 月份
     * @return
     */
    private int getDaysOfMonth(int iYear, int iMonth) {
        switch (iMonth) {
            case 1: {
            }
            case 3: {
            }
            case 5: {
            }
            case 7: {
            }
            case 8: {
            }
            case 10: {
            }
            case 12: {
                return 31;
            }
            case 2: {
                if (isLeapYear(iYear)) {
                    return 29;
                } else {
                    return 28;
                }
            }
            case 4: {
            }
            case 6: {
            }
            case 9: {
            }
            case 11: {
                return 30;
            }
        }
        return 0;
    }

    /**
     * 判断闰年
     *
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
