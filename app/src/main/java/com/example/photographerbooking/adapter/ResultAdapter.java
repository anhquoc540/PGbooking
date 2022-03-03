package com.example.photographerbooking.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photographerbooking.R;
import com.example.photographerbooking.home.PhotographerDetailsActivity;
import com.example.photographerbooking.model.Photographer;

import java.util.ArrayList;
import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> implements Filterable {
    private Context context;
    private List<Photographer> listPG;

    public ResultAdapter(Context context, List<Photographer> listPG) {
        this.context = context;
        this.listPG = listPG;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photographer_item, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        Photographer photographer = listPG.get(position);
        holder.pg_avatar.setImageResource(photographer.getAvatar());
        holder.txtRating.setText(String.valueOf(photographer.getRating()));
        holder.txtName.setText(photographer.getName());
        holder.txtLocation.setText(photographer.getLocation());
    }

    @Override
    public int getItemCount() {
        return listPG.size();
    }

    @Override
    public Filter getFilter() {
        return filter;

    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Photographer> filteredList = new ArrayList<>();
            if(charSequence.toString().isEmpty()){
                filteredList.addAll(listPG);
            }else{
                for (Photographer photographer: listPG) {
                    if(photographer.getName().toLowerCase().contains(charSequence.toString())){
                        filteredList.add(photographer);
                    }

                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listPG.clear();
            listPG.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ResultViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtLocation;
        TextView txtRating;
        ImageView pg_avatar;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.fullName);
            pg_avatar = itemView.findViewById(R.id.ivPhotographer);
            txtLocation = itemView.findViewById(R.id.address);
            txtRating = itemView.findViewById(R.id.rating);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PhotographerDetailsActivity.class);
                    context.startActivity(intent);

                }
            });
        }
    }
}