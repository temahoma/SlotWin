package com.luyunfeng.outsource.slotwin.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringDef;
import android.support.annotation.StringRes;

import com.luyunfeng.outsource.slotwin.BuildConfig;

/**
 * Created by luyunfeng on 2017/1/10.
 */

public class ResourceHelper {

    private static Context context;

    private static Resources resources;

    public static void init(Context context) {
        ResourceHelper.context = context;
        resources = context.getResources();
    }

    public static Resources getResources() {
        return resources;
    }

    public static float getDimension(@DimenRes int resId) {
        return resources.getDimension(resId);
    }

    /**
     * 获取dp对应的像素值
     */
    public static int getDimensionPixelSize(@DimenRes int resId) {
        return resources.getDimensionPixelSize(resId);
    }

    /**
     * 获取sp对应的像素值
     */
    public static float getSpPixelSize(@DimenRes int resId) {
        return resources.getDimension(resId) / resources.getDisplayMetrics().scaledDensity;
    }

    public static String getString(@StringRes int resId) {
        return resources.getString(resId);
    }

    public static String[] getStringArray(@ArrayRes int resId) {
        return resources.getStringArray(resId);
    }

    public static int getColor(@ColorRes int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return resources.getColor(resId, context.getTheme());
        } else {
            return resources.getColor(resId);
        }
    }

    public static ColorStateList getColorStateList(@ColorRes int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return resources.getColorStateList(resId, context.getTheme());
        } else {
            return resources.getColorStateList(resId);
        }
    }

    public static Drawable getDrawable(@DrawableRes int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return resources.getDrawable(resId, context.getTheme());
        } else {
            return resources.getDrawable(resId);
        }
    }

    public static AssetManager getAssets() {
        return resources.getAssets();
    }

    public static int getIdentifier(String name, @IdentifierType String type) {
        return resources.getIdentifier(name, type, BuildConfig.APPLICATION_ID);
    }

    @StringDef({"id", "drawable", "string"})
    public @interface IdentifierType {}
}
