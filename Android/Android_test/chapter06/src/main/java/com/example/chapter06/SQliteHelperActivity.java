package com.example.chapter06;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chapter06.database.UserDBHelper;
import com.example.chapter06.enity.User;
import com.example.chapter06.util.ToastUtil;

import java.util.List;

public class SQliteHelperActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_age;
    private EditText et_height;
    private EditText et_weight;
    private CheckBox ck_married;
    private UserDBHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_helper);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_height = findViewById(R.id.et_height);
        et_weight = findViewById(R.id.et_weight);
        ck_married = findViewById(R.id.ck_married);

        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 打开数据库
        mHelper = UserDBHelper.getInstance(this);
        // 打开数据库的读写连接
        mHelper.openWriteLink();
        mHelper.openReadLink();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 关闭数据库
        mHelper.closeLink();
    }

    public void clearEditText() {
        et_name.setText("");
        et_age.setText("");
        et_height.setText("");
        et_weight.setText("");
        ck_married.setChecked(false);
    }

    @Override
    public void onClick(View v) {
        User user = null;
        String name = et_name.getText().toString();
        String age = et_age.getText().toString();
        String height = et_height.getText().toString();
        String weight = et_weight.getText().toString();
        switch (v.getId()) {
            case R.id.btn_save:
                user = new User(name, Integer.parseInt(age), Long.parseLong(height), Long.parseLong(weight), ck_married.isChecked());
                if (mHelper.insert(user) > 0) {
                    ToastUtil.showMsg(this, "添加成功");
                    clearEditText();
                }
                break;
            case R.id.btn_delete:
                if (mHelper.deleteByName(name) > 0) {
                    ToastUtil.showMsg(this, "删除成功");
                    clearEditText();
                }
                break;
            case R.id.btn_query:
                List<User> list = null;
                if (et_name.getText().toString().length() > 0) {
                    list = mHelper.queryByName(name);
                    for (User u :
                            list) {
                        Log.d("Query", u.toString());
                    }
                } else {
                    list = mHelper.queryAll();
                    for (User u :
                            list) {
                        Log.d("Query", u.toString());
                    }
                }
                break;
            case R.id.btn_update:
                user = new User(name, Integer.parseInt(age), Long.parseLong(height), Long.parseLong(weight), ck_married.isChecked());
                if (mHelper.update(user) > 0) {
                    ToastUtil.showMsg(this, "修改成功");
                    clearEditText();
                } else {
                    ToastUtil.showMsg(this, "修改失败 或 未找到该用户");
                }
                break;

        }
    }
}