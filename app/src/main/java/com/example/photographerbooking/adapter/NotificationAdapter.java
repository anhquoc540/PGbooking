package com.example.photographerbooking.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photographerbooking.R;
import com.example.photographerbooking.interfaces.ItemOnClickListener;
import com.example.photographerbooking.model.Notification;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>{
    private List<Notification> notificationList;
    private Context context;
    private ItemOnClickListener listener;

    public NotificationAdapter(List<Notification> notificationList, Context context, ItemOnClickListener listener) {
        this.notificationList = notificationList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notification_item,parent,false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Notification notification = notificationList.get(position);
        if(notification != null){
            holder.tvNotificationContent.setText(notification.getContent());
            holder.ivNotificationIcon.setImageResource(R.drawable.avatar_small);
            long betweenYears = ChronoUnit.YEARS.between(notification.getCreateTime(),LocalDateTime.now());
            long betweenMonths = ChronoUnit.MONTHS.between(notification.getCreateTime(),LocalDateTime.now());
            long betweenWeeks = ChronoUnit.WEEKS.between(notification.getCreateTime(),LocalDateTime.now());
            long betweenDays = ChronoUnit.DAYS.between(notification.getCreateTime(),LocalDateTime.now());
            long betweenHours = ChronoUnit.HOURS.between(notification.getCreateTime(),LocalDateTime.now());
            long betweenMinuses = ChronoUnit.MINUTES.between(notification.getCreateTime(),LocalDateTime.now());
            long betweenSeconds = ChronoUnit.SECONDS.between(notification.getCreateTime(),LocalDateTime.now());
            if(betweenYears >= 1 ){
                holder.tvNotificationAge.setText(betweenYears + " years");
                Log.i("Time Units :",betweenYears + " years");
            }else if(betweenMonths >= 1){
                holder.tvNotificationAge.setText(betweenMonths + " months");
                Log.i("Time Units :",betweenMonths + " months");
            }else if(betweenWeeks >= 1){
                holder.tvNotificationAge.setText(betweenWeeks + " weeks");
                Log.i("Time Units :",betweenWeeks + " weeks");

            }else if(betweenDays >= 1){
                holder.tvNotificationAge.setText(betweenDays + " days");
                Log.i("Time Units :",betweenDays + " days");
            }else if(betweenHours >= 1){
                holder.tvNotificationAge.setText(betweenHours + " hours");
                Log.i("Time Units :",betweenMonths + " hours");
            }else if(betweenMinuses >= 1){
                holder.tvNotificationAge.setText(betweenMonths + " minutes");
            }else if(betweenSeconds >= 1){
                holder.tvNotificationAge.setText(betweenSeconds + "second");
            }
        }else {
            Log.i("null", "nukkkkkkkkkkkk");
        }
    }

    @Override
    public int getItemCount() {
        return notificationList == null ? 0 : notificationList.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivNotificationIcon;
        private TextView tvNotificationContent;
        private TextView tvNotificationAge;
        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            ivNotificationIcon= itemView.findViewById(R.id.ivNotificationIcon);
            tvNotificationContent =itemView.findViewById(R.id.tvNotificationContent);
            tvNotificationAge = itemView.findViewById(R.id.tvNotificationAge);
        }
    }
}
