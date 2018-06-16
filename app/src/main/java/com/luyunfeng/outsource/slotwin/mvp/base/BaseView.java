package com.luyunfeng.outsource.slotwin.mvp.base;

import android.app.Activity;
import android.content.Context;
import android.view.View;

/**
 * Created by luyunfeng on 17/8/29.
 */

public interface BaseView {
    /**
     * 防止界面快速关闭时，还有数据消息队列发送消息
     */
    boolean isActive();

    Activity getActivity();

    Context getContext();

    View getDecorView();
}
