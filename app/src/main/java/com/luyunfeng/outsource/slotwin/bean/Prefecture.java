package com.luyunfeng.outsource.slotwin.bean;

import com.luyunfeng.outsource.slotwin.bean.shop.Shop;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luyunfeng on 2018/7/3.
 */
@Entity
public class Prefecture {

    @Id
    Long id;

    String name;

    @Transient
    List<Shop> shops = new ArrayList<>();

    @Generated(hash = 515860450)
    public Prefecture(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 1478374372)
    public Prefecture() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public Long genId() {
        return (long) name.hashCode();
    }
}
