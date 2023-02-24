package com.example.chapter06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class ShareWriteActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_age;
    private EditText et_height;
    private EditText et_weight;
    private SharedPreferences preferences;
    private CheckBox ck_married;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_write);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_height = findViewById(R.id.et_height);
        et_weight = findViewById(R.id.et_weight);
        findViewById(R.id.btn_read).setOnClickListener(this);
        ck_married = findViewById(R.id.ck_married);

        findViewById(R.id.btn_save).setOnClickListener(this);

        preferences = getSharedPreferences("config", MODE_PRIVATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                saveEditText();
                clearEditText();
                break;
            case R.id.btn_read:
                read();
                break;
        }
    }

    private void clearEditText() {
        et_age.setText(null);
        et_height.setText(null);
        et_name.setText(null);
        et_weight.setText(null);
        ck_married.setChecked(false);
    }

    private void read() {
        et_name.setText(preferences.getString("name", null) == null ? "" : preferences.getString("name", ""));
        et_age.setText(preferences.getInt("age", 0) == 0 ? "" : String.valueOf(preferences.getInt("age", 0)));
        et_height.setText(String.valueOf(preferences.getFloat("height", 0)));
        et_weight.setText(String.valueOf(preferences.getFloat("weight", 0)));
        ck_married.setChecked(preferences.getBoolean("married", false));
    }

    private void saveEditText() {
        String name = et_name.getText().toString();
        String age = et_age.getText().toString();
        String height = et_height.getText().toString();
        String weight = et_weight.getText().toString();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", name);
        editor.putInt("age", Integer.parseInt(age));
        editor.putFloat("height", Float.parseFloat(height));
        editor.putFloat("weight", Float.parseFloat(weight));
        editor.putBoolean("married", ck_married.isChecked());
        editor.apply();
    }
}