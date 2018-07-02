package com.cage.library.utils.data;

import android.support.annotation.Nullable;

import java.util.Map;

/**
 * Created by luyunfeng on 16/7/28.
 */
public class MapUtils {

    public static boolean isValid(@Nullable Map map) {
        return !isEmpty(map);
    }

    public static boolean isEmpty(@Nullable Map map) {
        return map == null || map.isEmpty();
    }

}
