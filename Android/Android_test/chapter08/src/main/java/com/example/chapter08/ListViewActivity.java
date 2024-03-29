package com.example.chapter08;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chapter08.adapter.PlanetBaseAdapter;
import com.example.chapter08.entity.Planet;
import com.example.chapter08.util.ToastUtil;
import com.example.chapter08.util.Utils;

import java.util.List;

public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, CompoundButton.OnCheckedChangeListener {

    private List<Planet> planetList;
    private CheckBox ck_diviver;
    private CheckBox ck_selector;
    private ListView lv_planet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        // 从布局文件中获取名叫lv_planet的列表视图
        lv_planet = findViewById(R.id.lv_planet);
        // 获取行星列表
        planetList = Planet.getDefaultList();
        // 创建一个行星列表的基于适配器
        PlanetBaseAdapter adapter = new PlanetBaseAdapter(this, planetList);
        // 给lv_planet设置行星列表的基于适配器
        lv_planet.setAdapter(adapter);
        // 给lv_planet设置列表项的点击监听器
        lv_planet.setOnItemClickListener(this);
        // 从布局文件中获取名叫ck_divider的复选框
        ck_diviver = findViewById(R.id.ck_divider);
        ck_diviver.setOnCheckedChangeListener(this);
        ck_selector = findViewById(R.id.ck_selector);
        ck_selector.setOnCheckedChangeListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.show(this, "您选择的是：" + planetList.get(position).name);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.ck_divider:
                // 显示分隔线
                if (ck_diviver.isChecked()) {
                    // 从资源文件获得图形对象
                    Drawable drawable = getResources().getDrawable(R.color.black, getTheme());
                    lv_planet.setDivider(drawable);
                    // 设置列表视图的分隔线高度
                    lv_planet.setDividerHeight(Utils.dip2px(this, 1));
                } else {
                    lv_planet.setDivider(null);
                    lv_planet.setDividerHeight(0);
                }
                break;

            case R.id.ck_selector:
                // 显示按压背景
                if (ck_selector.isChecked()) {
                    // 设置列表项的按压状态图形
                    lv_planet.setSelector(R.drawable.list_selector);
                } else {
                    Drawable drawable = getResources().getDrawable(R.color.transparent, getTheme());
                    lv_planet.setSelector(drawable);
                }
                break;
        }
    }
}