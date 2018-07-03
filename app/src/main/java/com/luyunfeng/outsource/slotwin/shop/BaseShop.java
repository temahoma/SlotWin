package com.luyunfeng.outsource.slotwin.shop;

import com.luyunfeng.outsource.slotwin.bean.BaseBouns;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by luyunfeng on 2018/6/24.
 */

public abstract class BaseShop {

    public String id;

    public String name;

    public String url;

    public BaseShop(String id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public abstract List<? extends BaseBouns> parse(Document document);
}
