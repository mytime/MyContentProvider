package cn.uhei.myproviderclient;

import android.content.ContentResolver;


import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Uri uri;
    private ContentValues values;
    private ContentResolver resolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void insert(View v){
        //通过内容提供者把数据出入到com.jikexueyuan.mycontentprovider 数据库
        //获取ContentResolver (内容拆包)
        resolver = getContentResolver();
//        uri = Uri.parse("content://com.jikexueyuan.person");
        values = new ContentValues();

        values.put("name", "晓庄");
        values.put("phone", 133800);
        values.put("money", 15000);
        resolver.insert(Uri.parse("content://com.jikexueyuan.person/person"), values);

        values.clear();
        values.put("name", "小山");
        values.put("phone", 136000);
        resolver.insert(Uri.parse("content://com.jikexueyuan.person/handsome"), values);
    }
    public void delete(View v){
        resolver = getContentResolver();
        uri = Uri.parse("content://com.jikexueyuan.person/person");
        int i = resolver.delete(uri,"name = ?",new String[]{"小山"});
        System.out.println(i);
    }
    public void update(View v){
        resolver = getContentResolver();
        uri = Uri.parse("content://com.jikexueyuan.person/person");
        ContentValues values = new ContentValues();
        values.put("money",28000);
        int i = resolver.update(uri, values, "name = ?", new String[]{"晓庄"});
        System.out.println(i);
    }
    public void query(View v){
        resolver = getContentResolver();
        uri = Uri.parse("content://com.jikexueyuan.person/person");
        Cursor cursor = resolver.query(uri, null, null, null, null);
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            String money = cursor.getString(cursor.getColumnIndex("money"));
            System.out.println(name+phone+money);;
        }
    }
    public void queryOne(View v){
        resolver = getContentResolver();
        //1:_id=1,在ContentProvider中定义
//        uri = Uri.parse("content://com.jikexueyuan.person/person/1");
        Cursor cursor = resolver.query(Uri.parse("content://com.jikexueyuan.person/person/1"),null,null,null,null);
        if (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            String money = cursor.getString(cursor.getColumnIndex("money"));
            System.out.println(name+phone+money);;
        }
    }
}
