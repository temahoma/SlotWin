package com.luyunfeng.outsource.slotwin.mvp.base;

/**
 * Created by luyunfeng on 17/8/31.
 */

public interface EmptyContract {

    interface IView extends BaseView {

    }

    class IPresenter extends BasePresenter<IView> {

    }
}
