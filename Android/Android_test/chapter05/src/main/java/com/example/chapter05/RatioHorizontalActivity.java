package com.example.chapter05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RatioHorizontalActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private TextView tv_radio_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratio_horizontal);
        RadioGroup radio_group = findViewById(R.id.radio_group);
        tv_radio_result = findViewById(R.id.tv_radio_result);
        radio_group.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_first:
                tv_radio_result.setText("您选择了类别一");
                break;
            case R.id.rb_second:
                tv_radio_result.setText("您选择了类别二");
                break;
            case R.id.rb_third:
                tv_radio_result.setText("您选择了类别三");
                break;
        }
    }
}