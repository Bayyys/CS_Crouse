package com.example.chapter08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.chapter08.util.ToastUtil;

public class SpinnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // 定义下拉列表显示的文本数组
    private final static String[] array = {"null", "101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "201", "202", "203", "204", "205", "206", "207", "208", "209", "210", "301", "302", "303", "304", "305", "306", "307", "308", "309", "310", "401", "402", "403", "404", "405", "406", "407", "408", "409", "410", "501", "502", "503", "504", "505", "506", "507", "508", "509", "510", "601", "602", "603", "604", "605", "606", "607", "608", "609", "610", "701", "702", "703", "704", "705", "706", "707", "708", "709", "710", "801", "802", "803", "804", "805", "806", "807", "808", "809", "810", "901", "902", "903", "904", "905", "906", "907", "908", "909", "910"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        Spinner sp_dropdown = findViewById(R.id.sp_dropdown);
        // 声明一个数组适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_select, array);
        sp_dropdown.setPrompt("请选择房间号"); // 设置下拉列表的标题, 仅在dialog模式下有效
        sp_dropdown.setAdapter(adapter);    // 设置下拉列表的数据源
        sp_dropdown.setSelection(0);    // 设置默认选中项，此处为默认选中第0个值
        sp_dropdown.setOnItemSelectedListener(this);    // 设置下拉列表的监听器
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.show(this, "您选择的是：" + array[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}