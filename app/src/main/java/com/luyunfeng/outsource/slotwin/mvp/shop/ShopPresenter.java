package com.luyunfeng.outsource.slotwin.mvp.shop;

import android.os.Handler;
import android.os.Message;

import com.cage.library.infrastructure.log.Log;
import com.cage.library.utils.concurrent.LooperRunnable;
import com.cage.library.utils.data.JsonParser;
import com.cage.library.utils.data.ListUtils;
import com.cage.library.utils.io.FileUtils;
import com.luyunfeng.outsource.slotwin.bean.BaseBouns;
import com.luyunfeng.outsource.slotwin.decorator.NetworkDecorator;
import com.luyunfeng.outsource.slotwin.network.Dispatcher;
import com.luyunfeng.outsource.slotwin.network.HttpUtil;
import com.luyunfeng.outsource.slotwin.network.Responder;
import com.luyunfeng.outsource.slotwin.network.param.Params;
import com.luyunfeng.outsource.slotwin.bean.shop.Shop;
import com.luyunfeng.outsource.slotwin.utils.Config;
import com.luyunfeng.outsource.slotwin.utils.MessageCode;

import org.jsoup.nodes.Document;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class ShopPresenter extends ShopContract.IPresenter
        implements Responder.OnResponseListener {

    private Shop shop;

    private String machineNumber;
    private Calendar selectedDate;

    private NetworkDecorator decorator;

    private Params params = new Params();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MessageCode.MESSAGE_HTML) {
                if (HttpUtil.ok(msg.arg1)) {
                    try {
                        List<? extends BaseBouns> bonusList = shop.getHtmlObject().parse((Document) msg.obj);

                        if (ListUtils.isEmpty(bonusList)) {
                            mView.empty();
                        } else {
                            String json = JsonParser.getGson().toJson(bonusList);
                            Log.d(json);
                            FileUtils.writeTextData(getBonusFilePath(), getBonusFileName(), json);
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
    public void onAttach(ShopContract.IView mView) {
        super.onAttach(mView);
        decorator = new NetworkDecorator();
        decorator.decorate(this);
    }

    @Override
    public void onResponded(Message msg) {

    }

    @Override
    public void readData() {
        readFromNet();
//        readFromFile();
    }

    private void readFromNet() {
        params.clear();
        params.put("url", shop.getHtmlObject().getMachineUrl(shop.getUrl(), machineNumber, selectedDate));
        new LooperRunnable() {
            @Override
            public void runInLooper() {
                Dispatcher.getHtml(handler, params);
            }
        };
    }

    @Override
    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void setTarget(String machineNumber, Calendar selectedDate) {
        this.machineNumber = machineNumber;
        this.selectedDate = selectedDate;
    }

    private String getBonusFilePath() {
        return Config.DATA_DIR + "bonus/" + machineNumber + "/";
    }

    private String getBonusFileName() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        String date = df.format(selectedDate.getTime());
        return date + ".json";
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
