package com.example.server.touchpull;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.server.touchpull.widget.TouchPullView;

/**
 * Created by Server on 2018/1/22.
 */

public class DragPullActivity extends Activity {
    private static final float TOUCH_MOVE_MAX_Y = 600;
    private float mTouchMoveStartY;
    private float mTouchMoveEndY;
    TouchPullView mTouchPullView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_pull);
        mTouchPullView = (TouchPullView) findViewById(R.id.touch_pull);
        findViewById(R.id.activity_main).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //得到意图
                switch (motionEvent.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:{
                        Log.i("MotionEvent","ACTION_DOWN");
                        mTouchMoveStartY = motionEvent.getY();
                        return true;
                    }
                    case MotionEvent.ACTION_UP:{
                        Log.i("MotionEvent","ACTION_UP");
                        mTouchPullView.release();
                    }break;
                    case MotionEvent.ACTION_MOVE:{
                        Log.i("MotionEvent","ACTION_MOVE");
                        float y = motionEvent.getY();
                        if ( y >= mTouchMoveEndY) {
                            float moveSize = y - mTouchMoveStartY;
                            float progress = moveSize >= TOUCH_MOVE_MAX_Y?1:moveSize/TOUCH_MOVE_MAX_Y;
                            mTouchPullView.setProgress(progress);
                        }
                        return true;
                    }
                    default:{}break;
                }
                return false;
            }
        });
    }
}
