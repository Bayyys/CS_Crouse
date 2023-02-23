package com.example.chapter05;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class TimePickerActivity extends AppCompatActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {

    private TextView tv_edit_judge;
    private TextView tv_time;
    private TimePicker tp_taker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);

        // DatePicker
        Button btn_time = findViewById(R.id.btn_time);
        tp_taker = findViewById(R.id.tp_taker);
        tp_taker.setIs24HourView(true);
        btn_time.setOnClickListener(this);
        tv_time = findViewById(R.id.tv_time);


        // DatePickerDialog
        findViewById(R.id.btn_choose_time).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_time:
                String desc = String.format("选择的时间为：%2d:%2d", tp_taker.getHour(), tp_taker.getMinute());
                tv_time.setText(desc);
                break;
            case R.id.btn_choose_time:
                // 获取日历的一个实例，里面包含了当前的时分秒
                Calendar calendar = Calendar.getInstance();
                // 构建一个时间对话框，该对话框已经集成了时间选择器。
                // TimePickerDialog的第二个构造参数指定了时间监听器
                TimePickerDialog dialog = new TimePickerDialog(this,  android.R.style.Theme_Light_Panel, this,
                        calendar.get(Calendar.HOUR_OF_DAY), // 小时
                        calendar.get(Calendar.MINUTE), // 分钟
                        true); // true表示24小时制，false表示12小时制
                dialog.show(); // 显示时间对话框
                break;
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String desc = String.format("选择的时间为：%2d:%2d", hourOfDay, minute);
        tv_time.setText(desc);
    }
}