package com.example.chapter06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DatabaseActivity extends AppCompatActivity implements View.OnClickListener {

    private String mDatabaseName;
    private TextView tv_database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        findViewById(R.id.btn_database_create).setOnClickListener(this);
        findViewById(R.id.btn_database_delete).setOnClickListener(this);
        tv_database = findViewById(R.id.tv_database);
        // 生成一个测试数据库
        mDatabaseName = getFilesDir().getAbsolutePath() + "/test.db";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_database_create:
                // 创建或打开数据库(不存在，则创建)
                SQLiteDatabase db = openOrCreateDatabase(mDatabaseName, Context.MODE_PRIVATE, null);
                String desc = String.format("数据库%s创建成功", db.getPath(), (db.isOpen() ? "成功" : "失败"));
                tv_database.setText(desc);
                break;
            case R.id.btn_database_delete:
                boolean result = deleteDatabase(mDatabaseName);
                tv_database.setText(String.format("数据库%s删除%s", mDatabaseName, (result ? "成功" : "失败")));
                break;
        }

    }
}