package com_budget.budget.budget_java.personalbudget;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class ExpenseMonthFragment extends Fragment{

    //RecyclerView implementing
    private RecyclerView rvLegend, rvMonths;
    //ArrayList for items months in recyclerView
    ArrayList<ItemMonth> arrayMonth;
    //Adapter for Months RecyclerView
    private AdapterMonths adapterMonths;
    //Adapter for Legend RecyclerView
    private AdapterRvLegends adapterRvLegends;
    //Pie chart View
    private PieChart pieChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expense_month, container, false);
        init(view);

        setupPieChart();
        loadPieChartData();
        buildRecyclerViewLegend();

        rvLegend.setAdapter(adapterRvLegends);

        buildRecyclerViewMonth();
        setDataMonths();

        return view;
    }

    private void init(View view) {
        pieChart = view.findViewById(R.id.chart1);
        rvLegend = view.findViewById(R.id.rvLegend);
        rvMonths = view.findViewById(R.id.rvMonths);
    }

    private void buildRecyclerViewLegend() {
        rvLegend.setHasFixedSize(true);
        rvLegend.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void buildRecyclerViewMonth() {
        rvMonths.setHasFixedSize(true);
        rvMonths.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
    }

    private void setDataMonths(){
        arrayMonth = new ArrayList<>();
        arrayMonth.add(new ItemMonth("January"));
        arrayMonth.add(new ItemMonth("February"));
        arrayMonth.add(new ItemMonth("March"));
        arrayMonth.add(new ItemMonth("April"));
        arrayMonth.add(new ItemMonth("May"));
        arrayMonth.add(new ItemMonth("June"));
        arrayMonth.add(new ItemMonth("July"));
        arrayMonth.add(new ItemMonth("August"));
        arrayMonth.add(new ItemMonth("September"));
        arrayMonth.add(new ItemMonth("October"));
        arrayMonth.add(new ItemMonth("November"));
        arrayMonth.add(new ItemMonth("December"));
        adapterMonths = new AdapterMonths(arrayMonth, getContext());
        rvMonths.setAdapter(adapterMonths);
    }


    private void setupPieChart() {
        pieChart.setDrawRoundedSlices(true);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setEntryLabelColor(Color.TRANSPARENT);
        pieChart.setCenterText("January");
        pieChart.setCenterTextSize(20);
        pieChart.getDescription().setEnabled(false);

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

        adapterRvLegends = new AdapterRvLegends(entries, getContext());

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(new int[]{R.color.purple_500, R.color.yellow, R.color.red, R.color.purple_200, R.color.purple_700, R.color.blue, R.color.black}, getContext());
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
}