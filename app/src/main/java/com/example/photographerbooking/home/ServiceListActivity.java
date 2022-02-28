package com.example.photographerbooking.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.photographerbooking.MainActivity;
import com.example.photographerbooking.R;
import com.example.photographerbooking.adapter.ServicePackageAdapter;
import com.example.photographerbooking.model.ServicePackage;

import java.util.ArrayList;
import java.util.List;

public class ServiceListActivity extends AppCompatActivity implements ServicePackageAdapter.ListItemClickListener{
    private RecyclerView rvServiceList;
    private List<ServicePackage> listServicePackage = new ArrayList<>();
    private ServicePackageAdapter servicePackageAdapter;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);

        rvServiceList = findViewById(R.id.rvServiceList);
        btnBack = findViewById(R.id.btnBack);

        setUpServicePackage();

        servicePackageAdapter = new ServicePackageAdapter(listServicePackage, this);
        rvServiceList.setAdapter(servicePackageAdapter);
        rvServiceList.setLayoutManager(new LinearLayoutManager(this));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServiceListActivity.this, PhotographerDetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setUpServicePackage() {
        listServicePackage.add(new ServicePackage(R.drawable.wedding_1, null, null, null));
        listServicePackage.add(new ServicePackage(R.drawable.food_1, null, null, null));
        listServicePackage.add(new ServicePackage(R.drawable.wedding_1, null, null, null));
    }

    @Override
    public void onCardListClick(int clickedItemIndex) {
        Intent intent = new Intent(this, ServiceDetails.class);
        startActivity(intent);
    }
}