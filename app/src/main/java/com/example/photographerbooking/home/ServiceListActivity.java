package com.example.photographerbooking.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photographerbooking.R;
import com.example.photographerbooking.adapter.ServicePackageAdapter;
import com.example.photographerbooking.data.ServiceData;
import com.example.photographerbooking.model.PhotoService;
import com.example.photographerbooking.model.Photographer;

import java.util.ArrayList;
import java.util.List;

public class ServiceListActivity extends AppCompatActivity implements ServicePackageAdapter.ListItemClickListener{
    private RecyclerView rvServiceList;
    private List<PhotoService> listServicePackage = new ArrayList<>();
    private ServicePackageAdapter servicePackageAdapter;
    private ImageButton btnBack;
    private Photographer pg;
    private ServiceData dataService = new ServiceData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);

        rvServiceList = findViewById(R.id.rvServiceList);
        btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        pg = (Photographer) intent.getSerializableExtra("pg");
        int idCategory = intent.getIntExtra("idCategory",0);
        List<Integer> serviceIds = intent.getIntegerArrayListExtra("serviceIds");

        setUpServicePackage(idCategory, serviceIds);

        servicePackageAdapter = new ServicePackageAdapter(listServicePackage, this);
        rvServiceList.setAdapter(servicePackageAdapter);
        rvServiceList.setLayoutManager(new LinearLayoutManager(this));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServiceListActivity.this, PhotographerDetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                //finish();
            }
        });
    }

    private void setUpServicePackage(int idCategory, List<Integer> serviceIds) {
        for (int i : serviceIds) {
            PhotoService service = dataService.getService(i);
            if (service.getIdCategory() == idCategory)
               listServicePackage.add(service);
        }
    }

    @Override
    public void onCardListClick(int clickedItemIndex) {
        Intent intent = new Intent(this, ServiceDetails.class);
        startActivity(intent);
    }
}