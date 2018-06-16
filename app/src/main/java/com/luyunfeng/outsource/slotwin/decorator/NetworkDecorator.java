package com.luyunfeng.outsource.slotwin.decorator;

import com.luyunfeng.outsource.slotwin.network.Responder;

/**
 * Created by luyunfeng on 17/8/30.
 * 使用Responder接收网络响应数据
 */

public class NetworkDecorator extends Decorator<Responder.OnResponseListener> {

    private Responder responder;

    @Override
    public void decorate(Responder.OnResponseListener host) {

        if (host == null) {
            throw new IllegalArgumentException("OnResponseListener is mandatory.");
        }

        super.decorate(host);

        responder = new Responder(host);
    }

    public Responder getResponer() {
        return responder;
    }

    @Override
    public void onDestroy() {
        responder.onDestroy();
        responder = null;
        super.onDestroy();
    }
}
