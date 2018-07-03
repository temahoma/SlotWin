package com.luyunfeng.outsource.slotwin.view.dialog;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.cage.library.infrastructure.resource.ResourceHelper;
import com.cage.library.infrastructure.text.StringUtils;
import com.cage.library.utils.list.adapter.BaseSimpleAdapter;
import com.cage.library.utils.list.adapter.OnItemClickListener;
import com.luyunfeng.outsource.slotwin.MyApplication;
import com.luyunfeng.outsource.slotwin.R;
import com.luyunfeng.outsource.slotwin.utils.ViewHelper;
import com.orhanobut.dialogplus.DialogPlus;

/**
 * Created by luyunfeng on 16/11/16.
 */

public class DVSingleSelection extends DVBase {

    private DialogFactory.InternalCallBack callBack;
    private OnSingleSelectedListener listener;
    private DialogPlus dialog;

    private BaseSimpleAdapter adapter;

    private String title;

    DVSingleSelection setTitle(int titleId) {
        this.title = ResourceHelper.getString(titleId);
        return this;
    }

    DVSingleSelection setAdapter(BaseSimpleAdapter adapter) {
        this.adapter = adapter;
        return this;
    }

    DVSingleSelection setCallBack(DialogFactory.InternalCallBack callBack) {
        this.callBack = callBack;
        return this;
    }

    DVSingleSelection setCallBack(OnSingleSelectedListener listener, DialogPlus dialog) {
        this.listener = listener;
        this.dialog = dialog;
        return this;
    }

    DVSingleSelection(Context context) {
        super(context);
    }

    @Override
    void initView() {
        if (StringUtils.isEmpty(title)) {
            title = ResourceHelper.getString(R.string.select_plz);
        }
        ((TextView) getView().findViewById(R.id.tv_title)).setText(title);

        RecyclerView rv_selection = (RecyclerView) getView().findViewById(R.id.rv_selection);
        rv_selection.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));

        rv_selection.addItemDecoration(ViewHelper.getDivider());
        if (adapter != null) {
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClicked(int index) {
                    if (listener != null) {
                        listener.onItemSelected(index);
                        if (dialog != null) dialog.dismiss();
                    } else if (callBack != null) {
                        callBack.onItemSelected(index);
                    }
                }
            });
            rv_selection.setAdapter(adapter);
        }
    }

    @Override
    int getLayoutId() {
        return R.layout.dialog_single_selection;
    }
}
