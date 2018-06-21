package com.luyunfeng.outsource.slotwin;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.luyunfeng.outsource.slotwin.bean.BaseBouns;

/**
 * Created by luyunfeng on 2018/6/16.
 */

public class CountValueFormatter implements IValueFormatter {

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        BaseBouns bouns = (BaseBouns) entry.getData();
        return String.valueOf(bouns.count);
    }
}
