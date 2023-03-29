package com.example.chapter06.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.chapter06.dao.BookDao;
import com.example.chapter06.enity.BookInfo;

// 该注解用于标识一个数据库类，entities属性用于指定数据库中的表，version属性用于指定数据库版本
// exportSchema属性用于指定是否导出数据库信息的json文件，true表示导出，false表示不导出
// 设置为true时，还需指定json文件的存放路径(在build.gradle中设置, android-defaultConfig-javaCompileOptions中设置)
@Database(entities = {BookInfo.class}, version = 1, exportSchema = true)
public abstract class BookDatabase extends RoomDatabase {
    public abstract BookDao bookDao();
}
