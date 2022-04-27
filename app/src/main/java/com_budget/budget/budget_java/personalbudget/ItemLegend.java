package com_budget.budget.budget_java.personalbudget;

import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class ItemLegend {
    private String namingLegend;
    private ArrayList<PieEntry> entries;

    public ItemLegend(String namingLegend, ArrayList<PieEntry> entries) {
        this.namingLegend = namingLegend;
        this.entries = entries;
    }

    public ArrayList<PieEntry> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<PieEntry> entries) {
        this.entries = entries;
    }

    public String getNamingLegend() {
        return namingLegend;
    }

    public void setNamingLegend(String namingLegend) {
        this.namingLegend = namingLegend;
    }
}
