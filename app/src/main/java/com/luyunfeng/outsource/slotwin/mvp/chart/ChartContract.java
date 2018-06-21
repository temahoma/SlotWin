package com.luyunfeng.outsource.slotwin.mvp.chart;

import com.luyunfeng.outsource.slotwin.bean.BaseBouns;
import com.luyunfeng.outsource.slotwin.mvp.base.BasePresenter;
import com.luyunfeng.outsource.slotwin.mvp.base.BaseView;

import java.util.List;

/**
 * Created by luyunfeng on 17/8/28.
 */

public interface ChartContract {

    interface IView extends BaseView {
        void display(List<? extends BaseBouns> bounsList);
        void empty();
    }

    abstract class IPresenter extends BasePresenter<IView> {

        public abstract void readHtml();

        public abstract void setUrl(String url);
    }
}
