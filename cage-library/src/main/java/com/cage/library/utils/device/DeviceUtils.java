package com.cage.library.utils.device;

import android.Manifest;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.cage.library.CageLibrary;
import com.cage.library.infrastructure.resource.ResourceHelper;
import com.cage.library.utils.permission.PermissionUtils;

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

    /**
     * 生成设备唯一标识码
     *
     * @return
     */
    public static String getDeviceUUID() {
        try {
            //获取设备信息前先请求权限
            boolean hasPermissions = PermissionUtils.hasPermissions(Manifest.permission.READ_PHONE_STATE);

            if (!hasPermissions) {
                return "Permission-Denied-UUID";
            }

            final String tmDevice, tmSerial, androidId;

            final TelephonyManager tm = getSystemService(Context.TELEPHONY_SERVICE);

            tmDevice = tm.getDeviceId();

            tmSerial = tm.getSimSerialNumber();

            androidId = "" + Settings.Secure.getString(CageLibrary.getAppContext().getContentResolver(), Settings.Secure.ANDROID_ID);

            UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());

            // Log.d("test", "deviceUuid = " + deviceUuid.toString());
            return deviceUuid.toString();
        } catch (Exception e) {
            return "EMPTY_UUID";
        }
    }

    public static int getStatusBarHeight() {
        int statusBarHeight = 0;
        try {
            Resources res = CageLibrary.getAppContext().getResources();
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
            Resources res = CageLibrary.getAppContext().getResources();
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
        return (T) CageLibrary.getAppContext().getSystemService(name);
    }
}
