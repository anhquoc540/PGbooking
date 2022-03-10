package com.example.photographerbooking.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.photographerbooking.R;
import com.example.photographerbooking.interfaces.ItemOnCheckListener;
import com.example.photographerbooking.interfaces.ItemOnClickListener;
import com.example.photographerbooking.model.Category;
import com.google.android.material.checkbox.MaterialCheckBox;

import java.util.List;

public class CategorySelectionAdapter extends RecyclerView.Adapter<CategorySelectionAdapter.CategoryItemViewHolder> {
    private Context context;
    private List<Category> categories;
    private ItemOnCheckListener<Integer> listener;

    public CategorySelectionAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_suggesstion, parent, false);
        return new CategoryItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CategoryItemViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.tvCategoryLabel.setText(categories.get(position).getLabel());
        holder.banner.setImageResource(categories.get(position).getImage());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.checkBox.isChecked()){
                    listener.onChecked(category.getId());
                }else{
                    listener.onUnchecked(category.getId());
                }
                Log.w("Click on check box","at " + category.getLabel());
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories == null ? 0 : categories.size();
    }


    public void setListener(ItemOnCheckListener listener) {
        this.listener = listener;
    }

    public class CategoryItemViewHolder extends RecyclerView.ViewHolder{
        ImageView banner;
        TextView tvCategoryLabel;
        MaterialCheckBox checkBox;
        public CategoryItemViewHolder(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
            tvCategoryLabel = itemView.findViewById(R.id.tvCategoryLabel);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
