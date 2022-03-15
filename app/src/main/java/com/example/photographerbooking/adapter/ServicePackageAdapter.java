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
import com.example.photographerbooking.data.PhotographerData;
import com.example.photographerbooking.model.PhotoService;
import com.example.photographerbooking.model.Photographer;

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
        PhotographerData dataPG = new PhotographerData();
        PhotoService dto = package_list.get(position);
        Photographer pg = dataPG.getPG(dto.getIdPG());
        holder.serviceImage.setImageResource(dto.getRepresentativeImg());
        holder.serviceName.setText(dto.getName());
        holder.rating.setText(dto.getRating()+"");
        holder.ratingBar.setRating(dto.getRating());
        holder.price.setText("$"+String.format("%.0f", dto.getPrice()));
        holder.ivAvatar.setImageResource(pg.getAvatar());
        holder.pgName.setText(pg.getName());
    }

    @Override
    public int getItemCount() {
        return package_list.size();
    }

    public interface ListItemClickListener {
        void onServiceCardClick(int clickedItemIndex);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView serviceImage, ivAvatar;
        TextView serviceName, rating, price, pgName;
        RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceImage = itemView.findViewById(R.id.serviceImage);
            serviceName = itemView.findViewById(R.id.serviceName);
            rating = itemView.findViewById(R.id.rating);
            price = itemView.findViewById(R.id.price);
            ratingBar = itemView.findViewById(R.id.rbAverageRating);
            pgName = itemView.findViewById(R.id.tvPGName);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onServiceCardClick(clickedPosition);
        }
    }
}