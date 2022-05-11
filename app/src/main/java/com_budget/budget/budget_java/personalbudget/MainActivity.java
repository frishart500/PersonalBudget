package com_budget.budget.budget_java.personalbudget;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private int[] tabIcons = {
            R.drawable.goes_down_img,
            R.drawable.goes_up_img
    };

    //btn addition
    //ImageView going to profile btn
    //btn go to bar chart (for annual period)
    private ImageView profileBtn, addBtn, graphCartBtn;
    //TabLayout implementing
    private TabLayout tabsExpensesAndIncomes;
    //RecyclerView implementing
    private RecyclerView rvLegend;
    //ArrayList for items legends in recyclerView
    //Adapter for Legend RecyclerView
    private AdapterRvLegends adapterRvLegends;
    //Pie chart View
    private PieChart pieChart;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        init();

        setupTabIcons();
        applyColorStateList(tabsExpensesAndIncomes, R.drawable.selector_color_tabs);

        setupPieChart();
        loadPieChartData();
        buildRecyclerVIew();

        rvLegend.setAdapter(adapterRvLegends);

        View.OnClickListener BTNsClicks = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.addBtn:
                        startActivity(new Intent(getApplicationContext(), AdditionActivity.class));
                        break;
                    case R.id.profileBtn:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        break;
                    case R.id.graphCartImgBtn:
                        startActivity(new Intent(getApplicationContext(), BarChartActivity.class));
                        break;
                }
            }
        };
        addBtn.setOnClickListener(BTNsClicks);
        graphCartBtn.setOnClickListener(BTNsClicks);
        profileBtn.setOnClickListener(BTNsClicks);

    }

    private void init(){
        profileBtn = findViewById(R.id.profileBtn);
        addBtn = findViewById(R.id.addBtn);
        graphCartBtn = findViewById(R.id.graphCartImgBtn);

        tabsExpensesAndIncomes = findViewById(R.id.tabsExpensesAndIncomes);

        pieChart = findViewById(R.id.chart1);
        rvLegend = findViewById(R.id.rvLegend);
    }

    private void buildRecyclerVIew(){
        rvLegend.setHasFixedSize(true);
        rvLegend.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setupTabIcons() {
        tabsExpensesAndIncomes.getTabAt(0).setIcon(tabIcons[0]);
        tabsExpensesAndIncomes.getTabAt(1).setIcon(tabIcons[1]);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void applyColorStateList(@NonNull TabLayout tabLayout, int resId) {
        tabLayout.setTabIconTint(getApplicationContext().getColorStateList(resId));
    }

    private void setupPieChart() {
        pieChart.setDrawRoundedSlices(true);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setEntryLabelColor(Color.TRANSPARENT);
        pieChart.setCenterText("январь");
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

        adapterRvLegends = new AdapterRvLegends(entries, this);

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(new int[] { R.color.purple_500, R.color.yellow, R.color.red, R.color.purple_200, R.color.purple_700, R.color.blue, R.color.black }, getApplicationContext());
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