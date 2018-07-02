package com.cage.library.mvp;

/**
 * Created by luyunfeng on 17/8/31.
 */

public interface CageContract {

    interface IView extends CageView {

    }

    class IPresenter extends CagePresenter<IView> {

    }
}
