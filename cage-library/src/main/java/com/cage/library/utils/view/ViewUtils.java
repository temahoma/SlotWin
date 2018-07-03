package com.cage.library.utils.view;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by luyunfeng on 2018/7/3.
 */

public class ViewUtils {

    public static View inflateView(ViewGroup viewGroup, @LayoutRes int layoutId) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false);
    }

    public static View inflateView(Activity activity, @LayoutRes int layoutId) {
        View view = LayoutInflater.from(activity).inflate(layoutId, null);
        ViewGroup root = activity.findViewById(android.R.id.content);
        root.addView(view);
        return view;
    }
}
