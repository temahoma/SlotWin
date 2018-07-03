package com.cage.library.utils.list.adapter;

import android.view.View;

import java.util.List;

/**
 * Created by luyunfeng on 16/11/17.
 */

public abstract class BaseSimpleAdapter<t> extends CageAdapter<t> {

    private OnItemClickListener clickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    public OnItemClickListener getOnItemClickListener() {
        return clickListener;
    }

    public BaseSimpleAdapter(int layoutId, List<t> list) {
        super(layoutId, list);
    }

    @Override
    protected void convert(final BaseViewHolder holder, t entity) {
        if (clickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClicked(holder.getAdapterPosition());
                }
            });
        }
    }
}
