package com.example.chapter06;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.chapter06.util.FileUtil;
import com.example.chapter06.util.ToastUtil;

import java.io.File;

public class ImageWriteActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_content;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_write);
        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_read).setOnClickListener(this);
        iv_content = findViewById(R.id.iv_content);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                String fileName = System.currentTimeMillis() + ".png";
                // 获取app外部存储的私有空间
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + File.separatorChar + fileName;
                Log.d("ning", path);
                // 从资源文件中获取Bitmap对象
                Bitmap b1 = BitmapFactory.decodeResource(getResources(), R.drawable.ting1);
                FileUtil.saveImage(path, b1);
                ToastUtil.show(this, "图片保存成功");
                break;
            case R.id.btn_read:
                Bitmap b2 = FileUtil.openImage(path);
                iv_content.setImageBitmap(b2);
                break;
        }
    }
}