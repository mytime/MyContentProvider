package com.jikexueyuan.mycontentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chebao360 on 15-12-25.
 */
public class MyOpenHelper extends SQLiteOpenHelper {
    public MyOpenHelper(Context context) {
        super(context, "people.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table person(_id integer primary key autoincrement,name char(10),phone char(20),money integer(10))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("create table handsome(_id integer primary key autoincrement,name char(10),phone char(20))");

    }
}
