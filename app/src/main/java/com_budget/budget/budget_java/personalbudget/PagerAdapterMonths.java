package com_budget.budget.budget_java.personalbudget;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapterMonths extends FragmentPagerAdapter {

    public PagerAdapterMonths(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new ExpenseMonthFragment();
        } else if (position == 1) {
            fragment = new IncomesMonthFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Expenses";
        }
        else if (position == 1)
        {
            title = "Incomes";
        }
        return title;
    }

}
