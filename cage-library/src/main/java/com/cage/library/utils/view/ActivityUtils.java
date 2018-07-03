package com.cage.library.utils.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by luyunfeng on 2018/6/28.
 */

public class ActivityUtils {

    /**
     * 防止界面快速关闭时，还有数据消息队列发送消息
     */
    public static boolean isAlive(@Nullable Activity activity) {
        if (activity == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (activity.isDestroyed() || activity.isFinishing()) {
                return false;
            }
        } else if (activity.isFinishing()) {
            return false;
        }
        return true;
    }

    public static void open(Activity activity, Class cls) {
        open(activity, cls, null);
    }

    public static void open(Activity activity, Class cls, @Nullable Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(activity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
    }

    public static void open4Result(Activity activity, Class cls, int requestCode) {
        open4Result(activity, cls, requestCode, null);
    }

    public static void open4Result(Activity activity, Class cls, int requestCode, @Nullable Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(activity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivityForResult(intent, requestCode);
    }
}
