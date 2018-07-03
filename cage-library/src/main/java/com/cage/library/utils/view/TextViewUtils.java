package com.cage.library.utils.view;

import android.widget.TextView;

/**
 * Created by luyunfeng on 2018/7/3.
 */

public class TextViewUtils {

    public static void text(TextView textView, String str){
        if (textView == null) {
            return;
        }
        if (str == null){
            textView.setText("");
        }else {
            textView.setText(str);
        }
    }
}
