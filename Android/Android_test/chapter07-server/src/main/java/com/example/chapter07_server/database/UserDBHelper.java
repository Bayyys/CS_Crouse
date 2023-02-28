package com.example.chapter07_server.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "user.db";
    private static final int VERSION = 1; // 数据库版本号, 运行时版本号与数据库版本号不一致时，会调用onUpgrade()方法
    public static final String TABLE_NAME = "user_info";
    private static UserDBHelper mHelper = null;

    public UserDBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    // 利用单例模式获取数据库帮助器的唯一实例
    public static UserDBHelper getInstance(Context context) {
        if (mHelper == null) {
            mHelper = new UserDBHelper(context);
        }
        return mHelper;
    }

    // 创建数据库，执行建表语句
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " name VARCHAR NOT NULL," +
                " age INTEGER NOT NULL," +
                " height LONG NOT NULL," +
                " weight LONG NOT NULL," +
                " married INTEGER NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
