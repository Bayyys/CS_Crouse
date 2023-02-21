package com.example.chapter03;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chapter03.util.datautil;

public class ButtonEnabledActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_result;
    private TextView text_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_enabled);
        // test btn
        btn_result = findViewById(R.id.btn_result);
        // enable btn
        Button btn_enable = findViewById(R.id.btn_enabled);
        // disable btn
        Button btn_disable = findViewById(R.id.btn_disable);
        // text result
        text_result = findViewById(R.id.text_result);

        btn_enable.setOnClickListener(this);
        btn_disable.setOnClickListener(this);
        btn_result.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_enabled:
                btn_result.setEnabled(true);
                btn_result.setTextColor(Color.BLACK);
                break;
            case R.id.btn_disable:
                btn_result.setEnabled(false);
                btn_result.setTextColor(Color.GRAY);
                String desc = String.format("查看按钮的测试结果");
                text_result.setText(desc);
                break;
            case R.id.btn_result:
                String desc_enable = String.format("%s\t您点击了按钮\t%s", datautil.getNowTime(), ((Button) v).getText());
                text_result.setText(desc_enable);
                break;
        }
    }
}