package com.luyunfeng.outsource.slotwin.shop;

import com.luyunfeng.outsource.slotwin.bean.BaseBouns;

import org.jsoup.nodes.Document;

import java.util.Calendar;
import java.util.List;

/**
 * Created by luyunfeng on 2018/6/24.
 */

public abstract class BaseShop {

    public String id;

    public String name;

    public String website;

    public String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public abstract List<? extends BaseBouns> parse(Document document);

    public abstract String getMachineUrl(String machineNumber, Calendar selectedDate);
}
