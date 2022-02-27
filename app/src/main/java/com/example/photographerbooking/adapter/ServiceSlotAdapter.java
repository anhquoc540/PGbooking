package com.example.photographerbooking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photographerbooking.R;
import com.example.photographerbooking.interfaces.ItemOnClickListener;
import com.example.photographerbooking.model.ServiceSlot;
import com.google.android.material.chip.Chip;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ServiceSlotAdapter extends RecyclerView.Adapter<ServiceSlotAdapter.SlotViewHolder> {
    Context context;
    private List<ServiceSlot> serviceSlotList;
    ItemOnClickListener listener;

    public ServiceSlotAdapter(Context context, List<ServiceSlot> serviceSlotList) {
        this.context = context;
        this.serviceSlotList = serviceSlotList;
    }

    public ServiceSlotAdapter(Context context, List<ServiceSlot> serviceSlotList, ItemOnClickListener listener) {
        this.context = context;
        this.serviceSlotList = serviceSlotList;
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
        String formatStrSlot;
        ServiceSlot slot = serviceSlotList.get(position);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");
        formatStrSlot = slot.getFrom().format(formatter) + " - " + slot.getTo().format(formatter);
        holder.chip.setText(formatStrSlot);
        holder.chip.setOnClickListener((view -> {
            listener.OnItemClicked(slot.getSlotOrdinary() + "");
        }));
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
