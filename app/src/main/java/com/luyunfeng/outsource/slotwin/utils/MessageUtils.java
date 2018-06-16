package com.luyunfeng.outsource.slotwin.utils;

import android.os.Message;


/**
 * Created by luyunfeng on 17/12/5.
 */

public class MessageUtils {
    public static Message obtain(int what) {
        Message message = Message.obtain();
        message.what = what;
        return message;
    }
}
