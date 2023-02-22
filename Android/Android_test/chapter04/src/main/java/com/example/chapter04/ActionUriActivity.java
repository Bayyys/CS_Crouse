package com.example.chapter04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class ActionUriActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_uri);
        findViewById(R.id.btn_uri_dial).setOnClickListener(this);
        findViewById(R.id.btn_uri_sms).setOnClickListener(this);
        findViewById(R.id.btn_uri_my).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String phoneNum = "12345";
        Intent intent = new Intent();
        Uri uri = null;
        switch (v.getId()){
            case R.id.btn_uri_dial:
                intent.setAction(Intent.ACTION_DIAL);
                uri = Uri.parse("tel:" + phoneNum);
                intent.setData(uri);
                break;

            case R.id.btn_uri_sms:
                intent.setAction(Intent.ACTION_SENDTO);
                uri = Uri.parse("smsto:" + phoneNum);
                intent.setData(uri);
                break;
            case R.id.btn_uri_my:
                intent.setAction("android.intent.action.NING");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                break;
        }
        startActivity(intent);
    }
}