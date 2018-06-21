package com.luyunfeng.outsource.slotwin.utils;

import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class DataParser<T> {

    private Type type;

    public DataParser<T> setType(TypeToken typeToken) {
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
            return GsonUtils.getIns().fromJson(jsonObject.getJSONArray("data").toString(), type);
        } catch (Exception e) {
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
            return GsonUtils.getIns().fromJson(json.toString(), type);
        } catch (Exception e) {
            e.printStackTrace();
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

            List<T> list = GsonUtils.getIns().fromJson(jsonObject.getJSONArray("data").toString(), type);

            if (ListUtils.isEmpty(list)) {
                return null;
            }
            return list.get(0);
        } catch (Exception e) {
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
            return GsonUtils.getIns().fromJson(json.toString(), clazz);
        } catch (Exception e) {
            return null;
        }
    }

}
