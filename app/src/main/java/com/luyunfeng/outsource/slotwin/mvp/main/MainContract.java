package com.luyunfeng.outsource.slotwin.mvp.main;

import com.luyunfeng.outsource.slotwin.bean.Prefecture;
import com.luyunfeng.outsource.slotwin.mvp.base.BasePresenter;
import com.luyunfeng.outsource.slotwin.mvp.base.BaseView;

import java.util.List;

/**
 * Created by luyunfeng on 17/8/28.
 */

public interface MainContract {

    interface IView extends BaseView {
        public void enableSelections(List<Prefecture> prefectures);
    }

    abstract class IPresenter extends BasePresenter<IView> {
        public abstract void prepareSelections();
    }
}
