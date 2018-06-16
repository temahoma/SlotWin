package com.luyunfeng.outsource.slotwin.mvp.base;

import android.support.annotation.LayoutRes;

/**
 * Created by luyunfeng on 16/12/2.
 */

public interface BaseUI {
    /**
     * 初始化数据
     */
    void initialized();

    /**
     * 初始化组件
     */
    void setupViews();

    /**
     * 获取layout ID
     */
    @LayoutRes int getLayoutId();
}
