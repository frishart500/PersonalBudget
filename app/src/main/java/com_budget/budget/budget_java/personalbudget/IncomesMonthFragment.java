package com_budget.budget.budget_java.personalbudget;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;


public class IncomesMonthFragment extends Fragment {

    //RecyclerView implementing
    private RecyclerView rvMonths;
    //ArrayList for items months in recyclerView
    ArrayList<ItemMonth> arrayMonthIncome;
    //Adapter for Months RecyclerView
    private AdapterMonths adapterMonths;
    //Adapter for Legend RecyclerView
    private AdapterRvLegends adapterRvLegends;
    //Pie chart View
    private PieChart chartIncomeMonth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_incomes_month, container, false);

        init(view);

        setupPieChart();
        loadPieChartData();

        buildRecyclerViewMonth();
        setDataMonths();

        return view;
    }

    private void init(View view) {
        chartIncomeMonth = view.findViewById(R.id.chartIncomeMonth);
        rvMonths = view.findViewById(R.id.rvMonthsIncome);
    }

    private void buildRecyclerViewMonth() {
        rvMonths.setHasFixedSize(true);
        rvMonths.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
    }

    private void setDataMonths(){
        arrayMonthIncome = new ArrayList<>();
        arrayMonthIncome.add(new ItemMonth("January"));
        arrayMonthIncome.add(new ItemMonth("February"));
        arrayMonthIncome.add(new ItemMonth("March"));
        arrayMonthIncome.add(new ItemMonth("April"));
        arrayMonthIncome.add(new ItemMonth("May"));
        arrayMonthIncome.add(new ItemMonth("June"));
        arrayMonthIncome.add(new ItemMonth("July"));
        arrayMonthIncome.add(new ItemMonth("August"));
        arrayMonthIncome.add(new ItemMonth("September"));
        arrayMonthIncome.add(new ItemMonth("October"));
        arrayMonthIncome.add(new ItemMonth("November"));
        arrayMonthIncome.add(new ItemMonth("December"));
        adapterMonths = new AdapterMonths(arrayMonthIncome, getContext());
        rvMonths.setAdapter(adapterMonths);
    }


    private void setupPieChart() {
        chartIncomeMonth.setDrawRoundedSlices(true);
        chartIncomeMonth.setDrawHoleEnabled(true);
        chartIncomeMonth.setEntryLabelColor(Color.TRANSPARENT);
        chartIncomeMonth.setCenterText("January");
        chartIncomeMonth.setCenterTextSize(20);
        chartIncomeMonth.getDescription().setEnabled(false);

        Legend l = chartIncomeMonth.getLegend();

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
        entries.add(new PieEntry(1000, "money transfer"));

        adapterRvLegends = new AdapterRvLegends(entries, getContext());

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(new int[]{R.color.purple_500}, getContext());
        dataSet.setSliceSpace(3);
        dataSet.setValueTextSize(0f);

        dataSet.setSelectionShift(5f);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueTextSize(0);

        data.setValueFormatter(new PercentFormatter(chartIncomeMonth));
        chartIncomeMonth.setData(data);
        chartIncomeMonth.invalidate();

        chartIncomeMonth.animateY(1400, Easing.EaseInOutQuad);
    }

}
