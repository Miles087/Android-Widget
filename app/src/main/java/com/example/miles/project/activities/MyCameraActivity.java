package com.example.miles.project.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageView;

import com.example.miles.project.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Miles on 2018/2/27.
 */

public class MyCameraActivity extends Activity {

    private ImageView ivPicture;

    private String strFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_capture);
        ivPicture = findViewById(R.id.iv_image);
        strFilePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        strFilePath = strFilePath + "/" + "myPicture.png";


        ivPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 上传图片文件

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void startCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 233);
    }

    public void startAnotherCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //由于涉及到SD CARD存储，需要注册权限
        if (Build.VERSION.SDK_INT >= 23) {
            PackageManager pm = getPackageManager();
            boolean permission = (PackageManager.PERMISSION_GRANTED ==
                    pm.checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", "com.example.miles.project"));
            if (!permission) {
                //没权限
                String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
                ActivityCompat.requestPermissions(this, mPermissionList, 123);
                return;
            }
        }
        Uri photoUri = Uri.fromFile(new File(strFilePath));
        // android 7.0要这样写
        if (Build.VERSION.SDK_INT >= 25){
            photoUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", new File(strFilePath));
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        startActivityForResult(intent, 2333);
    }

    public void customCamera(View view) {
        startActivity(new Intent(this, CustomCameraActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 233: {
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    ivPicture.setImageBitmap(bitmap);
                }
                break;
                case 2333: {
                    FileInputStream fis = null;
                    try {
                        fis = new FileInputStream(strFilePath);
                        Bitmap bitmap = BitmapFactory.decodeStream(fis);
                        ivPicture.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }



//                    new File(strFilePath)

                }
                break;
            }
        }
    }
}
