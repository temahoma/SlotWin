package com.luyunfeng.outsource.slotwin.network.http;


import com.luyunfeng.outsource.slotwin.network.callback.NetworkCallback;
import com.luyunfeng.outsource.slotwin.network.param.Params;

/**
 * Created by luyunfeng on 17/5/17.
 */

public interface HttpRequest {

    /**
     * 发出请求，忽略返回
     */
    void post(String url, Params params);

    void post(String url, Params params, NetworkCallback callback);

    void post(String url, Params params, int retryCount, int sleepTime, NetworkCallback callback);

    /**
     * 返回后退出当前Looper
     */
    void postQuit(String url, Params params, NetworkCallback callback);

    /**
     * 上传文件
     */
    void upload(String url, Params params, byte[] data, NetworkCallback callback);

}
