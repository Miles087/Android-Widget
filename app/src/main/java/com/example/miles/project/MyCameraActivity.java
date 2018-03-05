package com.example.miles.project;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

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
        ivPicture = (ImageView)findViewById(R.id.iv_image);
        strFilePath = Environment.getExternalStorageDirectory().getPath();
        strFilePath = strFilePath  + "/" + "myPicture.png";
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void startCamera(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,233);
    }

    public void startAnotherCamera (View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //由于涉及到SD CARD存储，需要注册权限
        Uri photoUri = Uri.fromFile(new File(strFilePath));
        intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
        startActivityForResult(intent,2333);
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
                case 2333:{
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
                }break;
            }
        }
    }
}
