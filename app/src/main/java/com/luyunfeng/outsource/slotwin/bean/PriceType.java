package com.luyunfeng.outsource.slotwin.bean;

import android.support.annotation.StringDef;

@StringDef({PriceType.REG, PriceType.BIG})
public @interface PriceType {
    String REG = "REG";
    String BIG = "BIG";
}
