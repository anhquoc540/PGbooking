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
import com.example.photographerbooking.model.ServicePackage;

import java.util.ArrayList;
import java.util.List;

public class ServicePackageAdapter extends RecyclerView.Adapter<ServicePackageAdapter.ViewHolder> {
    final private ListItemClickListener mOnClickListener;
    private List<ServicePackage> package_list;

    public ServicePackageAdapter(List<ServicePackage> packageList, ListItemClickListener mOnClickListener) {
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
        ServicePackage dto = package_list.get(position);
        holder.image.setImageResource(dto.getImage());
//        holder.type.setText(dto.getType() + " starting at");
//        holder.price.setText("$ " + dto.getPrice());
//        holder.unit.setText("/ " + dto.getUnit());
    }

    @Override
    public int getItemCount() {
        return package_list.size();
    }

    public interface ListItemClickListener {
        void onCardListClick(int clickedItemIndex);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView image;
        TextView type, unit, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.serviceImage);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onCardListClick(clickedPosition);
        }
    }
}
