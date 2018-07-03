package com.luyunfeng.outsource.slotwin.utils;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ScrollView;

import com.luyunfeng.outsource.slotwin.MyApplication;
import com.luyunfeng.outsource.slotwin.R;

/**
 * Created by luyunfeng on 15/10/19.
 * 控件相关实用类
 */
public class ViewHelper {

    public static void scrollToTop(final View scroll) {
        if (scroll == null) {
            return;
        }
        scroll.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (scroll instanceof ScrollView) {
                    ((ScrollView) scroll).smoothScrollTo(0, 0);
                } else if (scroll instanceof NestedScrollView) {
                    ((NestedScrollView) scroll).smoothScrollTo(0, 0);
                } else if (scroll instanceof RecyclerView) {
                    ((RecyclerView) scroll).smoothScrollToPosition(0);
                }
            }
        }, 100);
    }


    public static void setSpaceDivider(RecyclerView recyclerView) {
        if (recyclerView.getTag() == null) {
            RecyclerView.ItemDecoration itemDecoration =
                    new DividerBuilder()
                            .setType("GridDecoration")
                            .setDividerSizeRes(R.dimen.medium_1_space)
                            .build();
            recyclerView.addItemDecoration(itemDecoration);
            recyclerView.setTag(itemDecoration);
        }
    }

    public static RecyclerView.ItemDecoration getDivider() {
        return new DividerBuilder()
                .setDividerSizeRes(R.dimen.small_divider_height)
                .setDivider(R.color.light_grey_color)
                .build();
    }

    public static RecyclerView.ItemDecoration getLargeDivider() {
        return new DividerBuilder()
                .setDividerSizeRes(R.dimen.small_2_space)
                .setDivider(R.color.light_grey_color)
                .build();
    }

    /**
     * ScrollView嵌套列表
     */
    public static RecyclerView getNestRecyclerView(RecyclerView rv) {
        return getNestRecyclerView(rv, 0);
    }

    public static RecyclerView getNestRecyclerView(RecyclerView rv, int spanCount) {

        rv.setNestedScrollingEnabled(false);
        rv.setFocusable(false);
        rv.setFocusableInTouchMode(false);

        if (spanCount > 1) {
            rv.setLayoutManager(new GridLayoutManager(MyApplication.getContext(), spanCount) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
        } else {
            rv.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
        }
        return rv;
    }
}
