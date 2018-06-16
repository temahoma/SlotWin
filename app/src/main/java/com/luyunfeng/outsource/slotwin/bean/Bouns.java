package com.luyunfeng.outsource.slotwin.bean;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

/**
 * Created by luyunfeng on 2018/6/16.
 */

public class Bouns {
    public int index;
    public int count;
    public int bonus;
    public @PriceType
    String type;

    public Bouns(int index, int count, int bonus, @PriceType String type) {
        this.index = index;
        this.count = count;
        this.bonus = bonus;
        this.type = type;
    }
}

