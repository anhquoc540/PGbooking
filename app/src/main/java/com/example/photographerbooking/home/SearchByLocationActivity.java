package com.example.photographerbooking.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.photographerbooking.MainActivity;
import com.example.photographerbooking.R;
import com.example.photographerbooking.adapter.ServicePackageAdapter2;
import com.example.photographerbooking.data.ServiceData;
import com.example.photographerbooking.model.PhotoService;
import com.example.photographerbooking.util.ExpandableHeightGridView;

import java.util.ArrayList;
import java.util.List;

public class SearchByLocationActivity extends AppCompatActivity implements ServicePackageAdapter2.ListItemClickListener{
    private ServicePackageAdapter2 searchServicesAdapter;
    private ExpandableHeightGridView gvSearchServices;
    private List<PhotoService> listSearchServices = new ArrayList<>();
    private ServiceData dataService = new ServiceData();
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_location);

        gvSearchServices = findViewById(R.id.gvSearchServices);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchByLocationActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        setUpSearchServices();
    }

    private void setUpSearchServices() {
        listSearchServices = dataService.getAllService();

        searchServicesAdapter = new ServicePackageAdapter2(this, listSearchServices, this);
        gvSearchServices.setAdapter(searchServicesAdapter);
        gvSearchServices.setExpanded(true);
    }

    @Override
    public void onMoreServiceClick(int clickedItemIndex) {
        Intent intent = new Intent(this, ServiceDetails.class);
        startActivity(intent);
        finish();
    }
}