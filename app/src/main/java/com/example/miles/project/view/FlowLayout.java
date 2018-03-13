package com.example.miles.project.view;

import android.content.Context;
import android.icu.util.Measure;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Server on 2018/3/12.
 */

public class FlowLayout extends ViewGroup {

    private List<List<View>> mAllViews = new ArrayList<List<View>>();
    private List<Integer> mLineHeight = new ArrayList<>();

    public FlowLayout(Context context) {
        this(context,null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);

        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);


        //wrap_content
        int width = 0;
        int height = 0;
        //记录每一行的宽度和高度
        int lineWidth = 0;
        int lineHeight = 0;
        int cCount = getChildCount();
        for(int i = 0; i < cCount; i++){
            View child = getChildAt(i);
            //测量子view的宽和高
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            MarginLayoutParams lp = (MarginLayoutParams)child.getLayoutParams();
            //子view占据的宽度
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            //子view占据的高度
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            if ((lineWidth + childWidth) > sizeWidth - getPaddingLeft() - getPaddingRight()){
                //对比得到最大宽度
                width = Math.max(width,lineWidth);
                //重置lineWidth
                lineWidth = childWidth;
                //换行
                height += lineHeight;
                lineHeight = childHeight;
            } else {
                //未换行的情况
                //叠加行宽
                lineWidth += childWidth;
                //得到当前行最大高度
                lineHeight = Math.max(lineHeight,childHeight);
            }
            //最后一个控件
            if (i == cCount - 1){
                width = Math.max(lineWidth,width);
                height += lineHeight;
            }
        }
        setMeasuredDimension(
                modeWidth == MeasureSpec.AT_MOST?width:sizeWidth + getPaddingLeft() + getPaddingRight(),
                modeHeight == MeasureSpec.AT_MOST?height:sizeHeight + getPaddingTop() + getPaddingBottom());



        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean bChanged, int l, int t, int r, int b) {
        mAllViews.clear();
        mLineHeight.clear();

        //当前ViewGroup的宽度
        int width = getWidth();

        int lineWidth = 0;
        int lineHeight = 0;

        List<View> lineViews = new ArrayList<View>();

        int cCount = getChildCount();

        for(int i = 0; i < cCount; i++){
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            if ((childHeight + lineWidth + lp.leftMargin + lp.rightMargin) > width - getPaddingLeft() - getPaddingRight()){
                mLineHeight.add(lineHeight);
                mAllViews.add(lineViews);

                lineWidth = 0;
                lineHeight = childHeight + lp.topMargin + lp.bottomMargin;
                //重置view集合
                lineViews = new ArrayList<>();
            }
            lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
            lineHeight = Math.max(lineHeight,childHeight + lp.topMargin + lp.bottomMargin);
            lineViews.add(child);
        }
        //处理最后一行
        mLineHeight.add(lineHeight);
        mAllViews.add(lineViews);


        //设置子view的位置
        int left = getPaddingLeft();
        int top = getPaddingTop();

        int lineNumber = mAllViews.size();

        for (int i = 0; i < lineNumber; i++){
            lineViews = mAllViews.get(i);
            lineHeight = mLineHeight.get(i);

            for (int j = 0; j < lineViews.size(); j++){
                View child = lineViews.get(j);
                //判断view显示状态
                if (child.getVisibility() == View.GONE){
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc = lc + child.getMeasuredWidth();
                int bc = tc + child.getMeasuredHeight();

                child.layout(lc,tc,rc,bc);
                left += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            }
            left = getPaddingLeft();
            top += lineHeight;
        }








    }

    /**
     * 与当前ViewGroup对应的LayoutParameters
     * @param attrs
     * @return
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }
}
