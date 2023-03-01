package com.example.chapter07_client.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class firstCheckUtil {
    public static void firstCheckSMSPERMISSION(Activity act, String[] PERMISSIONS, int REQUEST_CODES) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int check = PackageManager.PERMISSION_GRANTED;
            for (String permission : PERMISSIONS) {
                check = ContextCompat.checkSelfPermission(act, permission);
                if (check != PackageManager.PERMISSION_GRANTED) {
                    act.requestPermissions(PERMISSIONS, REQUEST_CODES);
                }
            }
        }
    }

}
