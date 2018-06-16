package com.luyunfeng.outsource.slotwin.network;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;

import com.luyunfeng.outsource.slotwin.network.callback.BaseNetworkCallback;
import com.luyunfeng.outsource.slotwin.network.http.HttpRequestImpl;
import com.luyunfeng.outsource.slotwin.network.param.Params;
import com.luyunfeng.outsource.slotwin.utils.MessageCode;
import com.luyunfeng.outsource.slotwin.utils.MessageUtils;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by LuYunfeng on 2015/10/12.
 * 网络交互分发器
 */
public class Dispatcher {

    // 检查网络是否可用
    private static boolean isNetworkOK() {
        return NetWorkUtil.isNetworkAvailable();
    }

    private static boolean isNetworkOK(Handler handler) {
        if (!isNetworkOK()) {
            if (handler != null) {
                handler.sendEmptyMessage(MessageCode.MESSAGE_NO_NETWORK);
            }
            return false;
        }
        return true;
    }

    // 检查更新
    public static void getHtml(final Handler handler, Params params) {
        if (!isNetworkOK(handler)) return;

        final String url = params.get("url");

        final Message message = MessageUtils.obtain(MessageCode.MESSAGE_HTML);

        try {
            Document doc = Jsoup.connect(url).get();
            message.arg1 = MessageCode.RESULT_HTTP_SUCCESS;
            message.obj = doc;
            handler.sendMessage(message);
        } catch (IOException e) {
            message.arg1 = MessageCode.RESULT_HTTP_FAILED;
            e.printStackTrace();
        }
    }
}

