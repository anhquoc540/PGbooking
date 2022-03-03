package com.example.photographerbooking.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photographerbooking.R;
import com.example.photographerbooking.model.PhotoService;

import java.util.List;

public class ServicePackageAdapter extends RecyclerView.Adapter<ServicePackageAdapter.ViewHolder> {
    final private ListItemClickListener mOnClickListener;
    private List<PhotoService> package_list;

    public ServicePackageAdapter(List<PhotoService> packageList, ListItemClickListener mOnClickListener) {
        this.package_list = packageList;
        this.mOnClickListener = mOnClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_package_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhotoService dto = package_list.get(position);
        holder.serviceImage.setImageResource(dto.getRepresentativeImg());
        holder.serviceName.setText(dto.getName());
        holder.bundleLabel.setText(dto.getBundleLabel());
        holder.rating.setText(dto.getRating()+"");
        holder.ratingBar.setRating(dto.getRating());
    }

    @Override
    public int getItemCount() {
        return package_list.size();
    }

    public interface ListItemClickListener {
        void onCardListClick(int clickedItemIndex);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView serviceImage;
        TextView serviceName, bundleLabel, rating, price;
        RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceImage = itemView.findViewById(R.id.serviceImage);
            serviceName = itemView.findViewById(R.id.serviceName);
            bundleLabel = itemView.findViewById(R.id.priceLabel);
            rating = itemView.findViewById(R.id.rating);
            price = itemView.findViewById(R.id.price);
            ratingBar = itemView.findViewById(R.id.rbAverageRating);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onCardListClick(clickedPosition);
        }
    }
}
