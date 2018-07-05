package com.luyunfeng.outsource.slotwin.bean.shop;

/**
 * Created by luyunfeng on 2018/6/30.
 */

public class ShopBuilder {

    String website;
    String url;
    String name;
    Long prefectureId;
    boolean needHtmlObject;

    public ShopBuilder setWebsite(String website) {
        this.website = website;
        return this;
    }

    public ShopBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public ShopBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ShopBuilder setPrefectureId(Long prefectureId) {
        this.prefectureId = prefectureId;
        return this;
    }

    public ShopBuilder setNeedHtmlObject(boolean needHtmlObject) {
        this.needHtmlObject = needHtmlObject;
        return this;
    }

    public Shop build() {
        HtmlObject htmlObject = null;
        String shopId = null;
        Shop shop;
        switch (website) {
            case "PAPIMO-NET":
                shop = new Shop();
                String[] urlParts = url.split("/");
                shopId = urlParts[urlParts.length - 1];
                if (needHtmlObject){
                    htmlObject = new PapimoHtml();
                }
                break;
            case "ｵﾘｼﾞﾅﾙｻｲﾄ":
                shop = new Shop();
                urlParts = url.split("/");
                shopId = urlParts[urlParts.length - 1];
                if (needHtmlObject){
                    htmlObject = new ParadisoHtml();
                }
                break;
            default:
                shop = null;
                break;
        }
        if (shop != null) {
            shop.setId(shopId);
            shop.setName(name);
            shop.setUrl(url);
            shop.setWebsite(website);
            shop.setHtmlObject(htmlObject);
            shop.setPrefectureId(prefectureId);
        }
        return shop;
    }
}
