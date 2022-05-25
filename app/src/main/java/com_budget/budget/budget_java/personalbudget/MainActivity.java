package com_budget.budget.budget_java.personalbudget;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

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
    //btn go to addition activity
    private FloatingActionButton addBtn, logOutBtn, graphBtn;
    //TabLayout implementing
    private TabLayout tabsExpensesAndIncomes;
    private ExtendedFloatingActionButton actionBtn;
    // to check whether sub FAB buttons are visible or not.
    private Boolean isAllFabsVisible;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        ViewPager viewPager = findViewById(R.id.viewpagerMonthExpense);
        PagerAdapterMonths viewPagerAdapter = new PagerAdapterMonths(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        tabsExpensesAndIncomes.setupWithViewPager(viewPager);

        setupTabIcons();
        applyColorStateList(tabsExpensesAndIncomes, R.drawable.selector_color_tabs);


        View.OnClickListener BTNsClicks = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.action:
                        if (!isAllFabsVisible) {

                            // when isAllFabsVisible becomes
                            // true make all the action name
                            // texts and FABs VISIBLE.
                            addBtn.show();
                            logOutBtn.show();
                            graphBtn.show();

                            // make the boolean variable true as
                            // we have set the sub FABs
                            // visibility to GONE
                            isAllFabsVisible = true;
                        } else {

                            // when isAllFabsVisible becomes
                            // true make all the action name
                            // texts and FABs GONE.
                            addBtn.hide();
                            logOutBtn.hide();
                            graphBtn.hide();

                            // make the boolean variable false
                            // as we have set the sub FABs
                            // visibility to GONE
                            isAllFabsVisible = false;
                        }
                        break;
                    case R.id.logoutBtn:
                        showBottomSheet(view);
                        break;
                    case R.id.addBtn:
                        startActivity(new Intent(getApplicationContext(), AdditionActivity.class));
                        break;
                    case R.id.graphBtn:
                        startActivity(new Intent(getApplicationContext(), BarChartActivity.class));
                        break;
                }
            }
        };
        actionBtn.setOnClickListener(BTNsClicks);
        addBtn.setOnClickListener(BTNsClicks);
        logOutBtn.setOnClickListener(BTNsClicks);
        graphBtn.setOnClickListener(BTNsClicks);

    }

    private void init() {
        tabsExpensesAndIncomes = findViewById(R.id.tabsExpensesAndIncomes);
        logOutBtn = findViewById(R.id.logoutBtn);
        graphBtn = findViewById(R.id.graphBtn);
        addBtn = findViewById(R.id.addBtn);
        actionBtn = findViewById(R.id.action);

        addBtn.setVisibility(View.GONE);
        logOutBtn.setVisibility(View.GONE);
        graphBtn.setVisibility(View.GONE);
        isAllFabsVisible = false;

    }

    private void setupTabIcons() {
        tabsExpensesAndIncomes.getTabAt(0).setIcon(tabIcons[0]);
        tabsExpensesAndIncomes.getTabAt(1).setIcon(tabIcons[1]);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void applyColorStateList(@NonNull TabLayout tabLayout, int resId) {
        tabLayout.setTabIconTint(getApplicationContext().getColorStateList(resId));
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

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}