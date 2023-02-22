package com.example.chapter05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class EditFocusActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    private TextView tv_edit_judge;
    private EditText et_phone;
    private EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_focus);
        tv_edit_judge = findViewById(R.id.tv_edit_judge);
        et_phone = findViewById(R.id.et_phone);
        et_password = findViewById(R.id.et_password);
        Button btn_edit_log = findViewById(R.id.btn_edit_log);
        et_password.setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        String phoneNum = et_phone.getText().toString();
        if (TextUtils.isEmpty(phoneNum) || phoneNum.length() < 11) {
            et_phone.requestFocus();
            Toast.makeText(this, "请输入11位手机号码", Toast.LENGTH_SHORT).show();
            tv_edit_judge.setText("请输入11位手机号码");
        } else {
            tv_edit_judge.setText("请输入密码");
        }
    }
}