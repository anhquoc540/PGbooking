package com.example.photographerbooking.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photographerbooking.R;
import com.example.photographerbooking.model.Photographer;

import java.util.ArrayList;
import java.util.List;

public class PhotographerItemsAdapter extends RecyclerView.Adapter<PhotographerItemsAdapter.ItemViewHolder> {
    final private ListItemClickListener mOnClickListener;
    private List<Photographer> listPG;
    private RatingBar rbAverageRating;
    private String key;

    public PhotographerItemsAdapter(List<Photographer> listPG, ListItemClickListener mOnClickListener, String key) {
        this.listPG = listPG;
        this.mOnClickListener = mOnClickListener;
        this.key = key;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photographer_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Photographer photographer = listPG.get(position);
        holder.ivPhotographer.setImageResource(photographer.getAvatar());
        holder.fullName.setText(photographer.getName());
        holder.address.setText(photographer.getLocation());
        holder.rating.setText(String.valueOf(photographer.getRating()));
        rbAverageRating.setRating(photographer.getRating());
    }

    @Override
    public int getItemCount() {
        return listPG.size();
    }

    public interface ListItemClickListener {
        void onCardListClick(int clickedItemIndex, String key);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView fullName, address, rating;
        ImageView ivPhotographer;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.fullName);
            address = itemView.findViewById(R.id.address);
            ivPhotographer = itemView.findViewById(R.id.ivPhotographer);
            rating = itemView.findViewById(R.id.rating);
            rbAverageRating = itemView.findViewById(R.id.rbAverageRating);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onCardListClick(clickedPosition, key);
        }
    }
}