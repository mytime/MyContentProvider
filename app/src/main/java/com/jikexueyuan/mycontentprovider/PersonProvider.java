package com.jikexueyuan.mycontentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class PersonProvider extends ContentProvider {

    private SQLiteDatabase db;

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
        db.insert("person",null,values);
        return uri;
    }
    //删除后返回删除数量
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int i = db.delete("person",selection,selectionArgs);
        return i;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int i = db.update("person",values,selection,selectionArgs);
        return i;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor = db.query("person", projection, selection, selectionArgs, null, null, selection);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }






}
