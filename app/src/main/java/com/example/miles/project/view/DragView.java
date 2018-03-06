package com.example.miles.project.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.miles.project.widget.CacheManager;
import com.example.miles.project.widget.ScreenUtil;

/**
 * Created by Miles on 2018/3/5.
 */

@SuppressLint("AppCompatCustomView")
public class DragView extends ImageView {

    private int iWidth;
    private int iHeight;
    private int iScreenWidth;
    private int iScreenHeight;
    private Context mContext;
    private boolean bIsDrag = false;
    private float downX;
    private float downY;
    int l,r,t,b;

    public boolean isDrag(){
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
    public void layout(int l, int t, int r, int b) {
        if (isDrag()){
            super.layout(l, t, r, b);
            return;
        }
        String strLeft = "";
        String strRight = "";
        String strTop = "";
        String strButtom = "";
        try {
            strLeft = CacheManager.readObject("DrawViewLeft").toString();
            strRight = CacheManager.readObject("DrawViewRight").toString();
            strTop = CacheManager.readObject("DrawViewTop").toString();
            strButtom = CacheManager.readObject("DrawViewButtom").toString();
            l = Integer.parseInt(strLeft);
            t = Integer.parseInt(strTop);
            r = Integer.parseInt(strRight);
            b = Integer.parseInt(strButtom);
        }catch (Exception e){}
        super.layout(l, t, r, b);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        iWidth = getMeasuredWidth();
        iHeight = getMeasuredHeight();
        iScreenWidth = ScreenUtil.getScreenWidth(mContext);
        iScreenHeight = ScreenUtil.getScreenHeight(mContext) - ScreenUtil.getStatusHeight(mContext);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        super.onTouchEvent(event);
        if (this.isEnabled()){
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:{
                    bIsDrag = false;
                    downX = event.getX();
                    downY = event.getY();
                }break;
                case MotionEvent.ACTION_MOVE:{
                    Log.i("MOVVVVVVVVVVVVE","ACTION_MOVE");
                    final float xDistance = event.getX() - downX;
                    final float yDistance = event.getY() - downY;
                    //当任意方向滑动距离大于10的时候才算是拖动
                    if (Math.abs(xDistance) > 10 || Math.abs(yDistance) > 10) {
                        Log.i("MOVVVVVVVVVVVVE","draggggggggggggggg MOVE");
                        bIsDrag = true;
                        l = (int)(getLeft() + xDistance);
                        r = l + iWidth;
                        t = (int)(getTop() + yDistance);
                        b = t + iHeight;
                        //不划出边界判断,这里写的是全屏的，如果是固定区域，则需要得到父控件的宽高位置后再做处理
                        if (l < 0){
                            l = 0;
                            r = l + iWidth;
                        } else if (r > iScreenWidth) {
                            r = iScreenWidth;
                            l = r - iWidth;
                        }
                        if (t < 0){
                            t = 0;
                            b = t + iHeight;
                        } else if (b > iScreenHeight) {
                            b = iScreenHeight;
                            t = b - iHeight;
                        }
                        this.layout(l,t,r,b);
                    } else {
                        if (Math.abs(xDistance) == 0 || Math.abs(yDistance) == 0) {
                            Toast.makeText(mContext, "你想去掉马赛克吗？ →_→", Toast.LENGTH_LONG).show();
                        }
                    }
                }break;
                case MotionEvent.ACTION_UP:{
                    bIsDrag = false;
                    CacheManager.writeObject(l,"DrawViewLeft");
                    CacheManager.writeObject(t,"DrawViewTop");
                    CacheManager.writeObject(r,"DrawViewRight");
                    CacheManager.writeObject(b,"DrawViewButtom");
                    setPressed(false);
                }break;
                case MotionEvent.ACTION_CANCEL:{
                    bIsDrag = false;
                    setPressed(false);
                }break;
            }
            return true;
        }
        return false;
    }
}
