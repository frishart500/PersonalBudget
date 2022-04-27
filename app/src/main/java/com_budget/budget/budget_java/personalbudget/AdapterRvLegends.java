package com_budget.budget.budget_java.personalbudget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class AdapterRvLegends extends RecyclerView.Adapter<AdapterRvLegends.ViewHolderLegend> {

    private ArrayList<PieEntry> arrayLegends;
    private Context context;

    public AdapterRvLegends(ArrayList<PieEntry> arrayLegends, Context context) {
        this.arrayLegends = arrayLegends;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderLegend onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_legend, parent, false);
        return new ViewHolderLegend(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLegend holder, int position) {
        holder.namingLegend.setText(arrayLegends.get(position).getLabel());
        //holder.namingLegend.setTextColor(context.getResources().getColor());
        holder.moneyLegend.setText(arrayLegends.get(position).getValue() + "$");
    }

    @Override
    public int getItemCount() {
        return arrayLegends.size();
    }

    public class ViewHolderLegend extends RecyclerView.ViewHolder {

        private TextView namingLegend;
        private TextView moneyLegend;

        public ViewHolderLegend(@NonNull View itemView) {
            super(itemView);

            namingLegend = itemView.findViewById(R.id.namingLegend);
            moneyLegend = itemView.findViewById(R.id.moneyLegend);

        }
    }
}
