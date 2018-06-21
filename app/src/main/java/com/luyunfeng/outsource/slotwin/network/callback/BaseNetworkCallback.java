package com.luyunfeng.outsource.slotwin.network.callback;

import android.os.Message;
import android.util.Log;

import com.luyunfeng.outsource.slotwin.network.Responder;
import com.luyunfeng.outsource.slotwin.utils.MessageCode;


/**
 * Created by luyunfeng on 17/5/16.
 */

public abstract class BaseNetworkCallback implements NetworkCallback {

    // 每次网络通讯传递的带有数据的成功或失败的消息
    protected Message responseMsg;

    // 回调
    protected Responder responder;

    public BaseNetworkCallback() {

    }

    public BaseNetworkCallback(Responder responder, Message message) {
        this.responder = responder;
        this.responseMsg = message;
    }

    @Override
    public String onResponse(byte[] responseBody) {
        if (responseBody == null) return null;
        String data = new String(responseBody);
        if (responseMsg != null) {
            Log.d("test", "【返回数据-" + responseMsg.what + "】" + data);
        } else {
            Log.d("test", "【返回数据】" + data);
        }
        return processData(data);
    }


    public String processData(String responseBody) {
        return responseBody;
    }

    @Override
    public void onFailure(String url, int statusCode, byte[] responseBody, Throwable error) {

        Log.d("test", "onFailure()\n"
                + "url = " + url + "\n"
                + "statusCode = " + statusCode + "\n"
                + (responseBody != null ? ("responseBody = " + new String(responseBody) + "\n") : "")
                + (error != null ? ("empty = " + error.toString() + "\n") : ""));

        if (responder != null && responseMsg != null) {
            responseMsg.arg1 = MessageCode.RESULT_HTTP_FAILED;
            responder.response(responseMsg);
        }
    }

    @Override
    public void onRetryRunOut() {
        if (responder != null && responseMsg != null) {
            responseMsg.arg1 = MessageCode.RESULT_HTTP_FAILED;
            responder.response(responseMsg);
        }
    }
}
