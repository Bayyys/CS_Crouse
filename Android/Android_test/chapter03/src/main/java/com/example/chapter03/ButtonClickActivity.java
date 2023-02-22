package com.example.chapter03;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chapter03.util.datautil;

public class ButtonClickActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView btn_text_single;
    private TextView btn_text_public;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_click);
        // btn_click_single
        Button btn_click_single = findViewById(R.id.btn_click_single);
        btn_text_single = findViewById(R.id.btn_text_single);
        btn_click_single.setOnClickListener(new myOnclickListener(btn_text_single));

        // btn_click_public
        Button btn_click_public = findViewById(R.id.btn_click_public);
        btn_text_public = findViewById(R.id.btn_text_public);
        btn_click_public.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_click_public){
            String desc = String.format("%s\t您点击了按钮\t%s", datautil.getNowTime(), ((Button) v).getText());
            btn_text_public.setText(desc);
        }
    }

    static class myOnclickListener implements View.OnClickListener {

        private final TextView btn_text_single;

        public myOnclickListener(TextView btn_text_single) {
            this.btn_text_single = btn_text_single;
        }

        @Override
        public void onClick(View view) {
            String desc = String.format("%s\t您点击了按钮\t%s", datautil.getNowTime(), ((Button) view).getText());
            btn_text_single.setText(desc);
        }
    }
}