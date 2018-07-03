package com.luyunfeng.outsource.slotwin.view.dialog;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.cage.library.utils.device.DeviceUtils;
import com.cage.library.utils.list.adapter.BaseSimpleAdapter;
import com.luyunfeng.outsource.slotwin.R;
import com.mingle.sweetpick.BlurEffect;
import com.mingle.sweetpick.CustomDelegate;
import com.mingle.sweetpick.DimEffect;
import com.mingle.sweetpick.SweetSheet;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.DialogPlusBuilder;
import com.orhanobut.dialogplus.ViewHolder;

/**
 * Created by luyunfeng on 16/9/27.
 */

public class DialogFactory {

    interface InternalCallBack {
        void onItemSelected(int index);
    }

    public static void show(Activity activity, int titleId, BaseSimpleAdapter adapter, final OnSingleSelectedListener listener) {

        DVSingleSelection dv = new DVSingleSelection(activity)
                .setTitle(titleId)
                .setAdapter(adapter);

        final DialogPlus dialog = getBottomDialog(dv.getView());

        dv.setCallBack(listener, dialog).initView();

        dialog.show();
    }

    public static void show(Activity activity, View view, boolean isDim) {

        ViewGroup viewGroup = activity.getWindow().getDecorView().findViewById(android.R.id.content);

        final SweetSheet sweetSheet = new SweetSheet(viewGroup);

        show(sweetSheet, view, isDim);
    }

    public static void show(SweetSheet sweetSheet, View view, boolean isDim) {
        CustomDelegate customDelegate = new CustomDelegate(true, CustomDelegate.AnimationType.DuangLayoutAnimation);

        sweetSheet.setBackgroundEffect(isDim ? new DimEffect(0.7f) : new BlurEffect(8));

        customDelegate.setCustomView(view);
        sweetSheet.setDelegate(customDelegate);
        sweetSheet.show();
    }

    private static DialogPlus getBottomDialog(View view) {
        return getBottomDialogBuilder(view, 0, 0, 0, 0).create();
    }

    private static DialogPlusBuilder getBottomDialogBuilder(View view) {
        return getBottomDialogBuilder(view, 0, 0, 0, 0);
    }

    private static DialogPlusBuilder getBottomDialogBuilder(View view, int left, int top, int right, int bottom) {
        int height = DeviceUtils.mScreenHeight;
        if (height == 0) {
            height = 1280;
        }
        return DialogPlus.newDialog(view.getContext())
                .setContentHolder(new ViewHolder(view))
                .setContentHeight(height * 3 / 5)
                .setGravity(Gravity.BOTTOM)
                .setInAnimation(R.anim.slide_top_in)
                .setOutAnimation(R.anim.slide_bottom_out)
                .setPadding(left, top, right, bottom);
    }

    private static DialogPlus getSheet(View view) {
        return DialogPlus.newDialog(view.getContext())
                .setContentHolder(new ViewHolder(view))
                .setContentBackgroundResource(Color.TRANSPARENT)
                .setGravity(Gravity.BOTTOM)
                .setInAnimation(R.anim.slide_top_in)
                .setOutAnimation(R.anim.slide_bottom_out).create();
    }
}
