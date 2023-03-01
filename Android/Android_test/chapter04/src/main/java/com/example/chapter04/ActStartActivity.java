package com.example.chapter04;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ActStartActivity extends AppCompatActivity implements View.OnClickListener {

    private  static final String TAG = "bay";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Activity onCreate");
        setContentView(R.layout.activity_act_start);
        Button btn_act_next = findViewById(R.id.btn_act_next);
        btn_act_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, ActFinishActivity.class));
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Activity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Activity onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Activity onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Activity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Activity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Activity onDestroy");
    }
}