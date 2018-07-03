package com.luyunfeng.outsource.slotwin.mvp.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cage.library.utils.data.ListUtils;
import com.cage.library.utils.list.adapter.CenterTextAdapter;
import com.cage.library.utils.view.ActivityUtils;
import com.luyunfeng.outsource.slotwin.R;
import com.luyunfeng.outsource.slotwin.bean.Prefecture;
import com.luyunfeng.outsource.slotwin.mvp.base.BaseMvpActivity;
import com.luyunfeng.outsource.slotwin.mvp.shop.ShopActivity;
import com.luyunfeng.outsource.slotwin.shop.BaseShop;
import com.luyunfeng.outsource.slotwin.view.dialog.DialogFactory;
import com.luyunfeng.outsource.slotwin.view.dialog.OnSingleSelectedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * http://papimo.jp/h/00001616/hit/view/717/20180623
 * http://www.paradiso.jp/machine_data/sp/10017/detail.php?h=10017&c=c749ef54b188453ad32b67f20af230a1&k=S20&d=1561
 * http://639982.p-moba.net/s/game_machine_detail.php?id=416
 * http://pleforce.co.jp/holldata/
 */
public class MainActivity extends BaseMvpActivity<MainContract.IView, MainContract.IPresenter> implements MainContract.IView {

    Button buttonPapimo;
    Button buttonParadiso;

    Prefecture current;

    @Override
    public void initialized() {
        prestener.prepareSelections();
    }

    @Override
    public void enableSelections(final List<Prefecture> prefectures) {

        if (ListUtils.isEmpty(prefectures)){
            return;
        }

        final List<String> list = new ArrayList<>(prefectures.size());
        for (Prefecture prefecture : prefectures) {
            list.add(prefecture.getName());
        }

        buttonPapimo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFactory.show(MainActivity.this, 0, new CenterTextAdapter(list), new OnSingleSelectedListener() {
                    @Override
                    public void onItemSelected(int index) {
                        current = prefectures.get(index);
                    }
                });
            }
        });

        buttonParadiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (current == null || ListUtils.isEmpty(current.getShops())) {
                    return;
                }

                final List<String> shops = new ArrayList<>(current.getShops().size());
                for (BaseShop shop : current.getShops()) {
                    shops.add(shop.name);
                }

                DialogFactory.show(MainActivity.this, 0, new CenterTextAdapter(shops), new OnSingleSelectedListener() {
                    @Override
                    public void onItemSelected(int index) {
                        BaseShop selected = current.getShops().get(index);
                        Bundle bundle = new Bundle();
                        bundle.putString("shop_id", selected.getId());
                        bundle.putString("shop_name", selected.getName());
                        bundle.putString("shop_url", selected.getUrl());
                        bundle.putString("shop_website", selected.getWebsite());
                        ActivityUtils.open(MainActivity.this, ShopActivity.class, bundle);
                    }
                });
            }
        });
    }

    @Override
    public void setupViews() {
        buttonPapimo = findViewById(R.id.buttonPapimo);
        buttonParadiso = findViewById(R.id.buttonParadiso);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {
        prestener = new MainPresenter();
    }

}
