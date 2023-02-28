package com.example.chapter07_server.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.chapter07_server.UserInfoContent;
import com.example.chapter07_server.database.UserDBHelper;

public class UserInfoProvider extends ContentProvider {

    private UserDBHelper dbHelper;
    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int USERS = 1;
    private static final int USER = 2;

    static {
        // 往Uri匹配器中添加指定的数据路径
        URI_MATCHER.addURI(UserInfoContent.AUTHORITIES, "user", USERS);
        URI_MATCHER.addURI(UserInfoContent.AUTHORITIES, "user/#", USER);
    }

    @Override
    public boolean onCreate() {
        Log.d("bay", "UserInfoProvider onCreate");
        dbHelper = UserDBHelper.getInstance(getContext());
        return true;
    }

    // content://com.example.chapter07_server.provider.UserInfoProvider/user
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (URI_MATCHER.match(uri) != USERS) {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
        Log.d("bay", "UserInfoProvider insert");
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long rowId = db.insert(UserDBHelper.TABLE_NAME, null, values);
        if (rowId > 0) {    // 判断插入是否执行成功
            // 如果添加成功，就利用新纪录的行号生成新的地址
            Uri newUri = ContentUris.withAppendedId(UserInfoContent.CONTENT_URI, rowId);
            // 通知监听器，数据已经改变
            getContext().getContentResolver().notifyChange(newUri, null);
        }
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (URI_MATCHER.match(uri)) {
            // content://com.example.chapter07_server.provider.UserInfoProvider/user
            // 删除多行
            case USERS:
                count = db.delete(UserDBHelper.TABLE_NAME, selection, selectionArgs);
                break;

            // content://com.example.chapter07_server.provider.UserInfoProvider/user/1
            // 删除单行
            case USER:
                String id = uri.getLastPathSegment();
                count = db.delete(UserDBHelper.TABLE_NAME, "_id=?", new String[]{id});
                break;
        }
        db.close();
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.d("bay", "UserInfoProvider query");
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(UserDBHelper.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

}