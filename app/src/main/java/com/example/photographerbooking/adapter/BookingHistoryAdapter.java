package com.example.photographerbooking.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.photographerbooking.R;
import com.example.photographerbooking.model.Booking;
import com.example.photographerbooking.model.BookingStatus;
import com.example.photographerbooking.model.PhotoService;
import com.google.android.material.chip.Chip;

import java.lang.reflect.Array;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class BookingHistoryAdapter extends RecyclerView.Adapter<BookingHistoryAdapter.BookingHistoryViewHolder> {
    private Context context;
    private List<Booking> bookingList;

    public BookingHistoryAdapter(Context context, List<Booking> bookingList) {
        this.context = context;
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public BookingHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.booking_item, parent, false);
        return new BookingHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingHistoryViewHolder holder, int position) {
        Booking booking = bookingList.get(position);
         PhotoService photoService = booking.getBookingService();
         ImageView ivServiceImage;
         TextView tvBookingId, tvBookingServiceName, tvCheckInDate, tvBookingStartTime;
         ivServiceImage = holder.ivServiceImage;
         tvBookingId =holder.tvBookingId;
         tvBookingServiceName = holder.tvBookingServiceName;
         tvCheckInDate = holder.tvCheckInDate;
         tvBookingStartTime= holder.tvBookingStartTime;
         Chip chip = holder.chipBookingStatus;

        ivServiceImage.setImageResource(photoService.getRepresentativeImg());
         SpannableString checkInDate = new SpannableString("Check in date: "+booking.getBookingDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
         checkInDate.setSpan(new StyleSpan(Typeface.BOLD),0,"Check in date:".length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
         tvCheckInDate.setText(checkInDate);

        SpannableString startingTime = new SpannableString("Starting time: "+booking.getStartingTime().format(DateTimeFormatter.ofPattern("hh:mm")));
        startingTime.setSpan(new StyleSpan(Typeface.BOLD),0,"Starting time:".length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tvBookingStartTime.setText(startingTime);

        SpannableString serviceName = new SpannableString("Service: " + photoService.getName());
        serviceName.setSpan(new StyleSpan(Typeface.BOLD),0,"Service:".length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tvBookingServiceName.setText(serviceName);

        SpannableString bookingPrice = new SpannableString("Total price: $" + booking.getPrice());
        bookingPrice.setSpan(new StyleSpan(Typeface.BOLD),0,"Total price:".length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);;
        tvBookingId.setText(bookingPrice);

        chip.setTextColor(Color.WHITE);
         switch (booking.getStatus()){
             case PENDING: {
                 chip.setText("Pending");
                 chip.setChipBackgroundColorResource(R.color.pending_color);
                 break;
             }
             case ACCEPTED: {
                 chip.setText("Accepted");
                 chip.setChipBackgroundColorResource(R.color.accept_color);
                 break;
             }
             case REJECTED: {
                 chip.setText("Rejected");
                 chip.setChipBackgroundColorResource(R.color.reject_color);
                 break;
             }
             case CANCELED: {
                 chip.setText("Canceled");
                 chip.setChipBackgroundColorResource(R.color.cancel_color);
                 break;
             }
             case COMPLETE: {
                 chip.setText("Completed");
                 chip.setChipBackgroundColorResource(R.color.complete_color);
                 break;
             }
             case FINISH: {
                 chip.setText("Finished");
                 chip.setChipBackgroundColorResource(R.color.finish_color);
                 break;
             }
             default:
                 throw new IllegalStateException("Unexpected value: " + booking.getStatus());
         }
    }

    @Override
    public int getItemCount() {
        return bookingList == null ? 0: bookingList.size();
    }

    public class BookingHistoryViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivServiceImage;
        private TextView tvBookingId, tvBookingServiceName, tvCheckInDate, tvBookingStartTime;
        Chip chipBookingStatus;
        public BookingHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ivServiceImage = itemView.findViewById(R.id.ivServiceImage);
            tvBookingId = itemView.findViewById(R.id.tvBookingId);
            tvBookingServiceName = itemView.findViewById(R.id.tvBookingServiceName);
            tvBookingStartTime = itemView.findViewById(R.id.tvBookingStartTime);
            tvCheckInDate = itemView.findViewById(R.id.tvCheckInDate);
            chipBookingStatus = itemView.findViewById(R.id.chipBookingStatus);
        }
    }
}
