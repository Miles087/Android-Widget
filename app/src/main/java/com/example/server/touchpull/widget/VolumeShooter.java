package com.example.server.touchpull.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Server on 2018/1/15.
 */

public class VolumeShooter extends View {
    public VolumeShooter(Context context) {
        super(context);
    }

    public VolumeShooter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VolumeShooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public VolumeShooter(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
