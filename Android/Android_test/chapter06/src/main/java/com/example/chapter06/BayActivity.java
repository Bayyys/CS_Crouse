package com.example.chapter06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class BayActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_img;
    private TextView tv_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bay);
        findViewById(R.id.btn_show).setOnClickListener(this);
        findViewById(R.id.btn_create).setOnClickListener(this);
        findViewById(R.id.btn_print_path).setOnClickListener(this);
        iv_img = findViewById(R.id.iv_img);
        tv_path = findViewById(R.id.tv_path);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show:
                iv_img.setImageResource(R.drawable.cart);
                break;
            case R.id.btn_create:
                String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath().toString();
                String dirPath = directory + "/test";
                tv_path.setText(dirPath);
                File file = new File(dirPath);
                if (!file.exists()) {
                    try {
                        file.mkdirs();
                        Log.d("Bayyy", "创建文件夹成功");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.btn_print_path:
                break;
        }
    }
}