package com.luyunfeng.outsource.slotwin.decorator;

/**
 * Created by luyunfeng on 17/8/29.
 * 装饰者
 */

public abstract class Decorator<H> {

    protected H host;

    public void decorate(H host) {
        this.host = host;
    }

    /**
     * 注意该方法必须被调用
     */
    public void onDestroy(){
        host = null;
    }


}
