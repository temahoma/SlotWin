package com.cage.library;

import android.annotation.SuppressLint;
import android.content.Context;

import com.cage.library.infrastructure.resource.ResourceHelper;

/**
 * Created by luyunfeng on 2018/6/26.
 */

public class CageLibrary {

    @SuppressLint("StaticFieldLeak")
    private static Context appContext;

    public static Context getAppContext() {
        return appContext;
    }

    public static void init(Context appContext) {
        CageLibrary.appContext = appContext;
        ResourceHelper.init(appContext);
    }
}
