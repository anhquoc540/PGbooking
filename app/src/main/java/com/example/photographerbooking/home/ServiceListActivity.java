package com.example.photographerbooking.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photographerbooking.MainActivity;
import com.example.photographerbooking.R;
import com.example.photographerbooking.adapter.ServicePackageAdapter;
import com.example.photographerbooking.data.ServiceData;
import com.example.photographerbooking.fragment.HomeFragment;
import com.example.photographerbooking.model.PhotoService;
import com.example.photographerbooking.model.Photographer;

import java.util.ArrayList;
import java.util.List;

public class ServiceListActivity extends AppCompatActivity implements ServicePackageAdapter.ListItemClickListener{
    private RecyclerView rvServiceList;
    private List<PhotoService> listServicePackage = new ArrayList<>();
    private ServicePackageAdapter servicePackageAdapter;
    private ImageButton btnBack;
    private int idPG;
    private ServiceData dataService = new ServiceData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);

        rvServiceList = findViewById(R.id.rvServiceList);
        btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        idPG = intent.getIntExtra("idPG",-1);
        int idCategory = intent.getIntExtra("idCategory",0);
        List<Integer> serviceIds = intent.getIntegerArrayListExtra("serviceIds");

        setUpServicePackage(idCategory, serviceIds);

        servicePackageAdapter = new ServicePackageAdapter(listServicePackage, this);
        rvServiceList.setAdapter(servicePackageAdapter);
        rvServiceList.setLayoutManager(new LinearLayoutManager(this));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (idPG != -1) {
                    Intent intent = new Intent(ServiceListActivity.this, PhotographerDetailsActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("idPG", idPG);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(ServiceListActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void setUpServicePackage(int idCategory, List<Integer> serviceIds) {
        if (serviceIds != null) {
            for (int i : serviceIds) {
                PhotoService service = dataService.getService(i);
                if (service.getIdCategory() == idCategory)
                    listServicePackage.add(service);
            }
        } else {
            for (int i = 0; i < dataService.getMapServiceSize(); i++) {
                PhotoService service = dataService.getService(i);
                if (service.getIdCategory() == idCategory)
                    listServicePackage.add(service);
            }
        }
    }

    @Override
    public void onServiceCardClick(int clickedItemIndex) {
        Intent intent = new Intent(this, ServiceDetails.class);
        startActivity(intent);
    }
}