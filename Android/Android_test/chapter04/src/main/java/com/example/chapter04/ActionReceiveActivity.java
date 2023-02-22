package com.example.chapter04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActionReceiveActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_receive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_receive);
        findViewById(R.id.btn_receive_return).setOnClickListener(this);
        tv_receive = findViewById(R.id.tv_receive);
        Bundle bundle = getIntent().getExtras();
        String request_time = bundle.getString("request_time");
        String request_content = bundle.getString("request_content");
        String desc = String.format("收到请求消息：...\n请求时间为: %s\n请求内容为: %s", bundle.getString("request_time"), bundle.getString("request_content"));
        tv_receive.setText(desc);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClass(this, ActionSendActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}