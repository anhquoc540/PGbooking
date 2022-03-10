package com.example.photographerbooking.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.photographerbooking.R;
import com.example.photographerbooking.home.ServiceDetails;
import com.example.photographerbooking.model.Category;
import com.example.photographerbooking.model.PhotoService;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemAdapter.SearchItemViewHolder> {
    private Context context;
    private List<PhotoService> serviceList;

    public SearchItemAdapter(Context context, List<PhotoService> serviceList) {
        this.context = context;
        this.serviceList = serviceList;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<PhotoService> filteredList = new ArrayList<>();
            if(charSequence.toString().isEmpty()){
                filteredList.addAll(serviceList);
            }else{
                for (PhotoService service: serviceList) {
                    if(service.getName().toLowerCase().contains(charSequence.toString())){
                        filteredList.add(service);
                    }

                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            serviceList.clear();
            serviceList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

    @NonNull
    @Override
    public SearchItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchItemViewHolder(LayoutInflater.from(context).inflate(R.layout.search_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchItemViewHolder holder, int position) {
        if(serviceList != null &&serviceList.size() > 0){
            PhotoService service = serviceList.get(position % serviceList.size());
            float price = service.getPrice();
            float rating = service.getRating();
            String name = service.getName();
            holder.searchItemRateBar.setRating(rating);
            holder.tvServicePrice.setText("$"+price+" / hour");
            holder.tvServiceName.setText(name);
            CategorySelectionAdapter adapter = new CategorySelectionAdapter(context, Arrays.asList());
            RecyclerView.LayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
//            holder.rvServiceImages.setAdapter(adapter);
//            holder.rvServiceImages.setLayoutManager(manager);
//            holder.rvServiceImages.smoothScrollBy(5, 0);
//            holder.rvServiceImages.addOnScrollListener(new RecyclerView.OnScrollListener() {
//                @Override
//                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                    super.onScrollStateChanged(recyclerView, newState);
//                }
//
//                @Override
//                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                    super.onScrolled(recyclerView, dx, dy);
//                }
//            });
            ImageListener imageListener = (position1, imageView) -> Glide.with(context).load(service.getBannerUrls().get(position1)).centerCrop().into(imageView);
            holder.rvServiceImages.setImageListener(imageListener);
            holder.rvServiceImages.setPageCount(service.getBannerUrls().size());
        }
    }

    @Override
    public int getItemCount() {
        return serviceList != null ? serviceList.size() : 0;
    }



    public Filter getFilter() {
        return filter;

    }

    public class SearchItemViewHolder extends RecyclerView.ViewHolder{
         RatingBar searchItemRateBar;
         TextView tvServicePoint, tvServiceRatingDetail,tvServiceName,tvServicePrice;
        CarouselView rvServiceImages;
        public SearchItemViewHolder(@NonNull View itemView) {
            super(itemView);
            searchItemRateBar = itemView.findViewById(R.id.searchItemRateBar);
            tvServicePoint = itemView.findViewById(R.id.tvServicePoint);
            tvServiceName = itemView.findViewById(R.id.tvServiceName);
            tvServiceRatingDetail = itemView.findViewById(R.id.tvServiceRatingDetail);
            tvServicePrice = itemView.findViewById(R.id.tvServiceItemPrice);
            rvServiceImages = itemView.findViewById(R.id.rvServiceImages);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ServiceDetails.class);
                    context.startActivity(intent);

                }
            });
        }
    }
}
