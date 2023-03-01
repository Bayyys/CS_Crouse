package com.example.chapter07_client;

import static com.example.chapter07_client.util.firstCheckUtil.firstCheckSMSPERMISSION;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import com.example.chapter07_client.util.PermissionUtil;
import com.example.chapter07_client.util.ToastUtil;

import java.util.ArrayList;

public class PermissionTestActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String[] PERMISSIONS_CONTACT = new String[]{
            "android.permission.READ_CONTACTS",
            "android.permission.WRITE_CONTACTS",
    };

    private static final String[] PERSSIONS_SMS = new String[]{
            "android.permission.READ_SMS",
            "android.permission.SEND_SMS",
    };


    private static final int REQUEST_CODE_CONTACT = 1;
    private static final int REQUEST_CODE_SMS = 2;

    private static final String[] PERMISSIONS_ALL = new String[]{
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.READ_SMS,
            Manifest.permission.SEND_SMS
    };

    private static final int REQUEST_CODES = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_test);
        firstCheckSMSPERMISSION(this, PERMISSIONS_ALL, REQUEST_CODES);
//        findViewById(R.id.btn_test).setOnClickListener(this);
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_CODE_CONTACT) {
//            if (PermissionUtil.checkGrant(grantResults)) {
//                ToastUtil.showMsg(this, "已经获取到联系人权限");
//            } else {
//                ToastUtil.showMsg(this, "未获取到联系人权限");
//                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                intent.setData(Uri.fromParts("package", getPackageName(), null));
//                startActivity(intent);
//            }
//        }
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test:
                if (PermissionUtil.checkPermissions(this, PERMISSIONS_CONTACT, REQUEST_CODE_CONTACT)) {
                    // 已经获取到权限
                    ToastUtil.showMsg(this, "已经获取到联系人权限");
                }
                break;
        }
    }
}