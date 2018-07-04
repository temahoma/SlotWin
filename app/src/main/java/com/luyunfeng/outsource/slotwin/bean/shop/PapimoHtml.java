package com.luyunfeng.outsource.slotwin.bean.shop;

import com.luyunfeng.outsource.slotwin.bean.BaseBouns;
import com.luyunfeng.outsource.slotwin.bean.PapimoBouns;
import com.luyunfeng.outsource.slotwin.bean.PriceType;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by luyunfeng on 2018/6/24.
 */

public class PapimoHtml implements HtmlObject {

    @Override
    public List<? extends BaseBouns> parse(Document document) {
        List<BaseBouns> bonusList = new ArrayList<>();
        Element body = document.body();
        Element history = body.getElementById("tab-history-index");
        Elements elements = history.getElementsByTag("tbody");
        Element tbody = elements.get(0);
        Elements records = tbody.getElementsByTag("tr");

        for (int i = records.size() - 1; i >= 0; i--) {
            Element record = records.get(i);
//                    Element cnt = record.getElementsByClass("cnt").get(0);
//                    Integer count = Integer.parseInt(cnt.html());
//                    Element time = record.getElementsByClass("time").get(0);
//                    String timeString = time.html();
            Element start = record.getElementsByClass("start").get(0);
            Integer count = Integer.parseInt(start.html());

            String bonusType = PriceType.REG;
            Elements elementsBig = record.getElementsByClass("big");
            if (elementsBig != null && elementsBig.size() > 0) {
                Element big = elementsBig.get(0);
                if (big != null) {
                    bonusType = PriceType.BIG;
                }
            }

            Element out = record.getElementsByClass("out").get(0);
            Integer bonus = Integer.parseInt(out.html());

            BaseBouns bouns = new PapimoBouns(bonusList.size() + 1, count, bonus, bonusType);
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
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        String date = df.format(selectedDate.getTime());
        return shopUrl + "/hit/view/" + machineNumber + "/" + date;
    }
}
