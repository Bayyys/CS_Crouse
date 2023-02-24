package com.example.chapter06.util;

import android.content.Context;
import android.widget.Toast;

import com.example.chapter06.FileWriteActivity;

public class ToastUtil {
    public static void showMsg(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void show(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
