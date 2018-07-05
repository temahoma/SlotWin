package com.luyunfeng.outsource.slotwin.bean;

/**
 * Created by luyunfeng on 2018/6/16.
 */

public class PapimoBonus extends BaseBonus {

    public PapimoBonus(int index, int count, int bonus, String type) {
        super(index, count, bonus, type);
    }

    public int getProfit(int count, int bonus){
        int profit = bonus - count / 33 * 50;
        return profit;
    }

}

