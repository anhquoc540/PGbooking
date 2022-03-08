package com.example.photographerbooking.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.example.photographerbooking.MainActivity;
import com.example.photographerbooking.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryFilterAdapter extends RecyclerView.Adapter<CategoryFilterAdapter.ViewHolder>{
    private List<String> listCategoryFilter;
    int selectedPosition;

    public CategoryFilterAdapter( List<String> listCategoryFilter) {
        this.listCategoryFilter = setUpList();
        selectedPosition = 0;
    }

    private List<String> setUpList() {
        List<String> list = new ArrayList<>();
        list.add("Discover");
        list.add("Portrait");
        list.add("Wedding");
        list.add("Baby/Family");
        list.add("Sports");
        list.add("Food");
        list.add("Fashion");
        list.add("Landscape");
        list.add("Wildlife");

        return list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_filter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvCategoryFilter.setText(listCategoryFilter.get(position));
        holder.tvCategoryFilter.setTextColor(Color.GRAY);
        holder.tvCategoryFilter.setTypeface(null, Typeface.NORMAL);
        if (position == selectedPosition) {
            holder.tvCategoryFilter.setTextColor(Color.BLUE);
            holder.tvCategoryFilter.setTypeface(null, Typeface.BOLD);
        }
    }

    @Override
    public int getItemCount() {
        return listCategoryFilter.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvCategoryFilter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategoryFilter = itemView.findViewById(R.id.tvCategoryFilter);

            tvCategoryFilter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedPosition = getAdapterPosition();
                    Log.d("Filter ", selectedPosition + "");
                    notifyDataSetChanged();
                }
            });
        }
    }
}
