package com.luyunfeng.outsource.slotwin.network.callback;

import android.support.annotation.NonNull;

/**
 * Created by luyunfeng on 17/5/16.
 * 网络请求统一回调
 */

public interface NetworkCallback {

    /**
     * 成功连接，并获取到响应消息
     * @param responseBody
     * @return
     */
    String onResponse(byte[] responseBody);

    /**
     * 组织数据并返回
     * @param object
     */
    void onSuccess(@NonNull String object);

    /**
     * 网络通讯失败
     * @param url
     * @param statusCode
     * @param responseBody
     * @param error
     */
    void onFailure(String url, int statusCode, byte[] responseBody, Throwable error);

    /**
     * 手动重试次数用尽
     */
    void onRetryRunOut();
}
