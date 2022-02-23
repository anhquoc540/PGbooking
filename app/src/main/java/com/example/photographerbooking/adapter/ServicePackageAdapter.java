package com.example.photographerbooking.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photographerbooking.R;
import com.example.photographerbooking.dto.ServicePackageDTO;

import java.util.ArrayList;

public class ServicePackageAdapter extends RecyclerView.Adapter<ServicePackageAdapter.ViewHolder> {
    ArrayList<ServicePackageDTO> package_list;

    public ServicePackageAdapter(ArrayList<ServicePackageDTO> packageList) {
        this.package_list = packageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_package_card, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ServicePackageDTO dto = package_list.get(position);
        holder.image.setImageResource(dto.getImage());
        holder.type.setText(dto.getType() + " starting at");
        holder.price.setText("$ " + dto.getPrice());
        holder.unit.setText("/ " + dto.getUnit());
    }

    @Override
    public int getItemCount() {
        return package_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView type, unit, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            image = itemView.findViewById(R.id.service_image);
            type = itemView.findViewById(R.id.service_type);
            price = itemView.findViewById(R.id.service_price);
            unit = itemView.findViewById(R.id.service_unit);
        }
    }
}
