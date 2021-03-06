package com.luyunfeng.outsource.slotwin;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.luyunfeng.outsource.slotwin.bean.BaseBonus;

/**
 * Created by luyunfeng on 2018/6/16.
 */

public class ProfitValueFormatter implements IValueFormatter {

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        BaseBonus bouns = (BaseBonus) entry.getData();
        return String.valueOf(bouns.accumulateProfit);
    }
}
