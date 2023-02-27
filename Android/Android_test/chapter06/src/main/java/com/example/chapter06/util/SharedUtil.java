package com.example.chapter06.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedUtil {
    private static SharedUtil mUtil;
    private SharedPreferences preferences;

    public static SharedUtil getInstance(Context ctx) {
        if (mUtil == null) {
            mUtil = new SharedUtil();
            mUtil.preferences = ctx.getSharedPreferences("shopping", Context.MODE_PRIVATE);
        }
        return mUtil;
    }

    public void writeBoolean(String key, boolean value) {
        preferences.edit().putBoolean(key, value).commit();
    }

    public boolean readBoolean(String key, boolean defValue) {
        return preferences.getBoolean(key, defValue);
    }
}
