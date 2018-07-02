package com.cage.library.utils.permission;

import android.Manifest;
import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.cage.library.CageLibrary;
import com.cage.library.infrastructure.resource.ResourceHelper;
import com.cage.library.utils.device.DeviceUtils;

import java.util.HashMap;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Android 6.0 及以后版本的权限管理
 */
public class PermissionUtils {

    //CALENDAR:   READ_CALENDAR
    //            WRITE_CALENDAR
    //CAMERA:     CAMERA
    //CONTACTS:   READ_CONTACTS
    //            WRITE_CONTACTS
    //            GET_ACCOUNTS
    //LOCATION:   ACCESS_FINE_LOCATION
    //            ACCESS_CORSE_LOCAION
    //PHONE:      READ_RHONE_STATE
    //            CALL_PHONE
    //            READ_CALL_LOG
    //            WRITE_CALL_LOG
    //            ADD_VOICEMAIL
    //            USE_SIP

    //            WRITE_EXTERNAL_STORAGE
    //            READ_EXTERNAL_STORAGE

    public static final int REQUEST_CAMERA_PERM = 101;
    public static final int REQUEST_PHONE_PERM = 102;
    public static final int REQUEST_STORAGE_PERM = 103;
    public static final int REQUEST_LOCATION_PERM = 104;

    @RequiresApi(Build.VERSION_CODES.M)
    private static HashMap<String, String> permissionMap = new HashMap<String, String>(){
        {
            put(Manifest.permission.READ_PHONE_STATE, AppOpsManager.OPSTR_READ_PHONE_STATE);
            put(Manifest.permission.CALL_PHONE, AppOpsManager.OPSTR_CALL_PHONE);
            put(Manifest.permission.CAMERA, AppOpsManager.OPSTR_CAMERA);
            put(Manifest.permission.WRITE_EXTERNAL_STORAGE, AppOpsManager.OPSTR_WRITE_EXTERNAL_STORAGE);
            put(Manifest.permission.READ_EXTERNAL_STORAGE, AppOpsManager.OPSTR_READ_EXTERNAL_STORAGE);
            put(Manifest.permission.ACCESS_FINE_LOCATION, AppOpsManager.OPSTR_FINE_LOCATION);
            put(Manifest.permission.ACCESS_COARSE_LOCATION, AppOpsManager.OPSTR_COARSE_LOCATION);
        }
    };

    public static boolean hasPermissions(String... permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        // MIUI权限判断补丁
        AppOpsManager appOpsManager = DeviceUtils.getSystemService(Context.APP_OPS_SERVICE);
        for (String permission : permissions) {
            int note = appOpsManager.checkOpNoThrow(
                    permissionMap.get(permission),
                    Binder.getCallingUid(),
                    CageLibrary.getAppContext().getPackageName()
            );
            if (note != AppOpsManager.MODE_ALLOWED) {
                return false;
            }
        }

        return EasyPermissions.hasPermissions(CageLibrary.getAppContext(), permissions);
    }


    public static void requestStorage(Object listener, String text){
        EasyPermissions.requestPermissions(listener,
                text,
                REQUEST_STORAGE_PERM,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
    }


    public static void requestLocation(Object listener, String text){
        EasyPermissions.requestPermissions(listener,
                text,
                REQUEST_LOCATION_PERM,
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    public static void requestCamera(Object listener, String text){
        EasyPermissions.requestPermissions(listener,
                text,
                REQUEST_CAMERA_PERM,
                Manifest.permission.CAMERA);
    }

    public static void requestPhone(Object listener, String text){
        EasyPermissions.requestPermissions(listener,
                text,
                REQUEST_PHONE_PERM,
                Manifest.permission.CALL_PHONE);
    }

    public static void settingDialog(Activity activity, String text){

        PackageManager packageManager = CageLibrary.getAppContext().getPackageManager();
        String appName = "应用";
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    CageLibrary.getAppContext().getPackageName(), 0);
            appName = ResourceHelper.getString(packageInfo.applicationInfo.labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        new AppSettingsDialog.Builder(activity, appName + "需要请求 " + text + " 权限，是否打开设置页面?")
                .setTitle("权限申请")
                .setPositiveButton("确定")
                .setNegativeButton("取消", null)
                .setRequestCode(REQUEST_PHONE_PERM)
                .build()
                .show();
    }
}
