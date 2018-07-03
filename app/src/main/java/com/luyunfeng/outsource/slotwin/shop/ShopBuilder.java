package com.luyunfeng.outsource.slotwin.shop;

/**
 * Created by luyunfeng on 2018/6/30.
 */

public class ShopBuilder {

    String website;
    String url;
    String name;

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

    public BaseShop build() {
        BaseShop baseShop = null;
        switch (website) {
            case "PAPIMO-NET":
                String[] urlParts = url.split("/");
                String shopId = urlParts[urlParts.length - 1];
                baseShop = new PapimoShop(shopId, name, url);
                break;
            case "ｵﾘｼﾞﾅﾙｻｲﾄ":
                urlParts = url.split("/");
                shopId = urlParts[urlParts.length - 1];
                baseShop = new ParadisoShop(shopId, name, url);
                break;
        }
        return baseShop;
    }
}
