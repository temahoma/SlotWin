package com.luyunfeng.outsource.slotwin.bean.shop;

import com.luyunfeng.outsource.slotwin.bean.BaseBouns;
import com.luyunfeng.outsource.slotwin.bean.ParadisoBouns;
import com.luyunfeng.outsource.slotwin.bean.PriceType;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by luyunfeng on 2018/6/24.
 */

public class ParadisoHtml implements HtmlObject{

    @Override
    public List<? extends BaseBouns> parse(Document document) {
        List<BaseBouns> bonusList = new ArrayList<>();
        Element body = document.body();
        // data_box 含多天
        Element div = body.getElementsByClass("data_box").get(0);
        Element table = div.getElementsByClass("list_table").get(1);
        Elements records = table.getElementsByTag("tbody").get(0).getElementsByTag("tr");

        for (int i = records.size() - 1; i >= 0; i--) {
            Element record = records.get(i);

            Elements tds = record.getElementsByTag("td");

            String bonusType = PriceType.REG;
            Integer bonus = 104;
            if ("ビッグ".equals(tds.get(1).html())){
                bonusType = PriceType.BIG;
                bonus = 325;
            }
            String countStr = tds.get(2).html();
            countStr = countStr.substring(0, countStr.length() - 1);
            Integer count = Integer.parseInt(countStr);

            BaseBouns bouns = new ParadisoBouns(bonusList.size() + 1, count, bonus, bonusType);
            int accumulateProfit = bouns.getProfit(count, bonus);
            if (bonusList.size() > 0) {
                accumulateProfit += bonusList.get(bonusList.size() - 1).accumulateProfit;
            }
            bouns.setAccumulateProfit(accumulateProfit);

            bonusList.add(bouns);
        }
        return bonusList;
    }

    @Override
    public String getMachineUrl(String shopUrl, String machineNumber, Calendar selectedDate) {
        return null;
    }
}
