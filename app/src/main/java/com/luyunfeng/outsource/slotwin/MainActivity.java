package com.luyunfeng.outsource.slotwin;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CombinedChart chart = findViewById(R.id.chart);
        List<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(1, 11));
        entries.add(new Entry(2, 12));
        entries.add(new Entry(3, 11));
        entries.add(new Entry(4, 15));

        LineDataSet dataSet = new LineDataSet(entries, "Label1"); // add entries to dataset
        dataSet.setColor(Color.parseColor("#c1c1c1"));
        dataSet.setValueTextColor(Color.parseColor("#b1b1b1")); // st


        List<Entry> entries2 = new ArrayList<Entry>();

        entries2.add(new Entry(1, 12));
        entries2.add(new Entry(2, 13));
        entries2.add(new Entry(3, 14));
        entries2.add(new Entry(4, 12));

        LineDataSet dataSet2 = new LineDataSet(entries2, "Label2"); // add entries to dataset
        dataSet2.setColor(Color.parseColor("#FFB90F"));
        dataSet2.setValueTextColor(Color.parseColor("#FF6A6A"));

        CombinedData combinedData = new CombinedData();
        LineData lineData = new LineData();
        lineData.addDataSet(dataSet);
        lineData.addDataSet(dataSet2);
        combinedData.setData(lineData);


        List<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, 0f));
        barEntries.add(new BarEntry(1, 0.31f));
        barEntries.add(new BarEntry(2, 0.82f));
        barEntries.add(new BarEntry(3, 0.61f));
        barEntries.add(new BarEntry(4, 0.53f));
        BarDataSet barDataSet = new BarDataSet(barEntries, "Label3"); // add entries to dataset
        barDataSet.setFormSize(2f);
        barDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return String.valueOf(value * 100);
            }
        });
        barDataSet.setColors(Color.parseColor("#FFB90F"), Color.parseColor("#FF6A6A"));
        BarData barData = new BarData();
        barData.setBarWidth(0.5f);
        barData.addDataSet(barDataSet);
        combinedData.setData(barData);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(barEntries.size(), true);
        xAxis.setAvoidFirstLastClipping(true);
        YAxis right = chart.getAxisRight();
        right.setDrawLabels(false);
        right.setDrawGridLines(false);

        YAxis left = chart.getAxisLeft();
        left.setLabelCount(7);
        left.setSpaceBottom(0);
        left.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return String.valueOf(value - 2);
            }
        });


        LimitLine limitLine = new LimitLine(2); //得到限制线
        limitLine.setLineWidth(2f); //宽度
        limitLine.setLineColor(Color.parseColor("#FF6A6A"));
        left.addLimitLine(limitLine); //Y轴添加限制线

//        left.setDrawLabels(false); // no axis labels
//        left.setDrawGridLines(false); // no grid lines
//        left.setDrawZeroLine(true); // draw a zero line
//        left.setZeroLineColor(Color.parseColor("#FF6A6A"));
//        left.setZeroLineWidth(6);
        chart.setData(combinedData);
        chart.invalidate(); // refresh

    }
}
