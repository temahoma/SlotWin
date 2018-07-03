package com.luyunfeng.outsource.slotwin.view.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by luyunfeng on 16/11/16.
 */

abstract class DVBase {

    abstract int getLayoutId();

    abstract void initView();

    private View view;

    DVBase(Context context) {
        view = LayoutInflater.from(context).inflate(getLayoutId(), null, false);
    }

    public View getView() {
        return view;
    }
}
