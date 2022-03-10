package com.example.photographerbooking.adapter;

import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photographerbooking.MainActivity;
import com.example.photographerbooking.R;
import com.example.photographerbooking.home.ServiceDetails;
import com.example.photographerbooking.model.PhotoService;

import java.util.List;

public class HotDealAdapter extends RecyclerView.Adapter<HotDealAdapter.ViewHolder>{
    private List<PhotoService> package_list;
    final private ListItemClickListener mOnClickListener;

    public HotDealAdapter(List<PhotoService> packageList,ListItemClickListener mOnClickListener) {
        this.package_list = packageList;
        this.mOnClickListener = mOnClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hot_deal_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhotoService dto = package_list.get(position);
        holder.serviceImage.setImageResource(dto.getRepresentativeImg());
        holder.serviceName.setText(dto.getName());
        holder.rating.setText(dto.getRating()+"");
        holder.ratingBar.setRating(dto.getRating());
        float price = dto.getPrice() * 70 /100;
        holder.price.setText("$"+ String.format("%.0f", price));
        holder.originalPrice.setText("$"+String.format("%.0f", dto.getPrice()));
        holder.originalPrice.setPaintFlags(holder.originalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return package_list.size();
    }

    public interface ListItemClickListener {
        void onHotDealsClick(int clickedItemIndex);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView serviceImage;
        TextView serviceName, rating, price, originalPrice;
        RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            serviceImage = itemView.findViewById(R.id.serviceImage);
            serviceName = itemView.findViewById(R.id.serviceName);
            rating = itemView.findViewById(R.id.rating);
            price = itemView.findViewById(R.id.price);
            originalPrice = itemView.findViewById(R.id.originalPrice);
            ratingBar = itemView.findViewById(R.id.rbAverageRating);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onHotDealsClick(clickedPosition);
        }
    }
}
