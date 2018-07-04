package com.luyunfeng.outsource.slotwin.utils;

import com.luyunfeng.outsource.slotwin.MyApplication;

import java.io.File;

/**
 * Created by luyunfeng on 2018/7/2.
 */

public class Config {
    public final static String DATA_DIR
            = MyApplication.getContext().getFilesDir().getAbsolutePath() + File.separator
            + "slotwin" + File.separator;
}
