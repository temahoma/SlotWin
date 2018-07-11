package com.cage.library.utils.app;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.cage.library.CageLibrary;
import com.cage.library.infrastructure.resource.ResourceHelper;

/**
 * Created by luyunfeng on 2018/6/26.
 */

public class AppUtils {
    public static String getHostAppName() {
        PackageManager packageManager = CageLibrary.getAppContext().getPackageManager();
        String appName = "应用";
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    CageLibrary.getAppContext().getPackageName(), 0);
            appName = ResourceHelper.getString(packageInfo.applicationInfo.labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appName;
    }
}
