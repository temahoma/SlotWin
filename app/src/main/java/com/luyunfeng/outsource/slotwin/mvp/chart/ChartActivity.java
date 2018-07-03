package com.luyunfeng.outsource.slotwin.mvp.chart;

import android.graphics.Color;
import android.os.Bundle;

import com.cage.library.infrastructure.resource.ResourceHelper;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.luyunfeng.outsource.slotwin.CountValueFormatter;
import com.luyunfeng.outsource.slotwin.ProfitValueFormatter;
import com.luyunfeng.outsource.slotwin.R;
import com.luyunfeng.outsource.slotwin.bean.BaseBouns;
import com.luyunfeng.outsource.slotwin.bean.EmptyValueFormatter;
import com.luyunfeng.outsource.slotwin.bean.PriceType;
import com.luyunfeng.outsource.slotwin.mvp.base.BaseMvpActivity;
import com.luyunfeng.outsource.slotwin.shop.BaseShop;
import com.luyunfeng.outsource.slotwin.shop.ShopBuilder;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * http://papimo.jp/h/00001616/hit/view/717/20180623
 * http://www.paradiso.jp/machine_data/sp/10017/detail.php?h=10017&c=c749ef54b188453ad32b67f20af230a1&k=S20&d=1561
 * http://639982.p-moba.net/game_machine_detail.php?id=237
 * http://pleforce.co.jp/holldata/
 */
public class ChartActivity extends BaseMvpActivity<ChartContract.IView, ChartContract.IPresenter> implements ChartContract.IView {

    CombinedChart chart;

    @Override
    public void initialized() {

        Bundle bundle = getIntent().getExtras();
        String shop = bundle.getString("shop");

        Calendar now = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        String date = df.format(now.getTime());

        Calendar.getInstance();
        BaseShop baseShop = new ShopBuilder()
                .setWebsite(shop)
                .build();
        prestener.setShop(baseShop);
        prestener.readHtml();
    }

    @Override
    public void display(List<? extends BaseBouns> bounsList) {

        CombinedData combinedData = new CombinedData();

        final int lineOffset = getLineOffset(bounsList);
        final float lineFactor = getLineFactor(bounsList, lineOffset);
        LineData lineData = getLineData(bounsList, lineOffset, lineFactor);
        combinedData.setData(lineData);

        BarData barData = getBarData(bounsList);
        combinedData.setData(barData);

        setupXAxis(bounsList.size());

        setupYAxis(lineOffset, lineFactor);

        chart.setData(combinedData);
        chart.invalidate(); // refresh
    }

