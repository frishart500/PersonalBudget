package com_budget.budget.budget_java.personalbudget;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity {


    // variable for our bar chart
    BarChart barChart;

    // variable for our bar data.
    BarData barData;

    // variable for our bar data set.
    BarDataSet barDataSet;

    // array list for storing entries.
    ArrayList barEntriesArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        // initializing variable for bar chart.

        barChart = findViewById(R.id.barChartAnnually);

        // calling method to get bar entries.
        getBarEntries();

        // creating a new bar data set.
        barDataSet = new BarDataSet(barEntriesArrayList, "Geeks for Geeks");

        // creating a new bar data and
        // passing our bar data set.
        barData = new BarData(barDataSet);

        // below line is to set data
        // to our bar chart.
        barChart.setData(barData);

        // adding color to our bar data set.
        barDataSet.setColors(new int[] { R.color.purple_500, R.color.yellow, R.color.red, R.color.purple_200 }, getApplicationContext());

        // setting text color.
        barDataSet.setValueTextColor(Color.BLACK);

        barChart.setDrawGridBackground(false);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.getAxisLeft().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.setPinchZoom(false);
        barChart.setVisibleXRangeMaximum(20); // allow 20 values to be displayed at once on the x-axis, not more
        barChart.moveViewToX(10);
        barChart.setDoubleTapToZoomEnabled(false);
        // setting text size
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(14f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setYOffset(5);
        xAxis.setDrawLabels(true);
        xAxis.setGranularityEnabled(true);
        xAxis.setGranularity(1f);
        xAxis.setXOffset(30);

        xAxis.setCenterAxisLabels(false);

    }

    private void getBarEntries() {
        // creating a new array list
        barEntriesArrayList = new ArrayList<>();

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntriesArrayList.add(new BarEntry(01.22f, 4));
        barEntriesArrayList.add(new BarEntry(02.22f, 6));
        barEntriesArrayList.add(new BarEntry(03.22f, 8));
        barEntriesArrayList.add(new BarEntry(04.22f, 2));
        barEntriesArrayList.add(new BarEntry(05.22f, 4));
        barEntriesArrayList.add(new BarEntry(06.22f, 1));
        barEntriesArrayList.add(new BarEntry(07.22f, 1));
        barEntriesArrayList.add(new BarEntry(08.22f, 1));
        barEntriesArrayList.add(new BarEntry(09.22f, 7));
        barEntriesArrayList.add(new BarEntry(10.22f, 4));
        barEntriesArrayList.add(new BarEntry(11.22f, 2));
        barEntriesArrayList.add(new BarEntry(12.22f, 1));
    }
}
