package com.luyunfeng.outsource.slotwin.shop;

/**
 * Created by luyunfeng on 2018/6/30.
 */

public class ShopBuilder {

    String date;

    String shop;

    public ShopBuilder setDate(String date) {
        this.date = date;
        return this;
    }

    public ShopBuilder setShop(String shop) {
        this.shop = shop;
        return this;
    }

    public BaseShop build(){
        BaseShop baseShop = null;
        switch (shop){
            case "papimo":
                baseShop = new PapimoShop("http://papimo.jp/h/00001616/hit/view/716/"+this.date, this.date);
                break;
            case "paradiso":
                baseShop = new ParadisoShop("http://www.paradiso.jp/machine_data/sp/10017/detail.php?h=10017&c=c749ef54b188453ad32b67f20af230a1&k=S20&d=1562&o=0", this.date);
                break;
        }
        return baseShop;
    }
}
