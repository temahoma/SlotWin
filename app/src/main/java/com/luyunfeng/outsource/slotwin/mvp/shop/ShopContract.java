package com.luyunfeng.outsource.slotwin.mvp.shop;

import com.luyunfeng.outsource.slotwin.bean.BaseBonus;
import com.luyunfeng.outsource.slotwin.mvp.base.BasePresenter;
import com.luyunfeng.outsource.slotwin.mvp.base.BaseView;
import com.luyunfeng.outsource.slotwin.bean.shop.Shop;

import java.util.Calendar;
import java.util.List;

/**
 * Created by luyunfeng on 17/8/28.
 */

public interface ShopContract {

    interface IView extends BaseView {
        void display(List<BaseBonus> bounsList);
        void empty();
    }

    abstract class IPresenter extends BasePresenter<IView> {

        public abstract void setShop(Shop shop);

        public abstract void setTarget(String machineNumber, Calendar selectedDate);

        public abstract void readData();
    }
}
