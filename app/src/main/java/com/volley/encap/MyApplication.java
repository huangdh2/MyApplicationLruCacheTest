package com.volley.encap;

import android.app.Application;
import android.content.Context;

/**
 * Created by Trea on 2016/5/20.
 */
public class MyApplication extends Application{
    public static String TAG;
    public static Context myApplication;

    public static Context newInstance() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        TAG = this.getClass().getSimpleName();
        myApplication = getApplicationContext();
    }
}
