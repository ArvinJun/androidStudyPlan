package com.arviv.skinTest;

import android.app.Application;
import android.content.Context;

public class SkinApplication extends Application {

    public static  SkinApplication application;
    @Override
    public void onCreate() {

        application =this;

        super.onCreate();
    }

    public static Context getApp(){


        return  application.getApplicationContext();
    }
}
