package com.example.miles.project.activities;


import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.example.miles.project.R;

import java.io.IOException;

/**
 * Created by Server on 2018/3/15.
 */

public class CustomCameraActivity extends Activity implements SurfaceHolder.Callback{

    private Camera mCamera;
    private SurfaceView sv_preview;
    private SurfaceHolder mSurfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_camera);
        sv_preview = (SurfaceView)findViewById(R.id.sv_preview);
        mSurfaceHolder = sv_preview.getHolder();
        mSurfaceHolder.addCallback(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCamera == null) {
            mCamera = getCamera();
            if (mSurfaceHolder != null) {
                setStartPreview(mCamera,mSurfaceHolder);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
    }

    public void capture(View view){

    }

    /**
     * 获取Camera
     * @return
     */
    private Camera getCamera(){
        Camera mCamera;
        try {
            mCamera = Camera.open();
        }catch (Exception e){
            mCamera = null;
            e.printStackTrace();
        }
        return mCamera;
    }

    /**
     * 预览
     */
    private void setStartPreview(Camera camera, SurfaceHolder holder){
        try {
            camera.setPreviewDisplay(holder);
            camera.setDisplayOrientation(90);   //设置相机旋转角度
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放Camera
     */
    private void releaseCamera(){
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        setStartPreview(mCamera,mSurfaceHolder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        mCamera.stopPreview();
        setStartPreview(mCamera,mSurfaceHolder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        releaseCamera();
    }
}
