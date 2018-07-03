package com.cage.library.utils.list.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;

import com.cage.library.utils.view.ViewUtils;

import java.util.List;

/**
 * Created by luyunfeng on 16/8/4.
 */
public abstract class CageAdapter<t> extends RecyclerView.Adapter<BaseViewHolder> {

    private SparseIntArray layoutMap;
    @LayoutRes
    private int layoutId;
    protected List<t> list;
    protected OnListItemClickListener<t> listener;
    protected OnListItemLongClickListener<t> longListener;

    /**
     * 空实现，调用此方法请重写{@link #onCreateViewHolder}
     */
    protected CageAdapter() {

    }

    // 单种类型
    protected CageAdapter(@LayoutRes int layoutId, List<t> list) {
        this.layoutId = layoutId;
        this.list = list;
    }

    // 多种类型
    protected CageAdapter(SparseIntArray layoutMap, List<t> list) {
        this.layoutMap = layoutMap;
        this.list = list;
    }

    // 多种类型，viewType = 布局id
    protected CageAdapter(List<t> list) {
        this.list = list;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout;
        if (layoutId != 0) {
            layout = layoutId;
        } else if (layoutMap != null) {
            layout = layoutMap.get(viewType);
        } else {
            layout = viewType;
        }
        return new BaseViewHolder(ViewUtils.inflateView(parent, layout));
    }

    protected abstract void convert(BaseViewHolder holder, t entity);

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (position < list.size()) {
            convert(holder, list.get(position));
        } else {
            convert(holder, null);
        }
    }


    public List<t> getList() {
        return list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    protected void setClickEvent(BaseViewHolder holder, final t entity) {
        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(entity);
                }
            });
        }
    }

    protected void setLongClickEvent(final BaseViewHolder holder, final t entity) {
        if (longListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    longListener.onItemLongClicked(holder.getAdapterPosition(), entity);
                    return true;
                }
            });
        }
    }

    public void setOnItemClickListener(OnListItemClickListener<t> listener) {
        this.listener = listener;
    }

    public void setOnItemLongClickListener(OnListItemLongClickListener<t> longListener) {
        this.longListener = longListener;
    }

    public interface OnListItemClickListener<t> {
        void onItemClicked(t entity);
    }


    public interface OnListItemLongClickListener<t> {
        void onItemLongClicked(int position, t entity);
    }
}
