package com.luyunfeng.outsource.slotwin.utils;

import android.os.Build;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by luyunfeng on 17/4/20.
 */

public class ThreadHelper {
    public static Future submit(Runnable runnable) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        return service.submit(runnable);
    }

    public static void quitThread() {
        Looper looper = Looper.myLooper();
        if(looper == null || looper == Looper.getMainLooper()){
            return;
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2){
            looper.quitSafely();
        }else{
            looper.quit();
        }
    }
}
