package com.luyunfeng.outsource.slotwin.bean;

import com.luyunfeng.outsource.slotwin.shop.BaseShop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luyunfeng on 2018/7/3.
 */

public class Prefecture {
    int id;
    String name;
    List<BaseShop> shops = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<BaseShop> getShops() {
        return shops;
    }

    public int getId() {
        return id;
    }


}
