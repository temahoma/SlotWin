package com.luyunfeng.outsource.slotwin.mvp.chart;

import android.graphics.Color;

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
import com.luyunfeng.outsource.slotwin.bean.EmptyValueFormatter;
import com.luyunfeng.outsource.slotwin.bean.BaseBouns;
import com.luyunfeng.outsource.slotwin.bean.PriceType;
import com.luyunfeng.outsource.slotwin.mvp.base.BaseMvpActivity;
import com.luyunfeng.outsource.slotwin.utils.ResourceHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ChartActivity extends BaseMvpActivity<ChartContract.IView, ChartContract.IPresenter> implements ChartContract.IView {

    CombinedChart chart;

    @Override
    public void initialized() {

        chart = findViewById(R.id.chart);
        chart.setNoDataText("Loading...");
        chart.setDescription(null);

        prestener.setUrl("http://papimo.jp/h/00001616/hit/view/717/20180620");
        prestener.readHtml();
    }

    @Override
    public void display(List<? extends BaseBouns> bounsList) {

        CombinedData combinedData = new CombinedData();

        final float lineFactor = getLineFactor(bounsList);
        LineData lineData = getLineData(bounsList, lineFactor);
        combinedData.setData(lineData);

        BarData barData = getBarData(bounsList);
        combinedData.setData(barData);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        int labelCount = Math.min(bounsList.size(), 15);
        xAxis.setLabelCount(labelCount, true);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.enableGridDashedLine(10,10,0);

        YAxis right = chart.getAxisRight();
        right.setDrawLabels(false);
        right.setDrawGridLines(false);

        YAxis left = chart.getAxisLeft();
        left.enableGridDashedLine(10,10,0);
        left.setLabelCount(7, true);
        left.setSpaceBottom(0);
        left.setTextSize(8);
        left.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
//                if (value == 0) {
//                    return "";
//                }
                float yValue = (value - 1) / lineFactor;
//                if (yValue < 0){
//                    return "";
//                }
                BigDecimal bigDecimal  =   new  BigDecimal((value - 1) / lineFactor);
                bigDecimal = bigDecimal.setScale(0,  BigDecimal.ROUND_HALF_UP);
                return bigDecimal.toString();
            }
        });

        LimitLine limitLine = new LimitLine(1); //得到限制线
        limitLine.setLineWidth(2f); //宽度
        limitLine.setLineColor(Color.parseColor("#6E6E6E"));
        left.addLimitLine(limitLine); //Y轴添加限制线

        chart.setData(combinedData);
        chart.invalidate(); // refresh
    }

    @Override
    public void empty() {
        chart.setNoDataText("Empty");
        chart.invalidate();
    }

    LineData getLineData(List<? extends BaseBouns> bounsList, float factor) {

        List<Entry> entries = new ArrayList<>();

        for (BaseBouns bouns : bounsList) {
            Entry entry = new Entry(
                    bouns.index,
                    bouns.accumulateProfit * factor + 1,
                    bouns
            );
            entries.add(entry);
        }

        LineDataSet dataSet = new LineDataSet(entries, "收益(枚)"); // add entries to dataset
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

        BarDataSet bigPriceDataSet = new BarDataSet(bigPriceEntries, "BIG");
        BarDataSet regPriceDataSet = new BarDataSet(regPriceEntries, "REG");
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

    float getLineFactor(List<? extends BaseBouns> counts) {
        BaseBouns maxBonus = Collections.max(counts, new Comparator<BaseBouns>() {
            @Override
            public int compare(BaseBouns o1, BaseBouns o2) {
                return Integer.compare(o1.accumulateProfit, o2.accumulateProfit);
            }
        });

        float factor = 8f / (maxBonus.accumulateProfit) * 0.8f;
        return factor;
    }


    @Override
    public void setupViews() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {
        prestener = new ChartPresenter();
    }
}
