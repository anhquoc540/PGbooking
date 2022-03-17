package com.example.photographerbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.photographerbooking.enums.BookStatus;
import com.example.photographerbooking.interfaces.IBookingAction;
import com.example.photographerbooking.model.Book;
import com.example.photographerbooking.model.Booking;
import com.example.photographerbooking.model.Customer;
import com.example.photographerbooking.model.PhotoService;
import com.example.photographerbooking.util.Utilities;
import com.example.photographerbooking.util.Utils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class BookingDetails extends AppCompatActivity implements IBookingAction {
    Book book;
    MaterialButton btnAccept;
    MaterialButton btnReject, btnCancel;
    TextView tvStatus;
    TextView captureLocation;
    TextView checkInDateTime;
    TextView checkInPrice;
    TextView checkOutDateTime;
    TextView serviceName;
    TextView booker;
    TextView deliveryFormatOptions, deliveryLocation, deliveryDate;
    MaterialCardView action_bar;
    View footerSpace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);
        init();
    }
    private void init() {
        MaterialCardView cardView = findViewById(R.id.cardUseAvatar);
        cardView.setCardBackgroundColor(Color.TRANSPARENT);
        cardView.setCardElevation(0);
        int id = this.getIntent().getIntExtra("bookId",0);
        book = Utils.getBooking(id);
        Log.w("book_info_id ", book.getId()+"");
        booker = findViewById(R.id.tvBookerName);
        serviceName = findViewById(R.id.tvCheckInServiceValue);
        captureLocation = findViewById(R.id.tvCaptureLocValue);
        checkInDateTime = findViewById(R.id.tvCheckInTimeValues);
        checkOutDateTime = findViewById(R.id.tvCheckOutTimeValues);
        checkInPrice = findViewById(R.id.tvCheckInPriceValues);
        tvStatus = findViewById(R.id.tvBookStatusValue);

        deliveryFormatOptions = findViewById(R.id.tvDeliveryFormatOptions);
        deliveryLocation = findViewById(R.id.tvDeliveryLocation);
        deliveryDate = findViewById(R.id.tvDeliveryDateValue);
        action_bar = findViewById(R.id.action_bar);
        footerSpace = findViewById(R.id.footerSpace);
        btnCancel = findViewById(R.id.btnCancel);
        btnAccept = findViewById(R.id.btnAccept);
        btnReject = findViewById(R.id.btnReject);
        if(book.getStatus().equals(BookStatus.PENDING)){
            btnCancel.setVisibility(View.GONE);
        }else if(book.getStatus().equals(BookStatus.ACCEPTED)){
            btnAccept.setVisibility(View.GONE);
            btnReject.setVisibility(View.GONE);
        }else if(book.getStatus().equals(BookStatus.COMPLETED)){
            btnCancel.setVisibility(View.GONE);
            btnAccept.setVisibility(View.GONE);
            btnReject.setVisibility(View.GONE);
        }
        config();
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    private void config() {
        PhotoService service = book.getBookingService();
        Customer customer = book.getCustomer();
        btnAccept.setOnClickListener(view -> {
            onAccepted(book.getId());
        });
        btnReject.setOnClickListener(view -> {
            onRejected(book.getId());
        });
        List<String> options = new ArrayList<>();
        options.add("\u2022 2x A4 picture\n");
        options.add("\u2022 2x A3 picture\n");
        options.add("\u2022 3x A2 picture\n");
        StringBuilder builder = new StringBuilder();
        options.forEach((option -> {
            builder.append(option);
        }));
        booker.setText(customer.getName());
        serviceName.setText(service.getName());
        tvStatus.setText(book.getStatus().name());
        checkInPrice.setText(book.getPrice() + " USD");
        checkInDateTime.setText(book.getBookingDate().format(DateTimeFormatter.ofPattern("MMM dd,yyyy")) + " " + book.getStartingTime().format(DateTimeFormatter.ofPattern("hh:ss")));
        checkOutDateTime.setText(book.getBookingDate().plusDays(2).format(DateTimeFormatter.ofPattern("MMM dd,yyyy")));
        captureLocation.setText(book.getLocation());
        deliveryLocation.setText(book.getDeliveryLocation());
        deliveryDate.setText(book.getDeliveryDate().format(DateTimeFormatter.ofPattern("MMM dd, yyyy")));
        deliveryFormatOptions.setText(builder.toString());
        if(book.getStatus().equals(BookStatus.ACCEPTED)){
            btnCancel.setVisibility(View.VISIBLE);
            btnCancel.setOnClickListener(view -> {openDialog();});
        }else{

        }
    }

    @Override
    public void onDetailed(Object id) {
    }

    @Override
    public void onAccepted(Object id) {
        book.setStatus(BookStatus.ACCEPTED);
        tvStatus.setText(BookStatus.ACCEPTED.name());
        btnAccept.setVisibility(View.GONE);
        btnReject.setVisibility(View.GONE);
        action_bar.setVisibility(View.GONE);
        footerSpace.setVisibility(View.GONE);
    }

    @Override
    public void onRejected(Object id) {
        book.setStatus(BookStatus.REJECTED);
        tvStatus.setText(BookStatus.REJECTED.name());
        btnAccept.setVisibility(View.GONE);
        btnReject.setVisibility(View.GONE);
    }

    @Override
    public void onCompleted(Object id) {

    }

    public void openDialog(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_cancel);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextInputEditText editText = dialog.findViewById(R.id.etCancelReason);
        MaterialButton btnConfirm = dialog.findViewById(R.id.btnCancelConfirm);
        MaterialButton btnCancel = dialog.findViewById(R.id.btnDismiss);
        btnConfirm.setOnClickListener((view1) ->{
            Pattern pattern = Pattern.compile("[a-zA-Z0-9]+");
            if(!pattern.matcher(editText.getText().toString()).matches()){
                Toast.makeText(this, "Enter reason to continue", Toast.LENGTH_LONG);
                return;
            }else {
                Toast.makeText(this, "Cancel Success",Toast.LENGTH_LONG);
            }
            dialog.dismiss();
        });
        btnCancel.setOnClickListener(view1 -> {
            dialog.dismiss();
        });
        ImageView close = findViewById(R.id.ivClose);
        close.setOnClickListener(view -> {
            dialog.dismiss();
        });
        dialog.show();
    }
}