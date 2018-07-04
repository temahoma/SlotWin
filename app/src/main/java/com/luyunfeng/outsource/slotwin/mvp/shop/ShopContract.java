package com.luyunfeng.outsource.slotwin.mvp.shop;

import com.luyunfeng.outsource.slotwin.bean.BaseBouns;
import com.luyunfeng.outsource.slotwin.mvp.base.BasePresenter;
import com.luyunfeng.outsource.slotwin.mvp.base.BaseView;
import com.luyunfeng.outsource.slotwin.shop.BaseShop;

import java.util.Calendar;
import java.util.List;

/**
 * Created by luyunfeng on 17/8/28.
 */

public interface ShopContract {

    interface IView extends BaseView {
        void display(List<? extends BaseBouns> bounsList);
        void empty();
    }

    abstract class IPresenter extends BasePresenter<IView> {

        public abstract void setShop(BaseShop shop);

        public abstract void setTarget(String machineNumber, Calendar selectedDate);

        public abstract void readData();
    }
}
