package com.luyunfeng.outsource.slotwin.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

public class GsonUtils {

    private volatile static Gson mBaseGson = null;

    public static Gson getIns() {
        if (mBaseGson == null) {
            synchronized (GsonUtils.class) {
                if (mBaseGson == null) {
                    mBaseGson = new GsonBuilder()
                            .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                            .create();
                }
            }
        }
        return mBaseGson;
    }
}
