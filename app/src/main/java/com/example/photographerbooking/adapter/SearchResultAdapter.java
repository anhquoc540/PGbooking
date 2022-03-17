package com.example.photographerbooking.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photographerbooking.R;
import com.example.photographerbooking.data.PhotographerData;
import com.example.photographerbooking.home.ServiceDetails;
import com.example.photographerbooking.model.Booking;
import com.example.photographerbooking.model.PhotoService;
import com.example.photographerbooking.model.Photographer;
import com.example.photographerbooking.util.Utils;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ResultViewHolder>{
    private Context context;
    private List<PhotoService> serviceList;

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ResultViewHolder(LayoutInflater.from(context).inflate(R.layout.item_service_result,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        PhotoService photoService = serviceList.get(position);
        ImageView ivBanner;
        TextView tvServiceName, tvPhotographerName,tvServicePrice;
        ivBanner = holder.ivBanner;
        tvServiceName = holder.tvServiceName;
        tvPhotographerName= holder.tvPhotographerName;
        tvServicePrice = holder.tvServicePrice;
        ivBanner.setImageResource(photoService.getRepresentativeImg());
        tvServiceName.setText(photoService.getName());
        Photographer photographer = new PhotographerData().getPG(photoService.getIdPG());
        tvPhotographerName.setText(photographer.getName());

        SpannableString serviceName = new SpannableString("Service: " +  photoService.getName());
        serviceName.setSpan(new StyleSpan(Typeface.BOLD), 0 , "Service: ".length(),Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tvServiceName.setText(serviceName);

        SpannableString servicePrice = new SpannableString("Price: " + photoService.getPrice());
        servicePrice.setSpan(new StyleSpan(Typeface.BOLD), 0 , "Price: ".length(),Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tvServicePrice.setText(servicePrice);

        SpannableString photographerName = new SpannableString("Photographer: " + photographer.getName());
        photographerName.setSpan(new StyleSpan(Typeface.BOLD), 0 , "Photographer: ".length(),Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tvPhotographerName.setText(servicePrice);

        holder.holder.setOnClickListener(view -> {
            Intent intent = new Intent(context, ServiceDetails.class);
            intent.putExtra("serviceId", photoService.getId());
        });

    }

    @Override
    public int getItemCount() {
        return serviceList == null ? 0 : serviceList.size();
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder{
        ImageView ivBanner;
        TextView tvServiceName, tvPhotographerName,tvServicePrice;
        MaterialCardView holder;
        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            ivBanner = itemView.findViewById(R.id.banner);
            tvServiceName = itemView.findViewById(R.id.tvServiceName);
            tvPhotographerName = itemView.findViewById(R.id.tvPhotographerName);
            tvServicePrice = itemView.findViewById(R.id.tvPhotographerName);
            holder = itemView.findViewById(R.id.holder);
        }
    }
}
