package com.cage.library.utils.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.cage.library.infrastructure.log.Log;

import org.json.JSONObject;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by yufeng on 16/9/20.
 */

public class JsonParser<T> {

    private volatile static Gson mGson = null;

    public static Gson getGson() {
        if (mGson == null) {
            synchronized (JsonParser.class) {
                if (mGson == null) {
                    mGson = new GsonBuilder()
                            .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                            .create();
                }
            }
        }
        return mGson;
    }

    private Type type;

    public JsonParser<T> setType(TypeToken typeToken) {
        this.type = typeToken.getType();
        return this;
    }

    /**
     * 从Json数组到List
     *
     * @param json
     * @return
     */
    public List<T> parseList(Object json) {

        if (json == null || type == null) {
            return null;
        }

        try {
            JSONObject jsonObject = new JSONObject(json.toString());
            return getGson().fromJson(jsonObject.getJSONArray("data").toString(), type);
        } catch (Exception e) {
            Log.printStackTrace(e);
            return null;
        }
    }

    /**
     * 从Json数组到List
     *
     * @param json
     * @return
     */
    public List<T> parseListPrue(Object json) {

        if (json == null || type == null) {
            return null;
        }

        try {
            return getGson().fromJson(json.toString(), type);
        } catch (Exception e) {
            Log.printStackTrace(e);
            return null;
        }
    }

    /**
     * 从Json数组到Object
     *
     * @param json
     * @return
     */
    public T parse(Object json) {

        if (json == null || type == null) {
            return null;
        }

        try {
            JSONObject jsonObject = new JSONObject(json.toString());

            List<T> list = getGson().fromJson(jsonObject.getJSONArray("data").toString(), type);

            if (ListUtils.isEmpty(list)) {
                return null;
            }
            return list.get(0);
        } catch (Exception e) {
            Log.printStackTrace(e);
            return null;
        }
    }

    /**
     * 从JsonObject到Object
     *
     * @param json
     * @return
     */
    public static <T> T from(Object json, Class<T> clazz) {
        if (json == null) {
            return null;
        }
        try {
            return getGson().fromJson(json.toString(), clazz);
        } catch (Exception e) {
            Log.printStackTrace(e);
            return null;
        }
    }

}
