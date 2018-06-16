package com.luyunfeng.outsource.slotwin.network.param;

import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by luyunfeng on 17/5/16.
 * 网络参数存储
 */

public class Params {

    public HashMap<String, String> map;

    public Params() {
        map = new HashMap<>();
    }

    public Params put(String key, String value) {
        map.put(key, value);
        return this;
    }

    public HashMap<String, String> getMap() {
        return map;
    }

    public Params put(String key, int value) {
        return put(key, String.valueOf(value));
    }

    public Params put(String key, float value) {
        return put(key, String.valueOf(value));
    }

    public Params put(String key, double value) {
        return put(key, String.valueOf(value));
    }

    public Params put(String key, boolean value) {
        return put(key, String.valueOf(value));
    }

    public boolean has(String key) {
        return map.containsKey(key);
    }

    public String get(String key) {
        return map.get(key);
    }

    public void remove(String key) {
        map.remove(key);
    }

    public void clear() {
        map.clear();
    }

    public Params pack(Params params) {
        clear();
        this.map.putAll(params.getMap());
        return params;
    }

    public static RequestBody toOkHttp(String url, Params params) {

        FormBody.Builder builder = new FormBody.Builder();

        if (params == null) return builder.build();

        for (Map.Entry<String, String> entry : params.map.entrySet()) {
            if (entry.getValue() == null)
                builder.add(entry.getKey(), "");
            else
                builder.add(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (result.length() > 0)
                result.append('&');

            result.append(entry.getKey());
            result.append('=');
            result.append(entry.getValue());
        }
        return result.toString();
    }
}
