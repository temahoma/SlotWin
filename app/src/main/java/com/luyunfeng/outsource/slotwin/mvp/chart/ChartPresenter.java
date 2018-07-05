package com.luyunfeng.outsource.slotwin.mvp.chart;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.cage.library.utils.concurrent.LooperRunnable;
import com.cage.library.utils.data.JsonParser;
import com.cage.library.utils.data.ListUtils;
import com.cage.library.utils.io.FileUtils;
import com.google.gson.reflect.TypeToken;
import com.luyunfeng.outsource.slotwin.bean.BaseBonus;
import com.luyunfeng.outsource.slotwin.bean.PapimoBonus;
import com.luyunfeng.outsource.slotwin.decorator.NetworkDecorator;
import com.luyunfeng.outsource.slotwin.network.Dispatcher;
import com.luyunfeng.outsource.slotwin.network.HttpUtil;
import com.luyunfeng.outsource.slotwin.network.Responder;
import com.luyunfeng.outsource.slotwin.network.param.Params;
import com.luyunfeng.outsource.slotwin.bean.shop.Shop;
import com.luyunfeng.outsource.slotwin.utils.Config;
import com.luyunfeng.outsource.slotwin.utils.MessageCode;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;


public class ChartPresenter extends ChartContract.IPresenter
        implements Responder.OnResponseListener {

    private Shop shop;

    private NetworkDecorator decorator;

    private Params params = new Params();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MessageCode.MESSAGE_HTML) {
                if (HttpUtil.ok(msg.arg1)) {
                    try {
                        List<? extends BaseBonus> bonusList =  shop.getHtmlObject().parse((Document) msg.obj);

                        if (ListUtils.isEmpty(bonusList)){
                            mView.empty();
                        }else {
                            String json = JsonParser.getGson().toJson(bonusList);
                            Log.d("test", json);
                            FileUtils.writeTextData(Config.DATA_DIR + getBonusFilePath(), getBonusFileName(), json);
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

    private void readFromFile(){
        String json = "";
        try {
            json = FileUtils.readTextData(Config.DATA_DIR + getBonusFilePath() + getBonusFileName());
            Log.d("test", json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<PapimoBonus> bonusList = new JsonParser<PapimoBonus>()
                .setType(new TypeToken<List<PapimoBonus>>() {
                })
                .parseListPrue(json);
        if (ListUtils.isEmpty(bonusList)){
            mView.empty();
        }else {
            mView.display(bonusList);
        }
    }

    private String getBonusFilePath(){
        return "bonus/x/";
    }

    private String getBonusFileName(){
        return  "xxx.json";
    }

    @Override
    public void setShop(Shop shop) {
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
