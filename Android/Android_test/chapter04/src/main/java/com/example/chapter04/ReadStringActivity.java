package com.example.chapter04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ReadStringActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_string);
        TextView tv_read_string = findViewById(R.id.tv_read_string);
        String stringValue = getString(R.string.weather_str);
        tv_read_string.setText("今天的天气是：" + stringValue);
    }
}