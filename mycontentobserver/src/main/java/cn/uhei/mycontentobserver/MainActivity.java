package cn.uhei.mycontentobserver;

import android.content.ContentResolver;
import android.database.ContentObservable;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //注册一个内容观察者,监听短息
        ContentResolver cr = getContentResolver();
        //Uri:监听谁
        //true:开启模糊监听，无论是收件箱/发件箱/垃圾箱/草稿箱都监听
        cr.registerContentObserver(Uri.parse("content://sms"),true,new MyObserver(new Handler()));

    }
    class MyObserver extends ContentObserver{
        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public MyObserver(Handler handler) {
            super(handler);
        }

        /**
         * 收到数据改变的通知，次方法调用
         * @param selfChange
         */
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            System.out.println("短信数据库改变了");
        }
    }
    }

