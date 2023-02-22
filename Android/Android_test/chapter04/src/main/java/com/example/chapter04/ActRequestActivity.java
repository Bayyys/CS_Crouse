package com.example.chapter04;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;

import com.example.chapter04.utils.dateUtil;

public class ActRequestActivity extends AppCompatActivity implements View.OnClickListener {
    private final String mRequest = "what's your phone number?";
    private ActivityResultLauncher<Intent> register;
    private TextView tv_response;
    private TextView tv_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_request);
        tv_request = findViewById(R.id.tv_request_info);
        tv_request.setText("待发送的数据为：" + mRequest);
        tv_response = findViewById(R.id.tv_request);

        findViewById(R.id.btn_request).setOnClickListener(this);
        register = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result != null){
                Intent intent = result.getData();
                if (intent != null && result.getResultCode() == Activity.RESULT_OK) {
                    Bundle bundle = intent.getExtras();
                    String response_time = bundle.getString("response_time");
                    String response_content = bundle.getString("response_content");
                    String desc = String.format("收到应答消息：...\n应答时间为: %s\n应答内容为: %s", response_time, response_content);
                    tv_response.setText(desc);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ActResponseActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("request_time", dateUtil.getNowTime());
        bundle.putString("request_content", mRequest);
        intent.putExtras(bundle);
//      过时
//        startActivityForResult(intent, 0);
        register.launch(intent);
    }
}