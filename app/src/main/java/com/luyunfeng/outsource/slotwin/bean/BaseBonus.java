package com.luyunfeng.outsource.slotwin.bean;

/**
 * Created by luyunfeng on 2018/6/16.
 */

public abstract class BaseBonus {
    public int index;
    public int count;
    public int bonus;
    public int accumulateProfit;
    public @PriceType
    String type;

    public abstract int getProfit(int count, int bonus);

    public BaseBonus(){

    }

    public BaseBonus(int index, int count, int bonus, @PriceType String type) {
        this.index = index;
        this.count = count;
        this.bonus = bonus;
        this.type = type;
    }

    public void setAccumulateProfit(int accumulateProfit) {
        this.accumulateProfit = accumulateProfit;
    }
}

