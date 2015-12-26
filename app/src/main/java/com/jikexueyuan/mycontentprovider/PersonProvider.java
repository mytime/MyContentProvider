package com.jikexueyuan.mycontentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class PersonProvider extends ContentProvider {

    private SQLiteDatabase db;
    //创建uri匹配器，操作多表
    UriMatcher um = new UriMatcher(UriMatcher.NO_MATCH);

    {
        //添加匹配规则
        //arg0:主机名,
        //arg1:路径
        //arg2:匹配码
        //#:数字，*：文本
        //数字通常做为查询时where条件来使用
        um.addURI("com.jikexueyuan.person", "person", 1);//content://com.jikexueyuan.person/person
        um.addURI("com.jikexueyuan.person", "handsome", 2);//content://com.jikexueyuan.person/handsome
        um.addURI("com.jikexueyuan.person", "person/#", 3);//content://com.jikexueyuan.person/person/2
    }

    public PersonProvider() {
    }

    //内容提供者创建时调用
    @Override
    public boolean onCreate() {
        MyOpenHelper oh = new MyOpenHelper(getContext());
        db = oh.getWritableDatabase();
        return false;
    }

    //values:其他应用要插入的数据
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        if (um.match(uri) == 1) {
            db.insert("person", null, values);
        } else if (um.match(uri) == 2) {
            db.insert("handsome", null, values);
        } else {
            throw new IllegalArgumentException("uri传错了");
        }
        return uri;
    }

    //删除后返回删除数量
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int i;
        if (um.match(uri) == 1) {
            i = db.delete("person", selection, selectionArgs);
        } else if (um.match(uri) == 2) {
            i = db.delete("handsome", selection, selectionArgs);
        } else {
            throw new IllegalArgumentException("uri传错了");
        }
        return i;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int i;
        if (um.match(uri) == 1) {
            i = db.update("person", values, selection, selectionArgs);
        } else if (um.match(uri) == 2) {
            i = db.update("handsome", values, selection, selectionArgs);
        } else {
            throw new IllegalArgumentException("uri传错了");
        }

        return i;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        if (um.match(uri) == 1) {
            cursor = db.query("person", projection, selection, selectionArgs, null, null,sortOrder,null);
        } else if (um.match(uri) == 2) {
            cursor = db.query("handsome", projection, selection, selectionArgs, null, null, sortOrder,null);
        }else if (um.match(uri) == 3) {
            //取出uri末尾携带的数字
            long id = ContentUris.parseId(uri);
            cursor = db.query("person", projection, "_id = ?", new String[]{""+id}, null, null, sortOrder,null);
        } else {
            throw new IllegalArgumentException("uri传错了");
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }


}
