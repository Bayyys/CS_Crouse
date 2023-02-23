package com.example.chapter05;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chapter05.util.ViewUtil;

import java.util.Random;

public class LoginForgetActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_password_first;
    private EditText et_password_second;
    private EditText et_verifycode;
    private String myPhoneNum;
    private String mVerifyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_forget);
        et_password_first = findViewById(R.id.et_password_first);
        et_password_second = findViewById(R.id.et_password_second);
        et_verifycode = findViewById(R.id.et_verifycode);
        Button btn_verifycode = findViewById(R.id.btn_verifycode);
        Button btn_confirm = findViewById(R.id.btn_confirm);
        // 文本长度监听器
        et_password_first.addTextChangedListener(new HideTextWatcher(et_password_first, 6));
        et_password_second.addTextChangedListener(new HideTextWatcher(et_password_second, 6));
        et_verifycode.addTextChangedListener(new HideTextWatcher(et_verifycode, 6));
        // 获取传递的手机号
        myPhoneNum = getIntent().getStringExtra("phone");
        btn_verifycode.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_verifycode:
                mVerifyCode = String.format("%06d", new Random().nextInt(999999));
                // 弹出对话框，提示用户记住六位数字验证码
                et_verifycode.setText(mVerifyCode);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("请记住验证码");
                builder.setMessage("手机号" + myPhoneNum + ", 本次验证码是：" + mVerifyCode + ", 请输入验证码");
                builder.setPositiveButton("好的", null);
                AlertDialog dialog = builder.create();
                dialog.show();
                break;

            case R.id.btn_confirm:
                String password_first = et_password_first.getText().toString();
                String password_second = et_password_second.getText().toString();
                if (password_first.length() < 6 || password_second.length() < 6) {
                    Toast.makeText(this, "请输入正确的密码！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password_first.equals(password_second)) {
                    Toast.makeText(this, "两次输入密码不一致！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mVerifyCode == null || !mVerifyCode.equals(et_verifycode.getText().toString())) {
                    // 验证码错误，弹出提示
                    Toast.makeText(this, "请输入正确的验证码！", Toast.LENGTH_SHORT).show();
                    return;
                } else if (mVerifyCode.equals(et_verifycode.getText().toString())) {
                    // 验证码正确，提示修改成功
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                    builder2.setTitle("修改成功");
                    builder2.setMessage("密码修改成功！点击\"确定\"返回登录页面");
                    builder2.setPositiveButton("确定", (dialog1, which) -> {
                        Intent intent = new Intent();
                        intent.putExtra("new_password", password_first);
                        setResult(Activity.RESULT_OK, intent);
//                        startActivity(intent);
                        finish();
                    });
                    AlertDialog dialog2 = builder2.create();
                    dialog2.show();
                    return;
                }
                break;
        }
    }

    private void modifyPassword() {
    }

    private class HideTextWatcher implements TextWatcher {
        private EditText mView;
        private int mMaxLength;

        public HideTextWatcher(EditText et, int maxLength) {
            super();
            this.mView = et;
            this.mMaxLength = maxLength;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.toString().length() == mMaxLength) {
                ViewUtil.hideOneInputMethod(LoginForgetActivity.this, mView);
            }

        }
    }
}