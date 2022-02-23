package com.example.photographerbooking.home;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ViewFlipper;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photographerbooking.MainActivity;
import com.example.photographerbooking.R;
import com.example.photographerbooking.adapter.ServicePackageAdapter;
import com.example.photographerbooking.model.ServicePackage;

import java.util.ArrayList;

public class PhotographerDetailsActivity extends AppCompatActivity{
    RecyclerView servicePackage;
    ServicePackageAdapter packageAdapter;
    ArrayList<ServicePackage> packageList;
    ViewFlipper viewFlipper;
    ImageButton btnNext, btnPrevious;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photographer_details);

        setServicePackageData();
        binding();
        setUpRecyclerView();
        setFunctionForButton();
    }

    private void binding() {
        servicePackage = findViewById(R.id.servicePackage);
        viewFlipper = findViewById(R.id.viewFlipper);
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);
    }

    private void setUpRecyclerView() {
        packageAdapter = new ServicePackageAdapter(packageList);
        servicePackage.setAdapter(packageAdapter);
        servicePackage.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void setFunctionForButton() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.setInAnimation(PhotographerDetailsActivity.this, R.anim.flip_in_foreward);
                viewFlipper.setOutAnimation(PhotographerDetailsActivity.this, R.anim.flip_out_foreward);
                viewFlipper.showNext();
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.setInAnimation(PhotographerDetailsActivity.this, R.anim.flip_in_backward);
                viewFlipper.setOutAnimation(PhotographerDetailsActivity.this, R.anim.flip_out_backward);
                viewFlipper.showPrevious();
            }
        });
    }

    private void setServicePackageData() {
        packageList = new ArrayList<>();
        packageList.add(new ServicePackage(R.drawable.personal_service, "Personal shooting", "per session", "160"));
        packageList.add(new ServicePackage(R.drawable.comercial_service, "Commercial shooting", "per day", "700"));
        packageList.add(new ServicePackage(R.drawable.event_service, "Event shooting", "per hour", "1,200"));
    }
}
