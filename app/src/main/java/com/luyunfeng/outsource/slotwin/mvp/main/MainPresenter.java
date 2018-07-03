package com.luyunfeng.outsource.slotwin.mvp.main;

import android.os.Handler;
import android.os.Message;

import com.cage.library.infrastructure.text.StringUtils;
import com.cage.library.utils.concurrent.LooperRunnable;
import com.luyunfeng.outsource.slotwin.bean.Prefecture;
import com.luyunfeng.outsource.slotwin.decorator.NetworkDecorator;
import com.luyunfeng.outsource.slotwin.network.Dispatcher;
import com.luyunfeng.outsource.slotwin.network.HttpUtil;
import com.luyunfeng.outsource.slotwin.network.Responder;
import com.luyunfeng.outsource.slotwin.network.param.Params;
import com.luyunfeng.outsource.slotwin.shop.BaseShop;
import com.luyunfeng.outsource.slotwin.shop.ShopBuilder;
import com.luyunfeng.outsource.slotwin.utils.MessageCode;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


public class MainPresenter extends MainContract.IPresenter
        implements Responder.OnResponseListener {

    private NetworkDecorator decorator;

    private Params params = new Params();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MessageCode.MESSAGE_HTML) {
                if (HttpUtil.ok(msg.arg1)) {
                    try {

                        List<Prefecture> prefectures = new ArrayList<>();

                        Document document = (Document) msg.obj;
                        Element body = document.body();
                        Elements tbodies = body.getElementsByTag("tbody");
                        for (Element tbody : tbodies) {
                            Elements ths = tbody.getElementsByTag("th");
                            String region = ths.get(1).getElementsByTag("font").get(0).html();
                            if (StringUtils.isValid(region) && !region.equals("------")) {
                                Elements trs = tbody.getElementsByTag("tr");
                                for (Element tr : trs) {
                                    Elements tds = tr.getElementsByTag("td");
                                    if (tds.size() > 3) {
                                        String name = tds.get(1).html();
                                        Element websiteElement = tds.get(3);
                                        String url = websiteElement.getElementsByTag("a").get(0).attr("href");
                                        String website = websiteElement.getElementsByTag("font").get(0).html();
                                        String shopName = tds.get(2).getElementsByTag("font").get(0).html().trim();

                                        Prefecture prefecture = getPrefecture(prefectures, name);
                                        if (prefecture == null) {
                                            prefecture = new Prefecture();
                                            prefecture.setName(name);
                                            prefectures.add(prefecture);
                                        }
                                        List<BaseShop> shops = prefecture.getShops();
                                        BaseShop shop = new ShopBuilder()
                                                .setWebsite(website)
                                                .setName(shopName)
                                                .setUrl(url)
                                                .build();
                                        shops.add(shop);
                                    }
                                }
                            }
                        }

                        mView.enableSelections(prefectures);

                    } catch (Throwable throwable) {
                        mView.enableSelections(null);
                    }
                } else {
                    mView.enableSelections(null);
                }
            } else {
                super.handleMessage(msg);
            }
        }
    };

    private Prefecture getPrefecture(List<Prefecture> prefectures, String name) {
        for (Prefecture prefecture : prefectures) {
            if (prefecture.getName().equals(name)) {
                return prefecture;
            }
        }
        return null;
    }

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

    @Override
    public void prepareSelections() {
        readFromNet();
//        readFromFile();
    }

    private void readFromNet() {
        params.clear();
        params.put("url", "http://pleforce.co.jp/holldata/");
        new LooperRunnable() {
            @Override
            public void runInLooper() {
                Dispatcher.getHtml(handler, params);
            }
        };
    }

}
