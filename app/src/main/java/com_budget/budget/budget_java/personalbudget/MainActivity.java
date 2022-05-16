package com_budget.budget.budget_java.personalbudget;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class MainActivity extends AppCompatActivity implements BottomSheetDialogLogOutFragment.ItemClickListener{
    //array for drawables(images) of tabs
    private int[] tabIcons = {
            R.drawable.goes_down_img,
            R.drawable.goes_up_img
    };
    //btn log out
    private ExtendedFloatingActionButton signOutBtn;
    //btn go to addition activity
    private FloatingActionButton addBtn;
    //btn go to bar chart (for annual period)
    private ImageView graphCartBtn;
    //TabLayout implementing
    private TabLayout tabsExpensesAndIncomes;
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
        buildRecyclerViewLegend();

        rvLegend.setAdapter(adapterRvLegends);

        buildRecyclerViewMonth();
        setDataMonths();

        View.OnClickListener BTNsClicks = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.signOutBtn:
                        showBottomSheet(view);
                        break;
                    case R.id.addBtn:
                        startActivity(new Intent(getApplicationContext(), AdditionActivity.class));
                        break;
                    case R.id.graphCartImgBtn:
                        startActivity(new Intent(getApplicationContext(), BarChartActivity.class));
                        break;
                }
            }
        };
        addBtn.setOnClickListener(BTNsClicks);
        signOutBtn.setOnClickListener(BTNsClicks);
        graphCartBtn.setOnClickListener(BTNsClicks);

    }

    private void init() {
        addBtn = findViewById(R.id.addBtn);
        signOutBtn = findViewById(R.id.signOutBtn);
        graphCartBtn = findViewById(R.id.graphCartImgBtn);

        tabsExpensesAndIncomes = findViewById(R.id.tabsExpensesAndIncomes);

        pieChart = findViewById(R.id.chart1);
        rvLegend = findViewById(R.id.rvLegend);
        rvMonths = findViewById(R.id.rvMonths);
    }

    private void buildRecyclerViewLegend() {
        rvLegend.setHasFixedSize(true);
        rvLegend.setLayoutManager(new LinearLayoutManager(this));
    }

    private void buildRecyclerViewMonth() {
        rvMonths.setHasFixedSize(true);
        rvMonths.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
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
        adapterMonths = new AdapterMonths(arrayMonth, getApplicationContext());
        rvMonths.setAdapter(adapterMonths);
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

        adapterRvLegends = new AdapterRvLegends(entries, this);

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(new int[]{R.color.purple_500, R.color.yellow, R.color.red, R.color.purple_200, R.color.purple_700, R.color.blue, R.color.black}, getApplicationContext());
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

    public void showBottomSheet(View view) {
        BottomSheetDialogLogOutFragment addPhotoBottomDialogFragment =
                BottomSheetDialogLogOutFragment.newInstance();
        addPhotoBottomDialogFragment.show(getSupportFragmentManager(),
                BottomSheetDialogLogOutFragment.TAG);
    }

    @Override
    public void onItemClick(String item) {
        startActivity(new Intent(getApplicationContext(), SuggestionWithLoginSysActivity.class));
    }
}