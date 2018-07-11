package com.cage.library.infrastructure;

import android.content.Context;
import android.content.SharedPreferences;

import com.cage.library.CageLibrary;
import com.cage.library.utils.app.AppUtils;

/**
 * Created by LuYunfeng on 2015/10/13.
 * sharePreference
 */
public class SPUtils {

    private static SharedPreferences sharedPreferences;

    public static SharedPreferences getSharedPreferences() {
        if (sharedPreferences == null) {
            String appName = AppUtils.getHostAppName();
            sharedPreferences = CageLibrary.getAppContext()
                    .getSharedPreferences(appName, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    public static int getInt(String key) {
        return getInt(key, 0);
    }

    public static int getInt(String key, int fallback) {
        return getSharedPreferences().getInt(key, fallback);
    }

    public static void putInt(String key, int value) {
        getSharedPreferences()
                .edit()
                .putInt(key, value)
                .apply();
    }

    public static String getString(String key) {
        return getString(key, "");
    }

    public static String getString(String key, String fallback) {
        return getSharedPreferences().getString(key, fallback);
    }

    public static void putString(String key, String value) {
        getSharedPreferences()
                .edit()
                .putString(key, value)
                .apply();
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean fallback) {
        return getSharedPreferences().getBoolean(key, fallback);
    }

    public static void putBoolean(String key, boolean value) {
        getSharedPreferences()
                .edit()
                .putBoolean(key, value)
                .apply();
    }

    public static long getLong(String key) {
        return getLong(key, 0);
    }

    public static long getLong(String key, long fallback) {
        return getSharedPreferences().getLong(key, fallback);
    }

    public static void putLong(String key, long value) {
        getSharedPreferences()
                .edit()
                .putLong(key, value)
                .apply();
    }
}
