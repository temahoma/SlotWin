package com.luyunfeng.outsource.slotwin.bean.shop;

import com.luyunfeng.outsource.slotwin.bean.BaseBonus;

import org.jsoup.nodes.Document;

import java.util.Calendar;
import java.util.List;

/**
 * Created by luyunfeng on 2018/7/4.
 */

public interface HtmlObject {

    List<BaseBonus> parse(Document document);

    String getMachineUrl(String shopUrl, String machineNumber, Calendar selectedDate);
}
