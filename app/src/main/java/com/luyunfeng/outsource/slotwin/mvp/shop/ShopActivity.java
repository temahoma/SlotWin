package com.luyunfeng.outsource.slotwin.mvp.shop;

import android.os.Bundle;
import android.widget.TextView;

import com.luyunfeng.outsource.slotwin.R;
import com.luyunfeng.outsource.slotwin.bean.BaseBouns;
import com.luyunfeng.outsource.slotwin.mvp.base.BaseMvpActivity;
import com.luyunfeng.outsource.slotwin.shop.BaseShop;
import com.luyunfeng.outsource.slotwin.shop.ShopBuilder;

import java.util.List;

/**
 * http://papimo.jp/h/00001616/hit/view/717/20180623
 * http://www.paradiso.jp/machine_data/sp/10017/detail.php?h=10017&c=c749ef54b188453ad32b67f20af230a1&k=S20&d=1561
 * http://639982.p-moba.net/game_machine_detail.php?id=237
 * http://pleforce.co.jp/holldata/
 */
public class ShopActivity extends BaseMvpActivity<ShopContract.IView, ShopContract.IPresenter> implements ShopContract.IView {

    @Override
    public void initialized() {

        Bundle bundle = getIntent().getExtras();

        String shop_id = bundle.getString("shop_id");
        String shop_name = bundle.getString("shop_name");
        String shop_url = bundle.getString("shop_url");
        String shop_website = bundle.getString("shop_website");

        tv_shop_name.setText(shop_name);

        //        Calendar now = Calendar.getInstance();
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
//        String date = df.format(now.getTime());
//
//        Calendar.getInstance();

        BaseShop baseShop = new ShopBuilder()
                .setWebsite(shop_website)
                .setName(shop_name)
                .setUrl(shop_url)
                .build();

        prestener.setShop(baseShop);
        prestener.readHtml();
    }

    @Override
    public void display(List<? extends BaseBouns> bounsList) {

    }

    @Override
    public void empty() {

    }

    TextView tv_shop_name;

    @Override
    public void setupViews() {
        tv_shop_name = findViewById(R.id.tv_shop_name);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop;
    }

    @Override
    protected void initPresenter() {
        prestener = new ShopPresenter();
    }
}
