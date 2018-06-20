package com.luyunfeng.outsource.slotwin.utils;

import java.io.Closeable;

/**
 * Created by luyunfeng on 17/8/24.
 * 处理读写操作工具类
 */

public class IOUtils {
    public static void close(Closeable closeable){
        if (closeable != null){
            try {
                closeable.close();
            } catch (Exception e) {

            }
        }
    }
}
