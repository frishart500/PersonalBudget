package com_budget.budget.budget_java.personalbudget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.data.PieEntry;

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
    public void onBindViewHolder(@NonNull ViewHolderLegend holder, @SuppressLint("RecyclerView") int position) {
        holder.namingLegend.setText(arrayLegends.get(position).getLabel());
        holder.moneyLegend.setText(arrayLegends.get(position).getValue() + "$");
        holder.trashBinRemoving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItem(position, holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayLegends.size();
    }

    private void deleteItem(int position, ViewHolderLegend holder) {
        arrayLegends.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, arrayLegends.size());
        holder.itemView.setVisibility(View.GONE);
    }

    public class ViewHolderLegend extends RecyclerView.ViewHolder {

        private TextView namingLegend;
        private TextView moneyLegend;
        private ImageView trashBinRemoving;

        public ViewHolderLegend(@NonNull View itemView) {
            super(itemView);

            namingLegend = itemView.findViewById(R.id.namingLegend);
            moneyLegend = itemView.findViewById(R.id.moneyLegend);
            trashBinRemoving = itemView.findViewById(R.id.delete_img_btn);

        }
    }

}
