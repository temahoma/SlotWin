package com.luyunfeng.outsource.slotwin.network.http;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;


import org.json.JSONObject;

/**
 * Created by luyunfeng on 17/5/18.
 */

public abstract class HttpRequestImpl implements HttpRequest {

    public static final int ORDER_RETRY_COUNT = 4;
    public static final int RETRY_COUNT = 2;
    public static final int SLEEP_TIME = 3000;
    public static final int STATUS_SLEEP_TIME = 5000;
    public static final int CONNECT_TIMEOUT = 60 * 1000;
    public static final int RESPONSE_TIMEOUT = 120 * 1000;

    /**
     * 用于重试休眠
     */
    protected Handler sleepHandler = new Handler();

    private static HttpRequest http;

    public static void init() {
        http = new OkHttp();
    }

    public static HttpRequest getIns() {
        if (http == null) {
            init();
        }
        return http;
    }
}
