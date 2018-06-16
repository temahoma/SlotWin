package com.luyunfeng.outsource.slotwin.mvp.base;

import android.support.annotation.IdRes;
import android.view.View;

/**
 * Created by luyunfeng on 17/8/28.
 */

public class BasePresenter<V extends BaseView> {

    protected V mView;

    public void onAttach(V mView) {
        this.mView = mView;
    }

    /**
     * 注意该方法必须被调用
     */
    public void onDetach() {
        mView = null;
    }

    public <T extends View> T findViewById(@IdRes int id) {
        return mView.getActivity().findViewById(id);
    }
}