    private void setupYAxis(final int lineOffset, final float lineFactor) {

        YAxis right = chart.getAxisRight();
        right.setDrawLabels(false);
        right.setDrawGridLines(false);

        YAxis left = chart.getAxisLeft();
        left.enableGridDashedLine(10, 10, 0);
        left.setLabelCount(7, true);
        left.setSpaceBottom(0);
        left.setTextSize(8);
        left.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
//                if (value == 0) {
//                    return "";
//                }
                float yValue = ((value - 1) / lineFactor) - lineOffset;
//                if (yValue < 0){
//                    return "";
//                }
                BigDecimal bigDecimal = new BigDecimal(yValue);
                bigDecimal = bigDecimal.setScale(0, BigDecimal.ROUND_HALF_UP);
                return bigDecimal.toString();
            }
        });

        LimitLine limitLine = new LimitLine(1 + lineOffset * lineFactor); //得到限制线
        limitLine.setLineWidth(2f); //宽度
        limitLine.setLineColor(ResourceHelper.getColor(R.color.fair_line));
        limitLine.setLabel(getString(R.string.fair));
        left.addLimitLine(limitLine); //Y轴添加限制线
    }

    private void setupXAxis(int size) {
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        int labelCount = Math.min(size, 15);
        xAxis.setLabelCount(labelCount, true);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.enableGridDashedLine(10, 10, 0);
    }

    @Override
    public void empty() {
        chart.setNoDataText("Empty");
        chart.invalidate();
    }

    LineData getLineData(List<? extends BaseBouns> bounsList, int lineOffset, float factor) {

        List<Entry> entries = new ArrayList<>();

        for (BaseBouns bouns : bounsList) {
            Entry entry = new Entry(
                    bouns.index,
                    (bouns.accumulateProfit + lineOffset) * factor + 1,
                    bouns
            );
            entries.add(entry);
        }


        LineDataSet dataSet = new LineDataSet(entries, ResourceHelper.getString(R.string.label_profit)); // add entries to dataset
        dataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        dataSet.setColor(ResourceHelper.getColor(R.color.profit_line));
        dataSet.setValueTextColor(ResourceHelper.getColor(R.color.profit_text));
        dataSet.setValueTextSize(10);
        dataSet.setCircleColor(ResourceHelper.getColor(R.color.profit_text));
        dataSet.setValueFormatter(new ProfitValueFormatter());
        LineData lineData = new LineData();
        lineData.addDataSet(dataSet);
        return lineData;
    }

    BarData getBarData(List<? extends BaseBouns> bounsList) {

        float factor = getBarFactor(bounsList);

        List<BarEntry> bigPriceEntries = new ArrayList<>();
        List<BarEntry> regPriceEntries = new ArrayList<>();
        for (BaseBouns bouns : bounsList) {
            BarEntry barEntry = new BarEntry(
                    bouns.index,
                    bouns.count * factor,
                    bouns);
            if (bouns.type.equals(PriceType.BIG)) {
                bigPriceEntries.add(barEntry);
            } else {
                regPriceEntries.add(barEntry);
            }
        }

        BarDataSet bigPriceDataSet = new BarDataSet(bigPriceEntries, ResourceHelper.getString(R.string.label_big));
        BarDataSet regPriceDataSet = new BarDataSet(regPriceEntries, ResourceHelper.getString(R.string.label_reg));
        CountValueFormatter countValueFormatter = new CountValueFormatter();
        bigPriceDataSet.setValueFormatter(countValueFormatter);
        regPriceDataSet.setValueFormatter(countValueFormatter);

        bigPriceDataSet.setColor(ResourceHelper.getColor(R.color.price_big));
        regPriceDataSet.setColor(ResourceHelper.getColor(R.color.price_reg));
        bigPriceDataSet.setValueTextSize(10);
        regPriceDataSet.setValueTextSize(10);

        List<BarEntry> emptyEntries = new ArrayList<>();
        emptyEntries.add(new BarEntry(0, 0f));
        emptyEntries.add(new BarEntry(bounsList.size() + 1, 0f));
        BarDataSet emptyDataSet = new BarDataSet(emptyEntries, null);
        emptyDataSet.setColor(Color.TRANSPARENT);
        emptyDataSet.setValueFormatter(new EmptyValueFormatter());

        BarData barData = new BarData();
        barData.setBarWidth(0.5f);
        barData.addDataSet(bigPriceDataSet);
        barData.addDataSet(regPriceDataSet);
        barData.addDataSet(emptyDataSet);

        return barData;
    }

    float getBarFactor(List<? extends BaseBouns> counts) {
        int maxCount = Collections.max(counts, new Comparator<BaseBouns>() {
            @Override
            public int compare(BaseBouns o1, BaseBouns o2) {
                return Integer.compare(o1.count, o2.count);
            }
        }).count;
        float factor = 1f / maxCount * 0.8f;
        return factor;
    }

    int getLineOffset(List<? extends BaseBouns> counts) {
        int offset = 0;
        BaseBouns minBonus = Collections.min(counts, new Comparator<BaseBouns>() {
            @Override
            public int compare(BaseBouns o1, BaseBouns o2) {
                return Integer.compare(o1.accumulateProfit, o2.accumulateProfit);
            }
        });
        if (minBonus.accumulateProfit < 0) {
            offset = -minBonus.accumulateProfit;
        }
        return offset;
    }

    float getLineFactor(List<? extends BaseBouns> counts, int lineOffset) {
        BaseBouns maxBonus = Collections.max(counts, new Comparator<BaseBouns>() {
            @Override
            public int compare(BaseBouns o1, BaseBouns o2) {
                return Integer.compare(o1.accumulateProfit, o2.accumulateProfit);
            }
        });

        float factor = 8f / (maxBonus.accumulateProfit + lineOffset) * 0.8f;
        return factor;
    }


    @Override
    public void setupViews() {
        chart = findViewById(R.id.chart);
        chart.setNoDataText("Loading...");
        chart.setDescription(null);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_chart;
    }

    @Override
    protected void initPresenter() {
        prestener = new ChartPresenter();
    }
}
