package com.luyunfeng.outsource.slotwin.utils;

import android.Manifest;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.luyunfeng.outsource.slotwin.MyApplication;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created by luyunfeng on 17/8/22.
 * 设备相关
 */

public class DeviceUtils {
    // 屏幕分辨率
    public static int mScreenWidth;
    // 除去虚拟按键的屏幕高度
    public static int mScreenHeight;
    // 状态栏高度
    public static int mStatusBarHeight;
    // 虚拟按键高度
    public static int mNavigationBarHeight;
    // dip
    public static float mDensity;

    public static int getStatusBarHeight() {
        int statusBarHeight = 0;
        try {
            Resources res = MyApplication.getContext().getResources();
            int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
            statusBarHeight = ResourceHelper.getDimensionPixelSize(resourceId);
        } catch (Exception e) {

        }
        return statusBarHeight;
    }


    // vivo判断失效
    private static boolean hasNavigationBar() {
        boolean hasNavigationBar = false;
        try {
            Resources res = MyApplication.getContext().getResources();
            int id = res.getIdentifier("config_showNavigationBar", "bool", "android");
            if (id > 0) {
                hasNavigationBar = res.getBoolean(id);
            }
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {

        }
        return hasNavigationBar;
    }


    public static boolean is21OrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static boolean is23OrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public static <T> T getSystemService(String name) {
        return (T) MyApplication.getContext().getSystemService(name);
    }
}
