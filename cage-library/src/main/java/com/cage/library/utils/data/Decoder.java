package com.cage.library.utils.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by luyunfeng on 17/8/2.
 */

public abstract class Decoder<T> {

    public List<T> decode(String str, String outerDelimiter, String innerDelimiter){

        List<String> list = ListUtils.split(outerDelimiter, str);

        if (ListUtils.isEmpty(list)) {
            return Collections.emptyList();
        }

        List<T> pickupList = new ArrayList<>();

        for (String singleItem : list) {
            List<String> items = ListUtils.split(innerDelimiter, singleItem);
            if (!ListUtils.isEmpty(items)){
                pickupList.add(getEntity(items));
            }
        }
        return pickupList;
    }

    public abstract T getEntity(List<String> items);
}
