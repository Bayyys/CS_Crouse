package com.example.chapter03;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chapter03.util.datautil;

public class ButtonLongClickActivity extends AppCompatActivity {

    private TextView btn_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_long_click);
        // btn_click_single
        Button btn_long_click = findViewById(R.id.btn_long_click);
        btn_text = findViewById(R.id.btn_text);
        btn_long_click.setOnLongClickListener(new myOnLongClickListener(btn_text));

        // btn_click_public
        Button btn_click_public = findViewById(R.id.btn_long_click_lambda);
        btn_click_public.setOnLongClickListener(v -> {
            String desc = String.format("%s\t您点击了按钮\t%s", datautil.getNowTime(), ((Button) v).getText());
            btn_text.setText(desc);
            return true;
        });
    }

    static class myOnLongClickListener implements View.OnLongClickListener {

        private final TextView btn_text;

        public myOnLongClickListener(TextView btn_text) {
            this.btn_text = btn_text;
        }

        @Override
        public boolean onLongClick(View view) {
            String desc = String.format("%s\t您点击了按钮\t%s", datautil.getNowTime(), ((Button) view).getText());
            btn_text.setText(desc);
            return true;
        }
    }
}