package com.example.chapter09;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chapter09.Helper.USBHelper;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class USBTestActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnFindAll, btnOpenOne;
    private TextView tvContent;
    private TextView text;
    private Button btn_send;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usbtest);
        btnFindAll = findViewById(R.id.btn_findAll);
        btnFindAll.setOnClickListener(this);
        btnOpenOne = findViewById(R.id.btn_openOne);
        btnOpenOne.setOnClickListener(this);
        tvContent = findViewById(R.id.tv_content);
        text = findViewById(R.id.text);
        btn_send = findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_findAll:
                List<UsbDevice> list = USBHelper.getInstance(this).getUsbDevices();
                String str = "";
                for (UsbDevice device : list) {
                    str = str + device.getDeviceName() + "\n";
                }
                if (TextUtils.isEmpty(str)) str = "当前设备没有接入USB设备";
                tvContent.setText(str);
                count = 0;
                break;
            case R.id.btn_openOne:
                List<UsbDevice> list2 = USBHelper.getInstance(this).getUsbDevices();
                for (UsbDevice device : list2) {
                    int statue = USBHelper.getInstance(this).connection(device.getVendorId(), device.getProductId());
                    tvContent.setText("连接状态：成功" + "\n" + "设备名称：" + device.getDeviceName() + "\n" + "设备ID：" + device.getDeviceId() + "\n" + "设备VID：" + device.getVendorId() + "\n" + "设备PID：" + device.getProductId() + "\n" + "设备版本：" + device.getVersion());
                }

                break;
            case R.id.btn_send:
                USBHelper.getInstance(this).sendData("message".getBytes());
                text.setText("第" + count++ + "次" + "收到：Some message!\n(非特定消息内容，仅判断是否收到消息)");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        USBHelper.getInstance(this).close();
    }
}