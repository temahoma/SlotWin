package com.luyunfeng.outsource.slotwin.network;

import android.os.Message;


import com.luyunfeng.outsource.slotwin.utils.MessageUtils;

import java.lang.ref.WeakReference;

/**
 * 网络请求回调
 *
 * @author luyunfeng
 * @date 17/10/30
 */

public class Responder {

    private WeakReference<OnResponseListener> mWeakReference;

    public Responder(OnResponseListener listener) {
        mWeakReference = new WeakReference<>(listener);
    }

    public void response(Message message) {
        if (mWeakReference != null && message != null) {
            OnResponseListener listener = mWeakReference.get();
            if (listener != null && listener.isAlive()) {
                listener.onResponded(message);
            }
        }
    }

    public void response(int what) {
        response(MessageUtils.obtain(what));
    }

    public void onDestroy() {
        mWeakReference.clear();
    }

    public interface OnResponseListener {
        /**
         * 网络数据返回回调
         */
        void onResponded(Message message);

        /**
         * 是否可以发送消息
         */
        boolean isAlive();
    }
}
