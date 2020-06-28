package com.mygate.licious;

import android.app.Application;
import android.content.Context;


public class AppController extends Application {

    private static AppController mInstance;
    private static Context sContext;

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public static Context getContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        mInstance = this;
        sContext = this;
        super.onCreate();
    }
}
