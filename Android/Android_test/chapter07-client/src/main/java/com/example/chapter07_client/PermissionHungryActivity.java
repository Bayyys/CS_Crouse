package com.example.chapter07_client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import com.example.chapter07_client.util.PermissionUtil;
import com.example.chapter07_client.util.ToastUtil;

public class PermissionHungryActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String[] PERMISSIONS_CONTACTS = new String[]{
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS
    };

    private static final String[] PERMISSIONS_SMS = new String[]{
            Manifest.permission.READ_SMS,
            Manifest.permission.SEND_SMS
    };

    private static final String[] PERMISSIONS_ALL = new String[]{
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.READ_SMS,
            Manifest.permission.SEND_SMS
    };

    private static final int REQUEST_CODE_ALL = 1;
    private static final int REQUEST_CODE_CONTACTS = 2;
    private static final int REQUEST_CODE_SMS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_lazy);
        findViewById(R.id.btn_sms).setOnClickListener(this);
        findViewById(R.id.btn_contact).setOnClickListener(this);

        PermissionUtil.checkPermissions(this, PERMISSIONS_ALL, REQUEST_CODE_ALL);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sms:
                PermissionUtil.checkPermissions(this, PERMISSIONS_SMS, REQUEST_CODE_SMS);
                break;
            case R.id.btn_contact:
                PermissionUtil.checkPermissions(this, PERMISSIONS_CONTACTS, REQUEST_CODE_CONTACTS);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_ALL:
                if (PermissionUtil.checkGrant(grantResults)) {
                    Log.d("bay", "权限获取成功！");
                    ToastUtil.showMsg(this, "通讯录读写权限获取成功！");
                } else {
                    // 部分权限获取失败
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            switch (permissions[i]) {
                                case Manifest.permission.READ_CONTACTS:
                                case Manifest.permission.WRITE_CONTACTS:
                                    ToastUtil.showMsg(this, "通讯录读写权限获取失败！");
                                    break;
                                case Manifest.permission.READ_SMS:
                                case Manifest.permission.SEND_SMS:
                                    ToastUtil.showMsg(this, "短信读写权限获取失败！");
                                    break;
                            }
                        }
                    }
                    ToastUtil.showMsg(this, "通讯录读写权限获取失败！");
                    jumpToSetting();
                }
                break;
            case REQUEST_CODE_CONTACTS:
                if (PermissionUtil.checkGrant(grantResults)) {
                    Log.d("bay", "通讯录读写权限获取成功！");
                    ToastUtil.showMsg(this, "通讯录读写权限获取成功！");
                } else {
                    ToastUtil.showMsg(this, "通讯录读写权限获取失败！");
                    jumpToSetting();
                }
                break;

            case REQUEST_CODE_SMS:
                if (PermissionUtil.checkGrant(grantResults)) {
                    Log.d("bay", "短信读写权限获取成功！");
                    ToastUtil.showMsg(this, "短信读写权限获取成功！");
                } else {
                    ToastUtil.showMsg(this, "短信读写权限获取失败！");
                    jumpToSetting();
                }
                break;
        }
    }

    // 跳到应用设置界面
    private void jumpToSetting() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", getPackageName(), null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}