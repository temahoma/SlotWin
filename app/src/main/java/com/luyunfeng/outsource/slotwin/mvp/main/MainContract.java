package com.luyunfeng.outsource.slotwin.mvp.main;

import com.luyunfeng.outsource.slotwin.bean.BaseBouns;
import com.luyunfeng.outsource.slotwin.mvp.base.BasePresenter;
import com.luyunfeng.outsource.slotwin.mvp.base.BaseView;
import com.luyunfeng.outsource.slotwin.shop.BaseShop;

import java.util.List;

/**
 * Created by luyunfeng on 17/8/28.
 */

public interface MainContract {

    interface IView extends BaseView {

    }

    abstract class IPresenter extends BasePresenter<IView> {

    }
}
