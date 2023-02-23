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

public class EditKeyboardActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private TextView tv_edit_judge;
    private TextView tv_calender;
    private DatePicker dp_taker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_keyboard);
        // EidtView & 文本变化监听器
        EditText et_phone = findViewById(R.id.et_phone);
        EditText et_password = findViewById(R.id.et_password);
        et_phone.addTextChangedListener(new HideTextWatcher(et_phone, 11));
        et_password.addTextChangedListener(new HideTextWatcher(et_password, 6));

        // AlertDialog
        findViewById(R.id.btn_edit_alert).setOnClickListener(this);
        tv_edit_judge = findViewById(R.id.tv_edit_judge);

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
            case R.id.btn_edit_alert:
                // 创建提醒对话框的建造器
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("尊敬的用户"); // 设置对话框的标题文本
                builder.setMessage("你真的要卸载我吗？"); // 设置对话框的内容文本
                // 设置对话框的肯定按钮文本及其点击监听器
                builder.setPositiveButton("残忍卸载", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_edit_judge.setText("虽然依依不舍，但是只能离开了");
                    }
                });
                // 设置对话框的否定按钮文本及其点击监听器, lambda表达式
                builder.setNegativeButton("我再想想", (dialog, which) -> {
                    tv_edit_judge.setText("让我再陪你三百六十五个日夜");
                });
                AlertDialog alert = builder.create(); // 根据建造器构建提醒对话框对象
                alert.show(); // 显示提醒对话框
                break;
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

    // 定义一个编辑框监听器，在输入文本达到指定长度时自动隐藏输入法
    private class HideTextWatcher implements TextWatcher {
        private EditText mView; // 声明一个编辑框对象
        private int mMaxLength; // 声明一个最大长度变量

        public HideTextWatcher(EditText v, int maxLength) {
            super();
            mView = v;
            mMaxLength = maxLength;
        }

        // 在编辑框的输入文本变化前触发
        public void beforeTextChanged(CharSequence s, int start, int count, int
                after) {
        }

        // 在编辑框的输入文本变化时触发
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        // 在编辑框的输入文本变化后触发
        public void afterTextChanged(Editable s) {
            String str = s.toString(); // 获得已输入的文本字符串
            // 输入文本达到11位（如手机号码），或者达到6位（如登录密码）时关闭输入法
            if ((str.length() == 11 && mMaxLength == 11)
                    || (str.length() == 6 && mMaxLength == 6)) {
                ViewUtil.hideOneInputMethod(EditKeyboardActivity.this, mView); // 隐藏输入法软键盘
            }
        }
    }
}