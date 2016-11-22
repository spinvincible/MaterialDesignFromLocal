package com.geekyapps.materialdesign;

import android.app.Application;
import android.content.Context;

/**
 * Created by Nixxmare on 11/17/2016.
 */

public class MyApplication extends Application {
private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static MyApplication getInstance(){
        return mInstance;
    }

    public static Context getAppContext(){
        return mInstance.getApplicationContext();
    }

}
