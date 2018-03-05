package com.example.miles.project;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Miles on 2018/1/18.
 */

public class VolumeActivity extends Activity {
    ImageView iv_volume;

    private long start = 0;
    private long stop = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);

        iv_volume = (ImageView)findViewById(R.id.iv_volume);

        findViewById(R.id.activity_main).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //得到意图
                switch (motionEvent.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:{
                        Log.i("MotionEvent","ACTION_DOWN");
                        start = System.currentTimeMillis();
                        stop = System.currentTimeMillis();
                        timer.start();
                        return true;
                    }
                    case MotionEvent.ACTION_UP:{
                        Log.i("MotionEvent","ACTION_UP");
                        timer.cancel();
                        stop = System.currentTimeMillis();
                        Log.i("start+_+stop",stop +"     " + start);
                        Log.i("caculate",stop - start + "");
                        float fPressTime = stop - start;
                        Log.i("result",fPressTime + "");
                        setImageRotate(false);
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private CountDownTimer timer = new CountDownTimer(5000,100) {
        @Override
        public void onTick(long l) {
            stop = System.currentTimeMillis();
            setImageRotate(true);
        }

        @Override
        public void onFinish() {

        }
    };

    private void setImageRotate(boolean bRollUp){
        if (bRollUp) {
            float fPressTime = stop - start;
            if (fPressTime <= 5000) {
                Log.i("result", fPressTime + "");
            } else {
                fPressTime = 5000;
            }
            float f = fPressTime / 111;
            ObjectAnimator animator = ObjectAnimator.ofFloat(iv_volume, "rotation", -f);
            animator.setDuration(100);
            animator.start();
        } else {
            //回到原位
            ObjectAnimator animator = ObjectAnimator.ofFloat(iv_volume, "rotation", 0f);
            animator.setDuration(500);
            animator.start();
        }
    }
}
