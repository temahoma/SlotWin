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
        String shopId = null;
        switch (website) {
            case "PAPIMO-NET":
                String[] urlParts = url.split("/");
                shopId = urlParts[urlParts.length - 1];
                baseShop = new PapimoShop();
                break;
            case "ｵﾘｼﾞﾅﾙｻｲﾄ":
                urlParts = url.split("/");
                shopId = urlParts[urlParts.length - 1];
                baseShop = new ParadisoShop();
                break;
        }
        if (baseShop != null){
            baseShop.setId(shopId);
            baseShop.setName(name);
            baseShop.setUrl(url);
            baseShop.setWebsite(website);
        }
        return baseShop;
    }
}
