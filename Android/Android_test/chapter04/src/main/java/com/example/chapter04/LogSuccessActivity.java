package com.example.chapter04;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class LogSuccessActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_success);
        findViewById(R.id.btn_log_return).setOnClickListener(this);
        findViewById(R.id.iv_log_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}