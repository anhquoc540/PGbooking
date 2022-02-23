package com.example.photographerbooking.home;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ViewFlipper;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photographerbooking.R;
import com.example.photographerbooking.adapter.ServicePackageAdapter;
import com.example.photographerbooking.dto.ServicePackageDTO;

import java.util.ArrayList;

public class PhotographerDetailsActivity extends AppCompatActivity {
    RecyclerView service_package;
    ServicePackageAdapter package_adapter;
    ArrayList<ServicePackageDTO> package_list;
    ViewFlipper view_flipper;
    ImageButton btn_next, btn_previous;

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
        service_package = findViewById(R.id.service_package);
        view_flipper = findViewById(R.id.view_flipper);
        btn_next = findViewById(R.id.btn_next);
        btn_previous = findViewById(R.id.btn_previous);
    }

    private void setUpRecyclerView() {
        package_adapter = new ServicePackageAdapter(package_list);
        service_package.setAdapter(package_adapter);
        service_package.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void setFunctionForButton() {
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view_flipper.setInAnimation(PhotographerDetailsActivity.this, R.anim.flip_in_foreward);
                view_flipper.setOutAnimation(PhotographerDetailsActivity.this, R.anim.flip_out_foreward);
                view_flipper.showNext();
            }
        });

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view_flipper.setInAnimation(PhotographerDetailsActivity.this, R.anim.flip_in_backward);
                view_flipper.setOutAnimation(PhotographerDetailsActivity.this, R.anim.flip_out_backward);
                view_flipper.showPrevious();
            }
        });
    }

    private void setServicePackageData() {
        package_list = new ArrayList<>();
        package_list.add(new ServicePackageDTO(R.drawable.personal_service, "Personal shooting", "per session", "160"));
        package_list.add(new ServicePackageDTO(R.drawable.comercial_service, "Commercial shooting", "per day", "700"));
        package_list.add(new ServicePackageDTO(R.drawable.event_service, "Event shooting", "per hour", "1,200"));
    }
}
