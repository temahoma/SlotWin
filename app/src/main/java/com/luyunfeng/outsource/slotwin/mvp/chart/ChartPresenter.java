package com.luyunfeng.outsource.slotwin.mvp.chart;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.luyunfeng.outsource.slotwin.bean.BaseBouns;
import com.luyunfeng.outsource.slotwin.bean.PapimoBouns;
import com.luyunfeng.outsource.slotwin.bean.PriceType;
import com.luyunfeng.outsource.slotwin.decorator.NetworkDecorator;
import com.luyunfeng.outsource.slotwin.network.Dispatcher;
import com.luyunfeng.outsource.slotwin.network.HttpUtil;
import com.luyunfeng.outsource.slotwin.network.Responder;
import com.luyunfeng.outsource.slotwin.network.param.Params;
import com.luyunfeng.outsource.slotwin.utils.DataParser;
import com.luyunfeng.outsource.slotwin.utils.FileUtils;
import com.luyunfeng.outsource.slotwin.utils.GsonUtils;
import com.luyunfeng.outsource.slotwin.utils.ListUtils;
import com.luyunfeng.outsource.slotwin.utils.LooperRunnable;
import com.luyunfeng.outsource.slotwin.utils.MessageCode;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ChartPresenter extends ChartContract.IPresenter
        implements Responder.OnResponseListener {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MessageCode.MESSAGE_HTML) {
                if (HttpUtil.ok(msg.arg1)) {
                    try {
                        List<BaseBouns> bonusList = new ArrayList<>();
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
                            if (elementsBig != null && elementsBig.size() > 0) {
                                Element big = elementsBig.get(0);
                                if (big != null) {
                                    bonusType = PriceType.BIG;
                                }
                            }

                            Element out = record.getElementsByClass("out").get(0);
                            Integer bonus = Integer.parseInt(out.html());

                            BaseBouns bouns = new PapimoBouns(bonusList.size() + 1, count, bonus, bonusType);
                            int accumulateProfit = bouns.getProfit(count, bonus);
                            if (bonusList.size() > 0) {
                                accumulateProfit += bonusList.get(bonusList.size() - 1).accumulateProfit;
                            }
                            bouns.setAccumulateProfit(accumulateProfit);

                            bonusList.add(bouns);
                        }

                        if (ListUtils.isEmpty(bonusList)){
                            mView.empty();
                        }else {
                            String json = GsonUtils.getIns().toJson(bonusList);
                            FileUtils.writeTextData("bonus/papimo", "20180621.json", json);
                            Log.d("test", json);
                            mView.display(bonusList);
                        }
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

        readFromFile();
        return;

//        params.clear();
//        params.put("url", url);
//
//        new LooperRunnable() {
//            @Override
//            public void runInLooper() {
//                Dispatcher.getHtml(handler, params);
//            }
//        };
    }

    private void readFromFile(){
        String json = null;
        try {
            json = FileUtils.readTextData(new FileInputStream(FileUtils.DATA_DIR + "bonus/papimo/" + "20180621.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("test", json);
        List<PapimoBouns> bonusList = new DataParser<PapimoBouns>()
                .setType(new TypeToken<List<PapimoBouns>>() {
                })
                .parseListPrue(json);
        if (ListUtils.isEmpty(bonusList)){
            mView.empty();
        }else {
            mView.display(bonusList);
        }
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
