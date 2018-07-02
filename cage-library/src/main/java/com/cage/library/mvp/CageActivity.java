package com.cage.library.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class CageActivity<V extends CageView, P extends CagePresenter<V>>
        extends AppCompatActivity
        implements CageView,
        CageUI {

    protected P prestener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = getLayoutId();
        if (layoutId != 0) {
            setContentView(layoutId);
        }

        initPresenter();
        prestener.onAttach((V) this);

        // 初始化组件
        setupViews();

        // 初始化数据
        initialized();
    }

    @Override
    public boolean isActive() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (this.isDestroyed() || this.isFinishing()) {
                return false;
            }
        } else if (this.isFinishing()) {
            return false;
        }
        return true;
    }


    // 实例化presenter
    protected abstract void initPresenter();

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public View getDecorView() {
        return getWindow().getDecorView();
    }
}
