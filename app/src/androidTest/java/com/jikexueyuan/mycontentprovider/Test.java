package com.jikexueyuan.mycontentprovider;

import android.test.AndroidTestCase;

/**
 * Created by chebao360 on 15-12-25.
 */
public class Test extends AndroidTestCase {

    public void test(){
        MyOpenHelper oh = new MyOpenHelper(getContext());
        oh.getWritableDatabase();
    }
}
