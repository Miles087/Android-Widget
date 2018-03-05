package com.example.miles.project.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.icu.util.TimeZone;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.format.Time;
import android.util.AttributeSet;
import android.view.View;

import com.example.miles.project.R;


/**
 * Created by Miles on 2018/1/15.
 */

public class ClockView extends View {

    //记录当前时间
    private Time mCalendar;
    //3张图片资源
    private Drawable mHourHand;
    private Drawable mMinuteHand;
    private Drawable mClockBG;
    //表盘宽高
    private int mWidth;
    private int mHeight;
    //记录是否加入到Windows中
    private boolean mAttached;
    //分钟
    private float mMinute;
    //小时
    private float mHour;
    //跟踪view的尺寸是否变化
    private Boolean mChanged;

    public ClockView(Context context) {
        super(context);

        final Resources r = context.getResources();

        if (null == mClockBG) {
            mClockBG = context.getDrawable(R.drawable.clock_background);
        }
        if (null == mHourHand) {
            mHourHand = context.getDrawable(R.drawable.hour);
        }
        if (null == mMinuteHand) {
            mMinuteHand = context.getDrawable(R.drawable.minute);
        }

        mCalendar = new Time();

        mWidth = mClockBG.getIntrinsicWidth();
        mHeight = mClockBG.getIntrinsicHeight();
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);

        final Resources r = context.getResources();

        if (null == mClockBG) {
            mClockBG = context.getDrawable(R.drawable.clock_background);
        }
        if (null == mHourHand) {
            mHourHand = context.getDrawable(R.drawable.hour);
        }
        if (null == mMinuteHand) {
            mMinuteHand = context.getDrawable(R.drawable.minute);
        }

        mCalendar = new Time();

        mWidth = mClockBG.getIntrinsicWidth();
        mHeight = mClockBG.getIntrinsicHeight();
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final Resources r = context.getResources();

        if (null == mClockBG) {
            mClockBG = context.getDrawable(R.drawable.clock_background);
        }
        if (null == mHourHand) {
            mHourHand = context.getDrawable(R.drawable.hour);
        }
        if (null == mMinuteHand) {
            mMinuteHand = context.getDrawable(R.drawable.minute);
        }

        mCalendar = new Time();

        mWidth = mClockBG.getIntrinsicWidth();
        mHeight = mClockBG.getIntrinsicHeight();
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        final Resources r = context.getResources();

        if (null == mClockBG) {
            mClockBG = context.getDrawable(R.drawable.clock_background);
        }
        if (null == mHourHand) {
            mHourHand = context.getDrawable(R.drawable.hour);
        }
        if (null == mMinuteHand) {
            mMinuteHand = context.getDrawable(R.drawable.minute);
        }

        mCalendar = new Time();

        mWidth = mClockBG.getIntrinsicWidth();
        mHeight = mClockBG.getIntrinsicHeight();
    }


    /*
     * 如果viewGroup给的尺寸比较小，按照最小比例去缩放
     * 避免表盘变形
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        float hScale = 1.0f;
        float vScale = 1.0f;

        if (widthMode != MeasureSpec.UNSPECIFIED && widthSize < mWidth) {
            hScale = (float)widthSize / (float)mWidth;
        }
        if (heightMeasureSpec != MeasureSpec.UNSPECIFIED && heightSize < mHeight ) {
            vScale = (float)heightSize / (float)mHeight;
        }
        float scale = Math.min(vScale,hScale);
        setMeasuredDimension(resolveSizeAndState((int)(mWidth * scale), widthMeasureSpec, 0),
                             resolveSizeAndState((int)(mHeight * scale), heightMeasureSpec, 0));
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w,h,oldw,oldh);
        mChanged = true;
    }

    private void onTimeChanged() {
        mCalendar.setToNow();
        int hour = mCalendar.hour;
        int minute = mCalendar.minute;
        int second = mCalendar.second;


        mMinute = minute + second / 60.0f;
        mHour = hour + mMinute / 60.0f;
        mChanged = true;
    }

    private final BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_TIMEZONE_CHANGED)) {
                String tz = intent.getStringExtra("time-zone");
                mCalendar = new Time(TimeZone.getTimeZone(tz).getID());
            }
            onTimeChanged();
            //重绘
            invalidate();
        }
    };

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!mAttached) {
            mAttached = true;
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_TIME_CHANGED);
            filter.addAction(Intent.ACTION_TIME_TICK);
            filter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
            getContext().registerReceiver(mIntentReceiver, filter);
        }
        mCalendar = new Time();
        onTimeChanged();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mAttached) {
            getContext().unregisterReceiver(mIntentReceiver);
            mAttached = false;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        boolean changed = mChanged;
        if (changed) {
            mChanged = false;
        }
        int availableWidth = super.getRight() - super.getLeft();
        int availableHeigh = super.getBottom() - super.getTop();

        int x = availableWidth / 2;
        int y = availableHeigh / 2;

        final Drawable clockBG = mClockBG;
        int w = clockBG.getIntrinsicWidth();
        int h = clockBG.getIntrinsicHeight();
        boolean scaled = false;

        if(availableWidth < w || availableHeigh < h) {
            scaled = true;
            float scale = Math.min((float) availableWidth / (float) w,
                    (float) availableHeigh / (float) h);
            canvas.save();
            canvas.scale(scale, scale, x, y);
        }

        if (changed) {
            clockBG.setBounds(x-(w/2),y-(h/2),x+(w/2),y+(h/2));
        }
        clockBG.draw(canvas);

        canvas.save();

        canvas.rotate(mHour / 12.0f * 360.0f, x, y);
        final Drawable hourHand = mHourHand;
        if (changed) {
            w = hourHand.getIntrinsicWidth();
            h = hourHand.getIntrinsicHeight();
            hourHand.setBounds(x-(w/2),y-(h/2),x+(w/2),y+(h/2));
        }
        hourHand.draw(canvas);
        canvas.restore();

        canvas.save();
        canvas.rotate(mMinute / 60.0f * 360.0f, x, y);
        final Drawable minuteHand = mMinuteHand;
        if (changed) {
            w = minuteHand.getIntrinsicWidth();
            h = minuteHand.getIntrinsicHeight();
            minuteHand.setBounds(x-(w/2),y-(h/2),x+(w/2),y+(h/2));
        }
        minuteHand.draw(canvas);
        canvas.restore();
        if (scaled) {
            canvas.restore();
        }
    }
}
