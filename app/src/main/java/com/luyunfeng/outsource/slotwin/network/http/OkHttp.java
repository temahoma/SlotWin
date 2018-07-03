package com.luyunfeng.outsource.slotwin.network.http;


import com.cage.library.utils.concurrent.ThreadHelper;
import com.luyunfeng.outsource.slotwin.network.callback.NetworkCallback;
import com.luyunfeng.outsource.slotwin.network.param.Params;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by luyunfeng on 17/3/2.
 */

public class OkHttp extends HttpRequestImpl {

    private OkHttpClient okHttpClient;

    public OkHttp() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        okHttpClient = builder
                .connectTimeout(HttpRequestImpl.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(HttpRequestImpl.RESPONSE_TIMEOUT, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(false)
                .build();
    }

    @Override
    public void post(String url, Params params) {
        post(url, Params.toOkHttp(url, params));
    }

    @Override
    public void post(String url, Params params, NetworkCallback callback) {
        post(url, params, HttpRequestImpl.RETRY_COUNT, HttpRequestImpl.SLEEP_TIME, callback);
    }

    @Override
    public void post(final String url, Params params, int retryCount, int sleepTime, final NetworkCallback callback) {
        final int retry[] = new int[]{retryCount, sleepTime};

        Request request = new Request.Builder()
                .url(url)
                .post(Params.toOkHttp(url, params))
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (e.getCause() instanceof SocketTimeoutException) {
                    retry(call, this, callback, retry, false);
                } else {
                    callback.onFailure(url, 0, null, e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                byte[] bytes = response.body().bytes();
                if (response.isSuccessful()) {
                    String object = callback.onResponse(bytes);
                    if (object == null) {
                        retry(call, this, callback, retry, false);
                    } else {
                        callback.onSuccess(object);
                    }
                } else {
                    callback.onFailure(url, response.code(), bytes, null);
                }
            }
        });
    }

    @Override
    public void postQuit(String url, Params params, NetworkCallback callback) {
        postQuit(url, Params.toOkHttp(url, params), HttpRequestImpl.RETRY_COUNT, HttpRequestImpl.SLEEP_TIME, callback);
    }

    // TODO check
    @Override
    public void upload(String url, Params params, byte[] data, NetworkCallback callback) {

        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), data);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("card_image", "credit_card_img.jpg", fileBody)
                .addPart(Params.toOkHttp(url, params))
                .build();

        postQuit(url, requestBody, HttpRequestImpl.RETRY_COUNT, HttpRequestImpl.SLEEP_TIME, callback);
    }

    private void post(String url, RequestBody formBody) {

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    private void postQuit(final String url, RequestBody formBody, int retryCount, int sleepTime, final NetworkCallback callback) {

        final int retry[] = new int[]{retryCount, sleepTime};

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (e.getCause() instanceof SocketTimeoutException) {
                    retry(call, this, callback, retry, true);
                } else {
                    callback.onFailure(url, 0, null, e);
                    ThreadHelper.quitThread();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                byte[] bytes = response.body().bytes();
                if (response.isSuccessful()) {
                    String object = callback.onResponse(bytes);
                    if (object == null) {
                        retry(call, this, callback, retry, true);
                    } else {
                        callback.onSuccess(object);
                        ThreadHelper.quitThread();
                    }
                } else {
                    callback.onFailure(url, response.code(), bytes, null);
                    ThreadHelper.quitThread();
                }
            }
        });
    }

    private void retry(Call call, Callback callback, NetworkCallback networkCallback, int retry[], boolean quit) {
        if (retry[0] > 0) {
            retry[0]--;
            try {
                Thread.sleep(retry[1]);
            } catch (InterruptedException e) {

            }
            okHttpClient.newCall(call.request()).enqueue(callback);
        } else {
            networkCallback.onRetryRunOut();
            if (quit)
                ThreadHelper.quitThread();
        }
    }
}
