package com.example.photographerbooking.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.example.photographerbooking.MainActivity;
import com.example.photographerbooking.R;
import com.example.photographerbooking.model.CategoryFilter;

import java.util.ArrayList;
import java.util.List;

public class CategoryFilterAdapter extends RecyclerView.Adapter<CategoryFilterAdapter.ViewHolder>{
    private List<CategoryFilter> listCategoryFilter;
    int selectedPosition;

    public CategoryFilterAdapter() {
        this.listCategoryFilter = setUpList();
        selectedPosition = 0;
    }

    private List<CategoryFilter> setUpList() {
        List<CategoryFilter> list = new ArrayList<>();
        list.add(new CategoryFilter("Discover", R.drawable.ic_discover));
        list.add(new CategoryFilter("Portrait", R.drawable.ic_portrait));
        list.add(new CategoryFilter("Wedding", R.drawable.ic_wedding));
        list.add(new CategoryFilter("Baby/Family", R.drawable.ic_family));
        list.add(new CategoryFilter("Sports", R.drawable.ic_sport));
        list.add(new CategoryFilter("Food", R.drawable.ic_food));
        list.add(new CategoryFilter("Fashion", R.drawable.ic_fashion));
        list.add(new CategoryFilter("More", R.drawable.ic_more));

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
        CategoryFilter dto = listCategoryFilter.get(position);
        holder.tvCategoryFilter.setText(dto.getName());
        holder.ivIcon.setImageResource(dto.getIcon());
    }

    @Override
    public int getItemCount() {
        return listCategoryFilter.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvCategoryFilter;
        ImageView ivIcon;
        CardView cvFilter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategoryFilter = itemView.findViewById(R.id.tvCategoryFilter);
            ivIcon = itemView.findViewById(R.id.ivFilterIcon);
            cvFilter = itemView.findViewById(R.id.cvFilter);

            cvFilter.setOnClickListener(new View.OnClickListener() {
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
