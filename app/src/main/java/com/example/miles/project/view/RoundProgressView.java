package com.example.miles.project.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.miles.project.R;

/**
 * Description: 圆形进度条
 * Author:      Created by Miles Wang
 * CreatedDate: 2020-11-25 15:36
 * Email:       icy-star@qq.com
 **/

public class RoundProgressView extends View implements View.OnTouchListener {

    private Context mContext;
    // 宽，高
    private float width;
    private float height;
    private float radius;

    // 基础半圆的画笔
    private Paint mPaint;
    // 进度的画笔
    private Paint mPdgreeScale;
    // 起始点
    private Paint mStartRound;
    // 结束点
    private Paint mEndRound;
    // 框
    private RectF bigRect;
    private RectF dgreeScale;

    public RoundProgressView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public RoundProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    private void init() {
        // 初始化画笔、Rect
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mContext.getColor(R.color.big_tound));
        mPaint.setStrokeWidth(30.0f);
        // 进度条画笔
        mPdgreeScale = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPdgreeScale.setStyle(Paint.Style.STROKE);
        mPdgreeScale.setColor(mContext.getColor(R.color.dgree_scale));
        mPdgreeScale.setStrokeWidth(10.0f);
        // 起始点
        mStartRound = new Paint(Paint.ANTI_ALIAS_FLAG);
        mStartRound.setStyle(Paint.Style.FILL);
        mStartRound.setColor(Color.MAGENTA);
        //结束点
        mEndRound = new Paint(Paint.ANTI_ALIAS_FLAG);
        mEndRound.setStyle(Paint.Style.FILL);
        mEndRound.setColor(Color.LTGRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float tRadius = width / 2;
        float center = width / 2;
        bigRect = new RectF(center - radius, center - radius, center + radius, center + radius);
        dgreeScale = new RectF(center - radius + 40, center - radius + 40, center + radius + 40, center + radius + 40);

        /** 30能被360整除 **/
        int iDrg = 30;
        /** 整个圆 **/
        canvas.drawArc(bigRect, -iDrg * 8, iDrg * 10, false, mPaint);
        /** 大刻度 **/
        canvas.save();
        canvas.rotate(-iDrg * 5, tRadius, tRadius);
        for (int i = 0; i < 25; i++) {
            canvas.drawLine(tRadius, tRadius / 2 - 60, tRadius, tRadius / 2 - 30, mPdgreeScale);
            canvas.rotate(12.5f, tRadius, tRadius);
        }
        canvas.restore();

        /**
         *  圆心坐标：(x0,y0)
         *  半径：r
         *  角度：a
         *  则圆上任一点为：（x1,y1）
         *  x1   =   x0   +   r   *   cos( a )
         *  y1   =   y0   +   r   *   sin( a )
         */

        /**
         * 取起始点位置
         **/


        /** 取结束点位置 **/
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                Toast.makeText(mContext, "按下了", Toast.LENGTH_SHORT).show();
            }
            break;
            case MotionEvent.ACTION_UP: {
                Toast.makeText(mContext, "抬起了", Toast.LENGTH_SHORT).show();
            }
            break;
        }
        return false;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        radius = width / 3;
    }
}