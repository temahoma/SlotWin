package com.luyunfeng.outsource.slotwin.bean.shop;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by luyunfeng on 2018/6/24.
 */

@Entity
public class Shop {

    public String id;

    public Long prefectureId;

    public String name;

    public String website;

    public String url;

    @Transient
    public transient HtmlObject htmlObject;

    @Generated(hash = 1113593178)
    public Shop(String id, Long prefectureId, String name, String website,
            String url) {
        this.id = id;
        this.prefectureId = prefectureId;
        this.name = name;
        this.website = website;
        this.url = url;
    }

    @Generated(hash = 633476670)
    public Shop() {
    }

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

    public void setHtmlObject(HtmlObject htmlObject) {
        this.htmlObject = htmlObject;
    }

    public HtmlObject getHtmlObject() {
        return htmlObject;
    }

    public Long getPrefectureId() {
        return this.prefectureId;
    }

    public void setPrefectureId(Long prefectureId) {
        this.prefectureId = prefectureId;
    }

}
