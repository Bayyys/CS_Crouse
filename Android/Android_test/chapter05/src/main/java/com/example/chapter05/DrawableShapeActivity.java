package com.example.chapter05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class DrawableShapeActivity extends AppCompatActivity implements View.OnClickListener {

    private View v_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_shape);
        findViewById(R.id.btn_rect).setOnClickListener(this);
        findViewById(R.id.btn_oval).setOnClickListener(this);
        v_content = findViewById(R.id.v_content);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_rect:
                v_content.setBackgroundResource(R.drawable.shape_rect_gold);
                break;
            case R.id.btn_oval:
                v_content.setBackgroundResource(R.drawable.shape_oval_rise);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }

    }
}