package com.example.chapter05;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SwithDefaultActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private TextView sw_result;
    private TextView tv_switch_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swith_default);
        Switch sw_switch_status = findViewById(R.id.sw_switch_status);
        CheckBox ck_switch_status = findViewById(R.id.ck_switch_status);
        sw_result = findViewById(R.id.tv_result);
        tv_switch_result = findViewById(R.id.tv_switch_result);
        sw_switch_status.setOnCheckedChangeListener(this);
        ck_switch_status.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.sw_switch_status:
                sw_result.setText(String.format("Switch按钮的状态是%s", isChecked ? "闭合" : "断开"));
                break;
            case R.id.ck_switch_status:
                tv_switch_result.setText(String.format("Optimised Switch按钮的状态是%s", isChecked ? "闭合" : "断开"));
                break;
        }
    }
}