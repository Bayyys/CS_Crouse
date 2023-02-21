package com.example.chapter03;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chapter03.util.datautil;

public class ButtonStyleActivity extends AppCompatActivity {

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_style);
        text = findViewById(R.id.tv_button);
    }

    public void doClick(View view) {
        String desc = String.format("%s 您点击了按钮： %s", datautil.getNowTime(), ((Button) view).getText());
        text.setText(desc);

    }
}