package com.luyunfeng.outsource.slotwin.mvp.shop;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.cage.library.utils.concurrent.LooperRunnable;
import com.cage.library.utils.data.JsonParser;
import com.cage.library.utils.data.ListUtils;
import com.cage.library.utils.io.FileUtils;
import com.google.gson.reflect.TypeToken;
import com.luyunfeng.outsource.slotwin.bean.PapimoBouns;
import com.luyunfeng.outsource.slotwin.decorator.NetworkDecorator;
import com.luyunfeng.outsource.slotwin.network.Dispatcher;
import com.luyunfeng.outsource.slotwin.network.HttpUtil;
import com.luyunfeng.outsource.slotwin.network.Responder;
import com.luyunfeng.outsource.slotwin.network.param.Params;
import com.luyunfeng.outsource.slotwin.shop.BaseShop;
import com.luyunfeng.outsource.slotwin.utils.Config;
import com.luyunfeng.outsource.slotwin.utils.MessageCode;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;


public class ShopPresenter extends ShopContract.IPresenter
        implements Responder.OnResponseListener {

    private BaseShop shop;

    private NetworkDecorator decorator;

    private Params params = new Params();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MessageCode.MESSAGE_HTML) {
                if (HttpUtil.ok(msg.arg1)) {
                    try {

                        Document document = (Document) msg.obj;
                        Element body = document.body();

                        mView.empty();

                    } catch (Throwable throwable) {
                        mView.empty();
                    }
                } else {
                    mView.empty();
                }
            } else {
                super.handleMessage(msg);
            }
        }
    };

    @Override
    public void onAttach(ShopContract.IView mView) {
        super.onAttach(mView);
        decorator = new NetworkDecorator();
        decorator.decorate(this);
    }

    @Override
    public void onResponded(Message msg) {

    }

    @Override
    public void readHtml() {
        readFromNet();
//        readFromFile();
    }

    private void readFromNet(){
        params.clear();
        params.put("url", shop.url);

        new LooperRunnable() {
            @Override
            public void runInLooper() {
                Dispatcher.getHtml(handler, params);
            }
        };
    }

    @Override
    public void setShop(BaseShop shop) {
        this.shop = shop;
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
