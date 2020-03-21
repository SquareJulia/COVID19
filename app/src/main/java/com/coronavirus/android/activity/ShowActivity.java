package com.coronavirus.android.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.coronavirus.android.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ShowActivity extends AppCompatActivity {

    private ImageView imageView;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE
            ,Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        InitView();
    }

    public void InitView(){
        imageView=(ImageView)findViewById(R.id.image);
        Intent intent=getIntent();
        imageView.setImageBitmap(MainActivity.shotWeb);

        FloatingActionButton save=(FloatingActionButton)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //保存图片
                askPermissions(ShowActivity.this);//存储权限申请
                MyThread myThread=new MyThread();
                new Thread(myThread).start();
            }
        });
    }

    public static void askPermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }

}
