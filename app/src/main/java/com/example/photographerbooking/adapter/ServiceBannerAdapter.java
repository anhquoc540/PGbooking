package com.example.photographerbooking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.photographerbooking.R;

import java.util.List;

public class ServiceBannerAdapter extends RecyclerView.Adapter<ServiceBannerAdapter.ServiceBannerViewHolder> {
    private Context context;
    private List<String> urls;

    public ServiceBannerAdapter(Context context, List<String> urls) {
        this.context = context;
        this.urls = urls;
    }

    @NonNull
    @Override
    public ServiceBannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.service_banner, parent, false);
        return new ServiceBannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceBannerViewHolder holder, int position) {
        String url = urls.get(position % urls.size());
        Glide.with(context).load(url).centerCrop().into(holder.banner);
    }

    @Override
    public int getItemCount() {
        return urls == null ? 0 : urls.size();
    }

    public class ServiceBannerViewHolder extends RecyclerView.ViewHolder{
        ImageView banner;
        public ServiceBannerViewHolder(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }
}
