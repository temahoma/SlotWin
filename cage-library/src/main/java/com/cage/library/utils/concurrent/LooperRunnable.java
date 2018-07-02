package com.cage.library.utils.concurrent;

import android.os.Looper;

/**
 * Created by luyunfeng on 17/5/2.
 */
public abstract class LooperRunnable implements Runnable {

    public abstract void runInLooper();

    public LooperRunnable(){
        start();
    }

    @Override
    public void run() {
        Looper.prepare();
        runInLooper();
        Looper.loop();
    }

    public void start(){
        ThreadHelper.submit(this);
    }
}
