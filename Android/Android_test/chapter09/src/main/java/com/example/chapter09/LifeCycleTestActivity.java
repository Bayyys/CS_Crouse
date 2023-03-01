package com.example.chapter09;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class LifeCycleTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle_test);
        Log.d("bay","onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("bay","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("bay","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("bay","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("bay","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("bay","onDestroy");
    }
}