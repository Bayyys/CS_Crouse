package com.example.chapter06;

import android.app.Application;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;

import com.example.chapter06.database.BookDatabase;
import com.example.chapter06.database.ShoppingDBHelper;
import com.example.chapter06.enity.GoodsInfo;
import com.example.chapter06.util.FileUtil;
import com.example.chapter06.util.SharedUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class MyApplication extends Application {
    private static MyApplication mApp = null;   //  声明一个应用实例对象
    public HashMap<String, String> infoMap = new HashMap<String, String>();
    private BookDatabase bookDatabase;  // 声明一个数据库对象
    public int goodsCount; // 购物车中商品的总数量

    public static MyApplication getInstance() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("bayyy", "onCreate");
        mApp = this;

        bookDatabase = Room.databaseBuilder(this, BookDatabase.class, "book")
                // 允许迁移数据库（发生数据库变更时，Room默认删除原数据库再创建新数据库。如此一来原来的记录会丢失，故而要改为迁移方式以便保存原有记录）
                .addMigrations()
                // 允许在主线程中操作数据库（Room默认不能在主线程中操作数据库）
                .allowMainThreadQueries()
                .build();

        initGoodsInfo();
    }

    private void initGoodsInfo() {
        // 获取共享参数保存的是否首次打开参数
        boolean isFirst = SharedUtil.getInstance(this).readBoolean("first", true);
        // 获取当前App的私有下载路径
        String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath().toString();
        String dirPath = directory + File.separatorChar + getPackageName();
        File dirPathFile = new File(dirPath);
        if (!dirPathFile.exists()) {
            dirPathFile.mkdirs();
            Log.d("bay", dirPath + "创建成功");
        } else {
            Log.d("bay", dirPath + "已存在");
        }
        if (isFirst) {
            // 模拟网络图片下载
            List<GoodsInfo> list = GoodsInfo.getDefaultList();
            for (GoodsInfo info : list) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), info.pic);
                String path = dirPath + File.separatorChar + info.id + ".jpg";
                // 向存储空间保存商品图片
                FileUtil.saveImage(path, bitmap);
                bitmap.recycle();
                info.picPath = path;
            }
            ShoppingDBHelper dbHelper = ShoppingDBHelper.getInstance(this);
            dbHelper.openWriteLink();
            dbHelper.insertGoodsInfos(list);
            dbHelper.closeLink();
            // 保存首次打开的标志
            SharedUtil.getInstance(this).writeBoolean("first", false);
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d("bayyy", "onTerminate");
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("bayyy", "onConfigurationChanged");
    }

    public BookDatabase getBookDB() {
        return bookDatabase;
    }
}
