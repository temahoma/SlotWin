package com.luyunfeng.outsource.slotwin.shop;

import com.luyunfeng.outsource.slotwin.bean.BaseBouns;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by luyunfeng on 2018/6/24.
 */

public abstract class BaseShop {

    public String url;

    public String date;

    public BaseShop(String url, String date){
        this.url = url;
        this.date = date;
    }

    public abstract String getName();

    public abstract List<? extends BaseBouns> parse(Document document);
}
