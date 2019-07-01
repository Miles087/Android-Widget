package com.example.miles.project.activities;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.miles.project.R;

/**
 * Created by Miles on 2018/1/18.
 */

public class VolumeActivity extends Activity {
    ImageView iv_volume;
    RelativeLayout rl_content;

    private long start = 0;
    private long stop = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);

        iv_volume = (ImageView)findViewById(R.id.iv_volume);
        rl_content = findViewById(R.id.rl_content);

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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
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
            ObjectAnimator animatorReset = ObjectAnimator.ofFloat(iv_volume, "rotation", 0f);
            animatorReset.setDuration(500);
            animatorReset.start();



            float iOriX = 0;
            float iOriY = 0;
            float iCtrX = rl_content.getWidth()/2;
            float iCtrY = 0;
            float iTerX = rl_content.getWidth()/2+iv_volume.getWidth();
            float iTerY = 0-rl_content.getHeight();

            Path bezirPath = new Path();
            bezirPath.moveTo(iOriX,iOriY);
            bezirPath.quadTo(iCtrX,iCtrY,iTerX,iTerY);
            ObjectAnimator bezirAnimator = ObjectAnimator.ofFloat(iv_volume,"translationX","translationY",bezirPath);

            AnimatorSet set = new AnimatorSet();
            set.play(bezirAnimator).with(animatorReset);
            set.setDuration(2000);
            set.start();
        }
    }
}
