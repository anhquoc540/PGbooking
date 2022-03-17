package com.example.photographerbooking.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photographerbooking.R;

import java.util.ArrayList;
import java.util.List;

public class SlotAdapter extends RecyclerView.Adapter<SlotAdapter.ViewHolder> {
    private final List<String> listSlot;
    private final onItemClickListener mOnItemClickListener;
    private int chosenPos;
    private int hourAmount;

    public interface onItemClickListener {
        void onSlotClick(int pos);
    }

    public SlotAdapter(onItemClickListener mOnItemClickListener, int hourAmount) {
        this.listSlot = setUpSlot();
        this.mOnItemClickListener = mOnItemClickListener;
        this.hourAmount = hourAmount;
    }

    public void setHourAmount(int hourAmount) {
        this.hourAmount = hourAmount;
    }

    private List<String> setUpSlot() {
        List<String> rs = new ArrayList<>();
        for(int hour = 0; hour < 24; hour++) {
            for(int minute = 0; minute < 60; minute+=10) {
                String formattedHour = String.format("%02d", hour);
                String formattedMinute = String.format("%02d", minute);
                rs.add(formattedHour + ":" + formattedMinute);
            }
        }
        return rs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slot_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvSlot.setText(listSlot.get(position));
        if (position >= 36 && position <= 47) {
            holder.tvSlot.setBackgroundResource(R.drawable.unavailable_slot_background);
        } else if (position >= chosenPos && position < (chosenPos + hourAmount*6)){
            holder.tvSlot.setBackgroundResource(R.drawable.chosen_slot_background);
        } else {
            holder.tvSlot.setBackgroundResource(R.drawable.slot_background);
        }
    }

    @Override
    public int getItemCount() {
        return listSlot.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvSlot;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSlot = itemView.findViewById(R.id.tvSlot);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chosenPos = getAdapterPosition();
                    mOnItemClickListener.onSlotClick(chosenPos);
                    notifyDataSetChanged();
                }
            });
        }
    }
}
