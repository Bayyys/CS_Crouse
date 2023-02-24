package com.example.chapter05;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chapter05.util.ViewUtil;

import java.util.Random;

public class LoginMainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private EditText et_phone;
    private EditText et_password;
    private TextView tv_password;
    private Button btn_forget;
    private CheckBox ck_remember;
    private RadioButton rb_password;
    private RadioButton rb_verifyCode;
    private ActivityResultLauncher<Intent> register;
    private Button btn_login;
    private SharedPreferences preferences;
    // 定义输入密码
    String mPassword = "";
    private String mVerifyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        // 1. Get EveryView
        // EditText
        et_phone = findViewById(R.id.et_phone);
        et_password = findViewById(R.id.et_password);
        // TextView
        tv_password = findViewById(R.id.tv_password);
        // Button
        btn_forget = findViewById(R.id.btn_forget);
        btn_login = findViewById(R.id.btn_login);
        // CheckBox
        ck_remember = findViewById(R.id.ck_remember);
        // RadioButton(RadioGroup)
        RadioGroup rb_login = findViewById(R.id.rg_login);
        rb_password = findViewById(R.id.rb_password);
        rb_verifyCode = findViewById(R.id.rb_verifycode);
        preferences = getSharedPreferences("login", MODE_PRIVATE);
        // Register
        register = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            Intent intent = result.getData();
            if (intent != null && result.getResultCode() == Activity.RESULT_OK) {
                mPassword = intent.getStringExtra("new_password");
                et_password.setText(mPassword);
            }
        });
        // SharedPreferences

        // 2. Limitation
        // reload()
        reload();
        // RadioGroup
        rb_login.setOnCheckedChangeListener(this);
        // et_phone设置文本监听器
        et_phone.addTextChangedListener(new HideTextWatcher(et_phone, 11));
        // et_password设置文本监听器
        et_password.addTextChangedListener(new HideTextWatcher(et_password, 6));
        // btn_forget点击事件监听
        btn_forget.setOnClickListener(this);
        // btn_login点击事件监听
        btn_login.setOnClickListener(this);
    }

    private void reload() {
        boolean isRemember = preferences.getBoolean("isRemember", false);
        if (isRemember) {
            String phone = preferences.getString("phone", "");
            String password = preferences.getString("password", "");
            et_phone.setText(phone);
            et_password.setText(password);
            ck_remember.setChecked(true);
            mPassword = password;
        } else {
            et_phone.setText("");
            et_password.setText("");
            ck_remember.setChecked(false);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            // Password Login
            case R.id.rb_password:
                // 判断是否记住过密码
                String phoneEdit = et_phone.getText().toString();
                if (phoneEdit.length() == 11) {
                    String phoneRemember = preferences.getString("phone", "");
                    if (phoneRemember.equals(phoneEdit)) {
                        String passwordRemember = preferences.getString("password", "");
                        et_password.setText(passwordRemember);
                        ck_remember.setChecked(true);
                    }
                } else {
                    et_password.setText(null);
                }
                tv_password.setText(getString(R.string.login_password));
                et_password.setHint(getString(R.string.input_password));
                btn_forget.setText(getString(R.string.forget_password));
                ck_remember.setVisibility(View.VISIBLE);
                ck_remember.setChecked(false);
                break;

            // Verify Code Login
            case R.id.rb_verifycode:
                tv_password.setText(getString(R.string.verifycode));
                et_password.setHint(getString(R.string.input_verifycode));
                et_password.setText(null);
                btn_forget.setText(getString(R.string.get_verifycode));
                ck_remember.setVisibility(View.GONE);
                break;
        }
    }

    private void loginSuccess() {
        // 提示用户登录成功
        String desc = String.format("您的手机号码是：%s，恭喜你通过登录验证，点击\"确定\"按钮返回上一页面", et_password.getText().toString());
        // 弹出提醒对话框，提示登陆成功
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("登录成功");
        builder.setMessage(desc);
        builder.setPositiveButton("确定", (dialog, which) -> {
            // 结束当前的活动页面
            finish();
        });
        builder.setNegativeButton("返回", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        String phoneNum = et_phone.getText().toString();
        switch (v.getId()) {
            case R.id.btn_forget:
                if (phoneNum.length() < 11) {
                    Toast.makeText(this, "请输入正确的11位手机号码", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (rb_password.isChecked()) {
                    // 密码方式登录：
                    // 1. 调到LoginForgetActivity
                    // 2. 需要携带已输入的手机号数据
                    Intent intent = new Intent(this, LoginForgetActivity.class);
                    intent.putExtra("phone", phoneNum);
                    register.launch(intent);
                } else if (rb_verifyCode.isChecked()) {
                    // 验证码方式登录：
                    // 生成六位随机数字验证码
                    mVerifyCode = String.format("%06d", new Random().nextInt(999999));
                    et_password.setText(mVerifyCode);
                    // 弹出对话框，提示用户记住六位数字验证码
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("请记住验证码");
                    builder.setMessage("手机号" + phoneNum + ", 本次验证码是：" + mVerifyCode + ", 请输入验证码");
                    builder.setPositiveButton("好的", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                break;

            // Click 登录
            case R.id.btn_login:
                if (rb_password.isChecked()) {
                    if (phoneNum.length() < 11) {
                        Toast.makeText(this, "请输入正确的手机号！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    // 密码方式登录：
                    if (!mPassword.equals(et_password.getText().toString())) {
                        // 密码错误，弹出提示
                        Toast.makeText(this, "请输入正确的密码！", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        // 密码正确，提示登录成功
                        if (ck_remember.isChecked()) {
                            // 保存登录信息
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("phone", phoneNum);
                            editor.putString("password", et_password.getText().toString());
                            editor.putBoolean("isRemember", true);
                            editor.apply();
                        }
                        loginSuccess();
                    }
                } else if (rb_verifyCode.isChecked()) {
                    // 验证码方式登录：
                    if (phoneNum.length() < 11) {
                        Toast.makeText(this, "请输入正确的手机号！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (mVerifyCode == null || !mVerifyCode.equals(et_password.getText().toString())) {
                        // 验证码错误，弹出提示
                        Toast.makeText(this, "请输入正确的验证码！", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        // 验证码正确，提示登录成功

                        loginSuccess();
                    }
                }
                break;
        }
    }

    // 定义一个编辑框监听器，在输入文本达到指定长度时自动隐藏输入法
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
                // 隐藏输入法软键盘
                ViewUtil.hideOneInputMethod(LoginMainActivity.this, mView);
                if (mView == et_phone) {
                    if (rb_password.isChecked()) {
                        // 如果是密码登录方式，判断是否保存了密码
                        String phoneEnter = et_phone.getText().toString();
                        String phoneSave = preferences.getString("phone", "");
                        if (phoneEnter.equals(phoneSave)) {
                            // 如果输入的手机号码和保存的手机号码一致，自动填充密码
                            mPassword = preferences.getString("password", "");
                            et_password.setText(mPassword);
                        } else {
                            // 如果输入的手机号码和保存的手机号码不一致，清空密码
                            mPassword = "";
                            et_password.setText(null);
                        }
                        // 如果是手机号码输入框，自动跳转到密码输入框
                        et_password.requestFocus();
                    } else if (rb_verifyCode.isChecked()) {
                        // 如果是验证码登录方式，自动跳转到验证码输入框
                        btn_forget.requestFocus();
                    }
                }

            }
        }
    }
}