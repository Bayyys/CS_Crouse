package com.example.chapter07_client;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chapter07_client.entity.User;
import com.example.chapter07_client.util.ToastUtil;

public class ContentWriteActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_age;
    private EditText et_weight;
    private EditText et_height;
    private CheckBox ck_married;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_write);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_weight = findViewById(R.id.et_weight);
        et_height = findViewById(R.id.et_height);
        ck_married = findViewById(R.id.ck_married);


        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_read).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                save();
                break;
            case R.id.btn_delete:
                delete();
                break;
            case R.id.btn_read:
                read();
                break;
        }
    }

    private void delete() {
        Uri uri = ContentUris.withAppendedId(UserInfoContent.CONTENT_URI, 2);
//        int count = getContentResolver().delete(uri, null, null);
        int count = getContentResolver().delete(UserInfoContent.CONTENT_URI, "name=?", new String[]{"jack"});
        if (count > 0) {
            ToastUtil.showMsg(this, "删除成功！");
        } else {
            ToastUtil.showMsg(this, "删除失败，未查询到该数据！");
        }
    }

    @SuppressLint("Range")
    private void read() {
        Cursor cursor = getContentResolver().query(UserInfoContent.CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                User info = new User();
                info.id = cursor.getInt(cursor.getColumnIndex(UserInfoContent._ID));
                info.name = cursor.getString(cursor.getColumnIndex(UserInfoContent.USER_NAME));
                info.weight = cursor.getLong(cursor.getColumnIndex(UserInfoContent.USER_WEIGHT));
                info.height = cursor.getLong(cursor.getColumnIndex(UserInfoContent.USER_HEIGHT));
                info.age = cursor.getInt(cursor.getColumnIndex(UserInfoContent.USER_AGE));
                info.married = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(UserInfoContent.USER_MARRIED)));
                Log.d("bay", info.toString());
            }
            cursor.close();
        }
    }

    private void save() {
        ContentValues values = new ContentValues();
        values.put(UserInfoContent.USER_NAME, et_name.getText().toString());
        values.put(UserInfoContent.USER_AGE, Integer.parseInt(et_age.getText().toString()));
        values.put(UserInfoContent.USER_WEIGHT, Long.parseLong(et_weight.getText().toString()));
        values.put(UserInfoContent.USER_HEIGHT, Long.parseLong(et_height.getText().toString()));
        values.put(UserInfoContent.USER_MARRIED, ck_married.isChecked());
        getContentResolver().insert(UserInfoContent.CONTENT_URI, values);
        ToastUtil.showMsg(this, "保存成功！");
    }
}

