package com.luyunfeng.outsource.slotwin;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.cage.library.CageLibrary;

/**
 * Created by luyunfeng on 15/10/26.
 * 全局资源管理类
 */
public class MyApplication extends Application
{
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        CageLibrary.init(context);
    }
}
