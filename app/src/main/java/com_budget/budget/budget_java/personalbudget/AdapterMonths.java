package com_budget.budget.budget_java.personalbudget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterMonths extends RecyclerView.Adapter<AdapterMonths.ViewHolderMonths> {

    private ArrayList<ItemMonth> arrayMonths;
    private Context context;
    private static int lastClickedPosition = -1;
    private int selectedItem;

    public AdapterMonths(ArrayList<ItemMonth> arrayMonths, Context context) {
        this.arrayMonths = arrayMonths;
        this.context = context;
        selectedItem = 0;
    }

    @NonNull
    @Override
    public AdapterMonths.ViewHolderMonths onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_month, parent, false);
        return new AdapterMonths.ViewHolderMonths(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMonths.ViewHolderMonths holder, @SuppressLint("RecyclerView") int position) {
        holder.itemView.setTag(arrayMonths.get(position));
        holder.textMonth.setText(arrayMonths.get(position).getTextMonth());

        holder.cardMonth.setCardBackgroundColor(context.getResources().getColor(R.color.white));
        holder.textMonth.setTextColor(context.getResources().getColor(R.color.black));

        if (selectedItem == position) {
            holder.cardMonth.setCardBackgroundColor(context.getResources().getColor(R.color.purple_500));
            holder.textMonth.setTextColor(context.getResources().getColor(R.color.white));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int previousItem = selectedItem;
                selectedItem = position;
                notifyItemChanged(previousItem);
                notifyItemChanged(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayMonths.size();
    }

    public class ViewHolderMonths extends RecyclerView.ViewHolder {
        private TextView textMonth;
        private CardView cardMonth;
        public ViewHolderMonths(@NonNull View itemView) {
            super(itemView);
            textMonth = itemView.findViewById(R.id.textMonth);
            cardMonth = itemView.findViewById(R.id.card_month);
        }
    }
}
