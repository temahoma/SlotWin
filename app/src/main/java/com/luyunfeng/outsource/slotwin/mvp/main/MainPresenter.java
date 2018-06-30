package com.luyunfeng.outsource.slotwin.mvp.main;

import android.os.Message;

import com.luyunfeng.outsource.slotwin.decorator.NetworkDecorator;
import com.luyunfeng.outsource.slotwin.network.Responder;


public class MainPresenter extends MainContract.IPresenter
        implements Responder.OnResponseListener {

    private NetworkDecorator decorator;

    @Override
    public void onAttach(MainContract.IView mView) {
        super.onAttach(mView);
        decorator = new NetworkDecorator();
        decorator.decorate(this);
    }

    @Override
    public void onResponded(Message msg) {

    }

    @Override
    public boolean isAlive() {
        return mView.isActive();
    }

    @Override
    public void onDetach() {
        decorator.onDestroy();
        super.onDetach();
    }

}
