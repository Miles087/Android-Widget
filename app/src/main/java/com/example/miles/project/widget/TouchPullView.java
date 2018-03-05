package com.example.miles.project.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import com.example.miles.project.R;

/**
 * Created by Miles on 2018/1/15.
 */

public class TouchPullView extends View {

    private Paint mCirclePaint;        //画笔
    private float mCircleRadius = 50;        //半径
    private float mCirclePointX, mCirclePointY;

    // 可拖动的高度
    private int mDragHeight = 300;

    // 目标宽度
    private int mTargetWidth = 400;
    // 贝塞尔曲线的路径以及画笔
    private Path mPath = new Path();
    private Paint mPathPaint;
    // 重心点最终高度，决定控制点的Y坐标
    private int mTargetGravityHeight = 10;
    // 角度变换  0 ~ 135
    private int mTangentAngle = 105;
    //由慢到快的插值器
    private Interpolator mProgressInterpolator = new DecelerateInterpolator();
    // 角度的插值器
    private Interpolator mTangentAngleInterpolator;

    private Drawable mContent = null;
    private int mContentMargin = 0;

    //进度
    private float mProgress;

    public TouchPullView(Context context) {
        super(context);
        init(null);
    }

    public TouchPullView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TouchPullView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TouchPullView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }
    /*
        初始化
     */
    private void init(AttributeSet attrs){
        //得到用户设置的参数
        final Context context = getContext();
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TouchPullView,0,0);
        int color = array.getColor(R.styleable.TouchPullView_pColor, 0x20000000);
        mCircleRadius = array.getDimension(R.styleable.TouchPullView_pRadius, mCircleRadius);
        mDragHeight = array.getDimensionPixelOffset(R.styleable.TouchPullView_pDragHeight, mDragHeight);
        mTangentAngle = array.getInteger(R.styleable.TouchPullView_pTangentAngle, 100);
        mTargetWidth = array.getDimensionPixelOffset(R.styleable.TouchPullView_pTargetWidth, mTargetWidth);
        mTargetGravityHeight = array.getDimensionPixelOffset(R.styleable.TouchPullView_pTargetGravityHeight, mTargetGravityHeight);
        mContent = array.getDrawable(R.styleable.TouchPullView_pContentDrawable);
        mContentMargin = array.getDimensionPixelOffset(R.styleable.TouchPullView_pContentDrawableMargin, 0);
        //销毁
        array.recycle();
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        //初始化路径部分画笔
        p = new Paint(Paint.ANTI_ALIAS_FLAG);
        //抗锯齿
        p.setAntiAlias(true);
        //防抖动
        p.setDither(true);
        //填充方式
        p.setStyle(Paint.Style.FILL);
        //颜色
        p.setColor(color);
        mPathPaint = p;

        //抗锯齿
        p.setAntiAlias(true);
        //防抖动
        p.setDither(true);
        //填充方式
        p.setStyle(Paint.Style.FILL);
        //颜色
        p.setColor(color);
        mCirclePaint = p;
        // 切角 路径插值器
        mTangentAngleInterpolator = PathInterpolatorCompat.create((mCircleRadius * 2.0f)/mDragHeight,
                90.0f / mTangentAngle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 进行基础坐标参数系改变
        int count = canvas.save();
        float tranX = (getWidth()-getValueByLine(getWidth(),mTargetWidth,mProgress))/2;
        canvas.translate(tranX,0);

        // 画贝塞尔曲线
        canvas.drawPath(mPath,mPathPaint);

        // 画圆
        canvas.drawCircle(mCirclePointX,
                mCirclePointY,
                mCircleRadius,
                mCirclePaint);

        Drawable drawable = mContent;
        if (drawable != null) {
            canvas.save();
            //剪切矩形区域
            canvas.clipRect(drawable.getBounds());
            //绘制drawable
            drawable.draw(canvas);
            canvas.restore();
        }
        canvas.restoreToCount(count);
    }

    /**
     *  当进行测量时触发
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int measureWidth;
        int measureHeight;

        int minHeight = (int) (mDragHeight * mProgress + 0.5 + getPaddingTop() + getPaddingBottom());
        int minWidth = (int) (2 * mCircleRadius + getPaddingLeft() + getPaddingRight());


        if (widthMode == MeasureSpec.EXACTLY) {
            measureWidth = width;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            measureWidth = Math.min(width,minWidth);
        } else {
            measureWidth = width;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            measureHeight = height;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            measureHeight = Math.min(height,minHeight);
        } else {
            measureHeight = height;
        }

        // 设置测量的高度宽度
        setMeasuredDimension(measureWidth,measureHeight);

    }

    /**
     * 当大小改变时触发
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // 当高度变化时，进行路径更新
        updatePathLayout();

    }

    /**
     * 更新路径相关操作
     */
    private void updatePathLayout() {
        // 获取进度
//        final float progress = mProgress;
        final float progress = mProgressInterpolator.getInterpolation(mProgress);
        final Path path = mPath;
        // 重置
        path.reset();
        // 获取可绘制区域 高度宽度
        final float w = getValueByLine(getWidth(),mTargetWidth, mProgress);
        final float h = getValueByLine(0,mDragHeight,mProgress);
        // X对称轴的参数 圆心的X坐标
        final float cPointX = w / 2;
        // 圆的半径
        final float cRaduis = mCircleRadius;
        // 圆心中点Y的坐标
        final float cPointY = h - cRaduis;
        // 控制点结束Y值
        final float endControlY = mTargetGravityHeight;
        // 更新圆的坐标
        mCirclePointX = cPointX;
        mCirclePointY = cPointY;
        // 复位
        path.reset();
        path.moveTo(0,0);
        //左边部分 结束点 和 控制点
        float lEndPointX, lEndPointY;
        float lControlPointX, lControlPointY;
        //当前切线弧度
        float angle = mTangentAngle * mTangentAngleInterpolator.getInterpolation(progress);
        double radian = Math.toRadians(angle);
//        double radian = Math.toRadians(getValueByLine(0,mTangentAngle,progress));
        float x = (float) (Math.sin(radian) * mCircleRadius);
        float y = (float) (Math.cos(radian) * mCircleRadius);
        //结束点
        lEndPointX = cPointX - x;
        lEndPointY = cPointY + y;
        //控制点
        lControlPointY = getValueByLine(0,endControlY,progress);
        float tHeight = lEndPointY - lControlPointY;
        //控制点与X坐标距离
        float tWidth = (float) (tHeight / Math.tan(radian));
        lControlPointX = lEndPointX - tWidth;
        //贝塞尔曲线 左边
        path.quadTo(lControlPointX,lControlPointY,lEndPointX,lEndPointY);
        //连接到右边
        path.lineTo(cPointX+(cPointX-lEndPointX),lEndPointY);
        //贝塞尔曲线 右边
        path.quadTo(cPointX + (cPointX - lControlPointX), lControlPointY, w, 0);
        //更新内容部分drawable
        updateContentLayout(cPointX,cPointY,cRaduis);
    }

    /**
     * 对内容部分进行测量并设置
     * @param cx    圆心X
     * @param cy    圆心Y
     * @param radius    半径
     */
    private void updateContentLayout(float cx, float cy, float radius) {
        Drawable drawable = mContent;
        if (drawable != null) {
            int margin = mContentMargin;
            int l = (int) (cx - radius + margin);
            int r= (int) (cx + radius - margin);
            int t= (int) (cy - radius + margin);
            int b= (int) (cy + radius - margin);
            drawable.setBounds(l, t, r, b);
        }
    }

    /**
     * 获取当前值
     * @param start 起始值
     * @param end    结束值
     * @param progress  进度
     * @return  当前进度的值
     */
    private float getValueByLine (float start, float end, float progress) {
        return start + ( end - start ) * progress;
    }

    /**
     * 设置进度
     * @param fProgress
     */
    public void setProgress(float fProgress) {
        Log.i("PROGRESS","P"+fProgress);
        mProgress = fProgress;
        requestLayout();        //请求重新进行测量
    }

    private ValueAnimator valueAnimator;

    public void release() {
        if (valueAnimator == null) {
            final ValueAnimator animator = ValueAnimator.ofFloat(mProgress,0f);
            animator.setInterpolator(new DecelerateInterpolator());
            animator.setDuration(400);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    Object val = valueAnimator.getAnimatedValue();
                    if (val instanceof Float) {
                        setProgress((float)val);
                    }
                }
            });
            valueAnimator = animator;
        } else {
            valueAnimator.cancel();
            valueAnimator.setFloatValues(mProgress,0f);
        }
        valueAnimator.start();
    }
}
