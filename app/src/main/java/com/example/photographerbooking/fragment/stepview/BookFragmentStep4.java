package com.example.photographerbooking.fragment.stepview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.photographerbooking.Common;
import com.example.photographerbooking.R;

public class BookFragmentStep4 extends Fragment {
    TextView txtPhotographer;
    TextView txtService;
    TextView txtShootingPlace;
    TextView txtTime;
    TextView txtEmail;
    TextView txtDate;
    TextView txtDeliveryAddress;
    private LocalBroadcastManager localBroadcastManager;

    BroadcastReceiver confirmBookingReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            setData();
        }
    };

    private void setData(){
        txtShootingPlace.setText(Common.dataPlace);
        StringBuilder bookingTime = new StringBuilder();
        for (String s : Common.book_slot) {
            bookingTime.append(s).append("\n");
        }
        txtTime.setText(bookingTime.toString());
        txtEmail.setText(Common.email);
        txtDeliveryAddress.setText(Common.dataDeliveryAddress);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        localBroadcastManager.registerReceiver(confirmBookingReceiver, new IntentFilter(Common.KEY_CONFIRM));
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        localBroadcastManager.unregisterReceiver(confirmBookingReceiver);
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Intent intent = new Intent();
        String place = intent.getStringExtra(Common.KEY_PLACE);
        System.out.println(place);
        return inflater.inflate(R.layout.fragment_booking_step_4, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        bindings();
        txtPhotographer.setText("Amanda Waller");
        txtService.setText("Wedding photo");
        //txtShootingPlace.setText(Common.dataPlace.toString());

        super.onViewCreated(view, savedInstanceState);
    }
    private void bindings(){
            txtPhotographer = getView().findViewById(R.id.pg_name);
            txtService = getView().findViewById(R.id.pg_service);
            txtTime = getView().findViewById(R.id.booking_time);
            txtDeliveryAddress =getView().findViewById(R.id.textViewDeliveryAddress);
            txtShootingPlace = getView().findViewById(R.id.booking_place);
            txtEmail =getView().findViewById(R.id.textViewDeliveryEmail);
    }
}
