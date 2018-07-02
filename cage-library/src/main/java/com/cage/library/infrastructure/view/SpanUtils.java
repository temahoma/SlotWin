package com.cage.library.infrastructure.view;

import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;

import com.cage.library.infrastructure.resource.ResourceHelper;

/**
 * Created by luyunfeng on 17/1/11.
 */
public class SpanUtils {
    private static AbsoluteSizeSpan getSizeSpan(@DimenRes int resId){
        if (resId == 0) return null;
        float dimen = ResourceHelper.getSpPixelSize(resId);
        if (dimen == 0) return null;
        return new AbsoluteSizeSpan((int) dimen, true);
    }

    private static ForegroundColorSpan getColorSpan(@ColorRes int resId){
        if (resId == 0) return null;
        int color = ResourceHelper.getColor(resId);
        if (color == 0) return null;
        return new ForegroundColorSpan(color);
    }

    public static void setSizeSpanEE(Spannable spannable, float dimen, int start, int end){
        setSpanEE(spannable, new AbsoluteSizeSpan((int) dimen, true), start, end);
    }

    public static void setSizeSpanEE(Spannable spannable, @DimenRes int dimenId, int start, int end){
        setSpanEE(spannable, getSizeSpan(dimenId), start, end);
    }

    public static void setColorIntSpanEE(Spannable spannable, @ColorInt int color, int start, int end){
        setSpanEE(spannable, new ForegroundColorSpan(color), start, end);
    }

    public static void setColorResSpanEE(Spannable spannable, @ColorRes int colorId, int start, int end){
        setSpanEE(spannable, getColorSpan(colorId), start, end);
    }

    public static void setStrikeSpanEE(Spannable spannable, int start, int end){
        spannable.setSpan(new StrikethroughSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    public static void setStyleSpanEE(Spannable spannable, int style, int start, int end){
        spannable.setSpan(new StyleSpan(style), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    public static void setSpanEE(Spannable spannable, Object span, int start, int end){
        spannable.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

}
