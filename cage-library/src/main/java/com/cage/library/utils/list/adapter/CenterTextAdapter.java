package com.cage.library.utils.list.adapter;


import com.cage.library.R;

import java.util.List;

/**
 * Created by luyunfeng on 16/11/17.
 */

public class CenterTextAdapter extends BaseSimpleAdapter<String> {

    public CenterTextAdapter(List<String> list){
        super(R.layout.list_item_center_text, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, String entity) {
        super.convert(holder, entity);
        holder.setText(R.id.ItemTextCenter, entity);
    }
}
