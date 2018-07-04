package com.cage.library;

import android.annotation.SuppressLint;
import android.content.Context;

import com.cage.library.infrastructure.log.Log;
import com.cage.library.infrastructure.resource.ResourceHelper;
import com.cage.library.infrastructure.tips.Toast;

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
        Toast.init(appContext);
        Log.init(true);
    }
}
