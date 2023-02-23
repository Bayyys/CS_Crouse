package com.example.chapter05;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chapter05.util.ViewUtil;

import java.util.Calendar;

public class DatePickerActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private TextView tv_edit_judge;
    private TextView tv_calender;
    private DatePicker dp_taker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);

        // DatePicker
        Button btn_calender = findViewById(R.id.btn_calendar);
        dp_taker = findViewById(R.id.dp_taker);
        btn_calender.setOnClickListener(this);
        tv_calender = findViewById(R.id.tv_calendar);


        // DatePickerDialog
        findViewById(R.id.btn_choose_date).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_calendar:
                String desc = String.format("选择的日期为：%d年%d月%d日", dp_taker.getYear(), dp_taker.getMonth() + 1, dp_taker.getDayOfMonth());
                tv_calender.setText(desc);
                break;
            case R.id.btn_choose_date:
                // 获取日历的一个实例，里面包含了当前的年月日
                Calendar calendar = Calendar.getInstance();
                // 构建一个日期对话框，该对话框已经集成了日期选择器。
                // DatePickerDialog的第二个构造参数指定了日期监听器
                DatePickerDialog dialog = new DatePickerDialog(this, this,
                        calendar.get(Calendar.YEAR), // 年份
                        calendar.get(Calendar.MONTH), // 月份
                        calendar.get(Calendar.DAY_OF_MONTH)); // 日子
                dialog.show(); // 显示日期对话框
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String desc = String.format("选择的日期为：%d年%d月%d日", year, month + 1, dayOfMonth);
        tv_calender.setText(desc);
    }
}