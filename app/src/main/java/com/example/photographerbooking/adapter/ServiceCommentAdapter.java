package com.example.photographerbooking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photographerbooking.R;
import com.example.photographerbooking.model.ServiceComment;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ServiceCommentAdapter extends RecyclerView.Adapter<ServiceCommentAdapter.ServiceCommentViewHolder> {
    Context context;
    List<ServiceComment> serviceCommentList;

    public ServiceCommentAdapter(Context context, List<ServiceComment> serviceCommentList) {
        this.context = context;
        this.serviceCommentList = serviceCommentList;
    }

    @NonNull
    @Override
    public ServiceCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.service_comment_item,parent, false);
        return new ServiceCommentViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceCommentViewHolder holder, int position) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ServiceComment comment = serviceCommentList.get(position);
        holder.ratingBar.setRating((float) comment.getRatePoint());
        holder.tvCommentContent.setText(comment.getContent());
        holder.tvCommentDate.setText(comment.getCreateDate().format(formatter));
        holder.ivIcon.setImageResource(R.drawable.avatar_small);
    }

    @Override
    public int getItemCount() {
        return serviceCommentList == null ? 0 : serviceCommentList.size();
    }

    public class ServiceCommentViewHolder extends RecyclerView.ViewHolder{
        TextView tvUsername;
        TextView tvCommentDate;
        TextView tvCommentContent;
        RatingBar ratingBar;
        ImageView ivIcon;

        public ServiceCommentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tv_username_1);
            tvCommentDate = itemView.findViewById(R.id.tvCommentDate);
            tvCommentContent = itemView.findViewById(R.id.tvCommentContent);
            ivIcon = itemView.findViewById(R.id.service_account_avatar);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }
}
