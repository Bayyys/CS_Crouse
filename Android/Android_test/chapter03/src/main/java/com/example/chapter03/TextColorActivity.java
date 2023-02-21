package com.example.chapter03;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class TextColorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_color);

        // 系统自带颜色
        TextView tv_code_system = findViewById(R.id.tv_code_system);
        tv_code_system.setTextColor(Color.GREEN);

        // 八位文本视图
        TextView tv_code_eight = findViewById(R.id.tv_code_eight);
        tv_code_eight.setTextColor(0xff00ff00);

        // 六位文本视图
        TextView tv_code_six = findViewById(R.id.tv_code_six);
        tv_code_six.setTextColor(0x00ff00);

        // 背景设置
        TextView tv_code_background = findViewById(R.id.tv_code_background);
        // 系统自带
//        tv_code_background.setBackgroundColor(Color.GREEN);
        // 资源文件
        tv_code_background.setBackgroundResource(R.color.green);

    }
}