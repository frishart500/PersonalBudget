package com_budget.budget.budget_java.personalbudget;


import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        RadarChart radarChart = findViewById(R.id.chartAnnualExpense);
        //entry 1
        ArrayList<RadarEntry> entries = new ArrayList<>();
        entries.add(new RadarEntry(220));
        entries.add(new RadarEntry(330));
        entries.add(new RadarEntry(440));
        entries.add(new RadarEntry(550));
        entries.add(new RadarEntry(660));
        entries.add(new RadarEntry(210));
        entries.add(new RadarEntry(350));
        entries.add(new RadarEntry(120));
        RadarDataSet radarDataSet = new RadarDataSet(entries, "");
        radarDataSet.setColor(Color.RED);
        radarDataSet.setLineWidth(2f);
        //radarDataSet.setValueTextColor(Color.RED);
        //radarDataSet.setValueTextSize(14f);
        //entry 2
        ArrayList<RadarEntry> entries2 = new ArrayList<>();
        entries2.add(new RadarEntry(120));
        entries2.add(new RadarEntry(310));
        entries2.add(new RadarEntry(340));
        entries2.add(new RadarEntry(340));
        entries2.add(new RadarEntry(450));
        entries2.add(new RadarEntry(450));
        entries2.add(new RadarEntry(560));
        entries2.add(new RadarEntry(310));
        entries2.add(new RadarEntry(310));
        entries2.add(new RadarEntry(650));
        entries2.add(new RadarEntry(320));
        entries2.add(new RadarEntry(320));
        RadarDataSet radarDataSet2 = new RadarDataSet(entries2, "");
        radarDataSet2.setColor(Color.BLUE);
        radarDataSet2.setLineWidth(2f);
        //radarDataSet2.setValueTextColor(Color.BLUE);
        //radarDataSet2.setValueTextSize(14f);
        //set the data
        RadarData radarData = new RadarData();
        radarData.addDataSet(radarDataSet);
        radarData.addDataSet(radarDataSet2);
        String[] labels = {"January", "February", "March", "April ", "May", "June ", "July", "August", "September", "October", "November", "December"};
        XAxis xAxis = radarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        radarChart.setData(radarData);


    }
}