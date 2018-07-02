package com.cage.library.utils.data;

import java.util.List;

/**
 * Created by luyunfeng on 17/8/2.
 */

public abstract class Encoder<T> {

    public String encode(List<T> list, String outerDelimiter, String innerDelimiter){

        if (ListUtils.isEmpty(list)) return "";

        StringBuilder sb = new StringBuilder();

        boolean firstTime = true;

        for (int i = 0; i < list.size(); i++) {

            if (firstTime) {
                firstTime = false;
            } else {
                sb.append(outerDelimiter);
            }

            T entity = list.get(i);

            ListUtils.join(sb, innerDelimiter, getJoinList(entity));
        }

        return sb.toString();
    }

    public abstract String[] getJoinList(T entity);


}
