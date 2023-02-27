package com.example.chapter06;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chapter06.database.ShoppingDBHelper;
import com.example.chapter06.enity.CartInfo;
import com.example.chapter06.enity.GoodsInfo;
import com.example.chapter06.util.ToastUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCartActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_count;
    private LinearLayout ll_cart;
    private ShoppingDBHelper mDBHelper;
    // 声明一个购物车的商品列表
    private List<CartInfo> mCartList;
    // 声明一个根据商品编号查找商品信息的映射，将商品信息缓存起来
    private Map<Integer, GoodsInfo> mGoodsMap = new HashMap<>();
    private TextView tv_total_price;
    private LinearLayout ll_empty;
    private LinearLayout ll_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText("购物车");
        ll_cart = findViewById(R.id.ll_cart);
        ll_content = findViewById(R.id.ll_content);
        ll_empty = findViewById(R.id.ll_empty);
        tv_total_price = findViewById(R.id.tv_total_price);

        mDBHelper = ShoppingDBHelper.getInstance(this);


        tv_count = findViewById(R.id.tv_count);
        tv_count.setText(String.valueOf(MyApplication.getInstance().goodsCount));

        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.iv_cart).setOnClickListener(this);
        findViewById(R.id.btn_shopping_channel).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.btn_settle).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showCart();
        showCount();
    }

    // 显示购物车中的商品
    private void showCart() {

        // 清空购物车中的商品
        ll_cart.removeAllViews();
        mCartList = mDBHelper.queryAllCartInfo();
        if (mCartList.size() == 0) {
            showCount();
            return;
        }

        // 显示购物车中的商品
        for (CartInfo info : mCartList) {
            GoodsInfo goods = mDBHelper.queryGoodsInfoById(info.goodsId);
            mGoodsMap.put(info.goodsId, goods);
            View view = LayoutInflater.from(this).inflate(R.layout.item_cart, null);
            ImageView iv_thumb = view.findViewById(R.id.iv_thumb);
            TextView tv_name = view.findViewById(R.id.tv_name);
            TextView tv_desc = view.findViewById(R.id.tv_desc);
            TextView tv_price = view.findViewById(R.id.tv_price);
            TextView tv_count = view.findViewById(R.id.tv_count);
            TextView tv_sum = view.findViewById(R.id.tv_sum);

            iv_thumb.setImageURI(Uri.parse(goods.picPath));
            tv_name.setText(goods.name);
            tv_desc.setText(goods.description);
            tv_price.setText(String.valueOf((int) goods.price));
            tv_count.setText(String.valueOf(info.count));
            // 计算商品的总价
            tv_sum.setText(String.valueOf((int) info.count * goods.price));

            // 给商品行添加长按事件。长按商品行，删除该商品
            view.setOnLongClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCartActivity.this);
                builder.setMessage("确定要删除" + goods.name + "吗？");
                builder.setPositiveButton("是", (dialog, which) -> {
                    ll_cart.removeView(view);
                    deleteGoods(info);
                    showCart();
                });
                builder.setNegativeButton("否", null);
                builder.create().show();
                return true;
            });

            view.setOnClickListener(v -> {
                Intent intent = new Intent(ShoppingCartActivity.this, ShoppingDetailActivity.class);
                intent.putExtra("goods_id", goods.id);
                startActivity(intent);
            });

            // 将商品信息添加到购物车中
            ll_cart.addView(view);
        }

        // 重新计算购物车中的商品总数
        refreshTotalPrice();

    }

    private void deleteGoods(CartInfo info) {
        MyApplication.getInstance().goodsCount -= info.count;
        // 从购物车的数据库中删除该商品
        mDBHelper.deleteCartInfoByGoodsId(info.goodsId);
        // 从购物车的列表中删除该商品
        CartInfo removed = null;
        for (CartInfo cartinfo : mCartList) {
            if (cartinfo.goodsId == info.goodsId) {
                removed = cartinfo;
                break;
            }
        }
        if (removed != null) {
            mCartList.remove(removed);
        }
        showCount();
        ToastUtil.show(this, "已从购物车中删除" + mGoodsMap.get(info.goodsId).name);
        mGoodsMap.remove(info.goodsId);
        refreshTotalPrice();

    }

    private void showCount() {
        tv_count.setText(String.valueOf(MyApplication.getInstance().goodsCount));
        if (MyApplication.getInstance().goodsCount == 0) {
            ll_empty.setVisibility(View.VISIBLE);
            ll_content.setVisibility(View.GONE);
            ll_cart.removeAllViews();
        } else {
            ll_content.setVisibility(View.VISIBLE);
            ll_empty.setVisibility(View.GONE);
        }
    }

    private void refreshTotalPrice() {
        int totalPrice = 0;
        for (CartInfo info : mCartList) {
            GoodsInfo goods = mGoodsMap.get(info.goodsId);
            totalPrice += info.count * goods.price;
        }
        tv_total_price.setText(String.valueOf(totalPrice));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_cart:
                break;
            case R.id.btn_shopping_channel:
                Intent intent = new Intent(this, ShoppingChannelActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.btn_clear:
                mDBHelper.deleteAllCartInfo();
                MyApplication.getInstance().goodsCount = 0;
                showCount();
                ToastUtil.show(this, "购物车已清空");
                break;
            case R.id.btn_settle:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("确定要结算吗？");
                builder.setPositiveButton("是", (dialog, which) -> {
                    mDBHelper.deleteAllCartInfo();
                    MyApplication.getInstance().goodsCount = 0;
                    showCount();
                    ToastUtil.show(this, "结算成功");
                });
                builder.setNegativeButton("否", null);
                builder.create().show();
                break;
        }
    }
}