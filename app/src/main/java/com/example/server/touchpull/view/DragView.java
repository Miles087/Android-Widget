package com.example.server.touchpull.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Server on 2018/3/5.
 */

public class DragView extends ImageView {

    private int iWidth;
    private int iHeight;
    private int iScreenWidth;
    private int iScreenHeight;
    private Context mContext;
    private boolean bIsDrag = false;

    public boolean isDrat(){
        return this.bIsDrag;
    }

    public DragView(Context context) {
        super(context);
        this.mContext = context;
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        iWidth = getMeasuredWidth();
        iHeight = getMeasuredHeight();
        iScreenWidth =
    }
}
