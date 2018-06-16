package com.luyunfeng.outsource.slotwin.mvp.chart;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.luyunfeng.outsource.slotwin.bean.Bouns;
import com.luyunfeng.outsource.slotwin.bean.PriceType;
import com.luyunfeng.outsource.slotwin.decorator.NetworkDecorator;
import com.luyunfeng.outsource.slotwin.network.Dispatcher;
import com.luyunfeng.outsource.slotwin.network.HttpUtil;
import com.luyunfeng.outsource.slotwin.network.Responder;
import com.luyunfeng.outsource.slotwin.network.param.Params;
import com.luyunfeng.outsource.slotwin.utils.LooperRunnable;
import com.luyunfeng.outsource.slotwin.utils.MessageCode;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


public class ChartPresenter extends ChartContract.IPresenter
        implements Responder.OnResponseListener {

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MessageCode.MESSAGE_HTML) {
                if (HttpUtil.ok(msg.arg1)) {
                    List<Bouns> bonusList = new ArrayList<>();
                    final Document document = (Document) msg.obj;
                    Element body = document.body();
                    Element history = body.getElementById("tab-history-index");
                    Elements elements = history.getElementsByTag("tbody");
                    Element tbody = elements.get(0);
                    Elements records = tbody.getElementsByTag("tr");

                    for (int i = records.size() - 1; i >= 0; i--) {
                        Element record = records.get(i);
//                    Element cnt = record.getElementsByClass("cnt").get(0);
//                    Integer count = Integer.parseInt(cnt.html());
//                    Element time = record.getElementsByClass("time").get(0);
//                    String timeString = time.html();
                        Element start = record.getElementsByClass("start").get(0);
                        Integer count = Integer.parseInt(start.html());

                        String bonusType = PriceType.REG;
                        Elements elementsBig = record.getElementsByClass("big");
                        if (elementsBig != null && elementsBig.size() > 0){
                            Element big = elementsBig.get(0);
                            if (big != null){
                                bonusType = PriceType.BIG;
                            }
                        }

                        Element out = record.getElementsByClass("out").get(0);
                        Integer bonus = Integer.parseInt(out.html());

                        Bouns bouns = new Bouns(bonusList.size()+1, count, bonus, bonusType);
                        bonusList.add(bouns);
                    }

//                List<Bouns> bonusList = new ArrayList<Bouns>() {{
//                    add(new Bouns(1, 251, 325, PriceType.BIG));
//                    add(new Bouns(2, 120, 325, PriceType.BIG));
//                    add(new Bouns(3, 263, 324, PriceType.BIG));
//                    add(new Bouns(4, 24, 325, PriceType.BIG));
//                    add(new Bouns(5, 4, 104, PriceType.REG));
//                    add(new Bouns(6, 181, 104, PriceType.REG));
//                    add(new Bouns(7, 477, 324, PriceType.BIG));
//                    add(new Bouns(8, 17, 324, PriceType.BIG));
//                }};

                    mView.display(bonusList);
                } else {

                }
            }else {
                super.handleMessage(msg);
            }
        }
    };

    private NetworkDecorator decorator;

    private Params params = new Params();

    private String url;

    @Override
    public void onAttach(ChartContract.IView mView) {
        super.onAttach(mView);
        decorator = new NetworkDecorator();
        decorator.decorate(this);
    }

    @Override
    public void onResponded(Message msg) {

    }

    @Override
    public void readHtml() {
        params.clear();
        params.put("url", url);
        new LooperRunnable() {
            @Override
            public void runInLooper() {
                Dispatcher.getHtml(handler, params);
            }
        };
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
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