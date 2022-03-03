package com.example.photographerbooking.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photographerbooking.R;
import com.example.photographerbooking.model.Category;

import java.util.List;

public class CategoryItemsAdapter2 extends RecyclerView.Adapter<CategoryItemsAdapter2.ItemViewHolder>{
    final private ListItemClickListener mOnClickListener;
    private List<Category> listCategory;

    public CategoryItemsAdapter2(List<Category> listCategory, ListItemClickListener mOnClickListener) {
        this.listCategory = listCategory;
        this.mOnClickListener = mOnClickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Category category = listCategory.get(position);
        holder.ivCategory.setImageResource(category.getImage());
        holder.tvCategoryLabel.setText(String.valueOf(category.getLabel()));
    }

    @Override
    public int getItemCount() {
        return listCategory.size();
    }

    public interface ListItemClickListener {
        void onCardListClick(int clickedItemIndex);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvCategoryLabel;
        ImageView ivCategory;
        ImageButton btnMoreDetail;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCategory = itemView.findViewById(R.id.ivCategory);
            tvCategoryLabel = itemView.findViewById(R.id.tvCategoryLabel);
            btnMoreDetail = itemView.findViewById(R.id.btnMoreDetail);

            btnMoreDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int clickedPosition = getAdapterPosition();
                    mOnClickListener.onCardListClick(clickedPosition);
                }
            });
        }
    }
}
