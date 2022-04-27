package com_budget.budget.budget_java.personalbudget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.model.GradientColor;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //private BarChart chart;
    //private ArrayList barArrayList;

    //RecyclerView implementing
    private RecyclerView rvLegend;
    //ArrayList for items legends in recyclerView
    //Adapter for Legend RecyclerView
    private AdapterRvLegends adapterRvLegends;
    //Pie chart View
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pieChart = findViewById(R.id.chart1);
        rvLegend = findViewById(R.id.rvLegend);
        setupPieChart();
        loadPieChartData();
        buildRecyclerVIew();

        rvLegend.setAdapter(adapterRvLegends);
       /* chart = findViewById(R.id.chart1);
        getData();
        BarDataSet barDataSet = new BarDataSet(barArrayList, "Amount Of Money in $");
        BarData barData = new BarData(barDataSet);
        chart.setData(barData);
        //color bar data set
        barDataSet.setColor(getResources().getColor(R.color.purple_200));
        //text color
        barDataSet.setValueTextColor(Color.BLACK);
        //setting text size
        barDataSet.setValueTextSize(16f);
        Description descriptionChart = new Description();
        descriptionChart.setText("");
        chart.setDescription(descriptionChart);
        chart.getDescription().setEnabled(true);*/

    }

    private void buildRecyclerVIew(){
        rvLegend.setHasFixedSize(true);
        rvLegend.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setDataLegendForRecyclerView(){

    }

    private void setupPieChart() {
        pieChart.setDrawRoundedSlices(true);
        pieChart.setDrawHoleEnabled(true);
        //pieChart.setUsePercentValues(true);
        //pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.TRANSPARENT);
        pieChart.setCenterText("январь");
        pieChart.setCenterTextSize(20);
        pieChart.getDescription().setEnabled(false);

        //Legend l = pieChart.getLegend();


        Legend l = pieChart.getLegend();

        l.setTextColor(Color.BLACK);


        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setTextColor(Color.BLACK);

        l.setForm(Legend.LegendForm.CIRCLE);
        l.setTextSize(12);
        l.setFormSize(20);
        l.setFormToTextSpace(2);
        l.setYEntrySpace(10);

        l.setDrawInside(false);
        l.setEnabled(true);



    }

    private void loadPieChartData() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(1000, "Drinking"));
        entries.add(new PieEntry(900, "Food"));
        entries.add(new PieEntry(1000, "Medical"));
        entries.add(new PieEntry(1000, "Taxi"));
        entries.add(new PieEntry(300, "Apps"));

        adapterRvLegends = new AdapterRvLegends(entries, this);

        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        for (int color: ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(colors);
        dataSet.setSliceSpace(3);
        dataSet.setValueTextSize(0f);

        dataSet.setSelectionShift(5f);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueTextSize(0);

        data.setValueFormatter(new PercentFormatter(pieChart));
        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }

    /*private void getData() {
        barArrayList = new ArrayList();
        barArrayList.add(new BarEntry(2f, 10));
        barArrayList.add(new BarEntry(3f, 20));
        barArrayList.add(new BarEntry(4f, 30));
        barArrayList.add(new BarEntry(5f, 40));
        barArrayList.add(new BarEntry(6f, 50));
        barArrayList.add(new BarEntry(7f, 60));
    }*/

}