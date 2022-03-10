package com.example.photographerbooking.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photographerbooking.R;
import com.example.photographerbooking.interfaces.ItemOnCheckListener;
import com.example.photographerbooking.interfaces.ItemOnClickListener;
import java.lang.String;

import com.example.photographerbooking.model.Category;
import com.google.android.material.chip.Chip;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class SelectedCategoryAdapter extends RecyclerView.Adapter<SelectedCategoryAdapter.SlotViewHolder> {
    Context context;
    private List<Category> serviceSlotList;
    ItemOnCheckListener<Integer> listener;

    public SelectedCategoryAdapter(Context context, List<Category> serviceSlotList) {
        this.context = context;
        this.serviceSlotList = serviceSlotList;
    }

    public void setListener(ItemOnCheckListener<Integer> listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public SlotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.service_slot, parent, false);
        return new SlotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SlotViewHolder holder, int position) {
        Category category = serviceSlotList.get(position);
        String slot = category.getLabel();
        holder.chip.setText(slot);
        holder.chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onUnchecked(category.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceSlotList == null ? 0 : serviceSlotList.size();
    }

    public class SlotViewHolder extends RecyclerView.ViewHolder {
        Chip chip;

        public SlotViewHolder(@NonNull View itemView) {
            super(itemView);
            chip = itemView.findViewById(R.id.chipServiceSlot);
        }
    }
}
