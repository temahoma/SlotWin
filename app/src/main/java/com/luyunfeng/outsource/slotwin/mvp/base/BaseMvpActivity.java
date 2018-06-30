package com.luyunfeng.outsource.slotwin.mvp.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class BaseMvpActivity<V extends BaseView, P extends BasePresenter<V>>
        extends AppCompatActivity
        implements BaseView,
        View.OnClickListener,
        BaseUI {

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
            if (this.isDestroyed() || this.isFinishing())
                return false;
        } else if (this.isFinishing())
            return false;
        return true;
    }

    protected void openActivity(Class cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

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
