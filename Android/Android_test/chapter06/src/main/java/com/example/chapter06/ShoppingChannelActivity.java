package com.example.chapter06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Ringtone;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chapter06.database.ShoppingDBHelper;
import com.example.chapter06.enity.GoodsInfo;
import com.example.chapter06.util.ToastUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class ShoppingChannelActivity extends AppCompatActivity implements View.OnClickListener {
    private ShoppingDBHelper mDBHelper;
    private TextView tv_count;
    private GridLayout gl_channel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_channel);
        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText("Market");

        tv_count = findViewById(R.id.tv_count);
        gl_channel = findViewById(R.id.gl_channel);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.iv_cart).setOnClickListener(this);

        mDBHelper = ShoppingDBHelper.getInstance(this);
        mDBHelper.openReadLink();
        mDBHelper.openWriteLink();


        // 从数据库中读取商品信息，并展示
        showGoods();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 查询购物车商品总数，并展示
        showCartInfoTotal();
    }

    private void showCartInfoTotal() {
        int count = mDBHelper.countCartInfo();
        MyApplication.getInstance().goodsCount = count;
        tv_count.setText(String.valueOf(count));
    }

    private void showGoods() {
        // 商品条目为线形布局，设置布局的宽度为屏幕的一半
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenWidth / 2, LinearLayout.LayoutParams.WRAP_CONTENT);
        // 查询商品数据库中的所有商品记录
        List<GoodsInfo> list = mDBHelper.queryAllGoodsInfo();
        // 移除所有的商品信息
        gl_channel.removeAllViews();
        for (GoodsInfo info : list) {
            // 获取布局 文件item_goods.xml的根视图
            View view = LayoutInflater.from(this).inflate(R.layout.item_goods, null);
            ImageView iv_thumb = view.findViewById(R.id.iv_thumb);
            TextView tv_name = view.findViewById(R.id.tv_name);
            TextView tv_price = view.findViewById(R.id.tv_price);
            Button btn_add = view.findViewById(R.id.btn_add);
            btn_add.setOnClickListener(v -> {
                // 添加到购物车
                addToCart(info.id, info.name);
            });

            // 设置商品信息的控件信息
            iv_thumb.setImageURI(Uri.parse(info.picPath));
            tv_name.setText(info.name);
            tv_price.setText(String.valueOf((int) info.price));

            // 点击商品图片，跳转到商品详情页面
            iv_thumb.setOnClickListener(v -> {
                Intent intent = new Intent(ShoppingChannelActivity.this, ShoppingDetailActivity.class);
                intent.putExtra("goods_id", info.id);
                startActivity(intent);
            });
            // 将商品信息的根视图添加到网格布局中
            gl_channel.addView(view, params);
        }
    }

    private void addToCart(int id, String name) {
        mDBHelper.insertCartInfo(id);
        int count = ++MyApplication.getInstance().goodsCount;
        tv_count.setText(String.valueOf(count));
        ToastUtil.show(this, "Add " + name + " to cart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDBHelper.closeLink();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_cart:
                // 点击购物车图标
                // 从商场页面跳到购物车页面
                Intent intent = new Intent(this, ShoppingCartActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                // 设置启动标志，避免多次返回同一页面
                startActivity(intent);
                break;
        }
    }
}