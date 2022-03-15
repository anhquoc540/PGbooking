package com.example.photographerbooking.home;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photographerbooking.MainActivity;
import com.example.photographerbooking.R;
import com.example.photographerbooking.adapter.AlbumsAdapter;
import com.example.photographerbooking.adapter.CategoryItemsAdapter2;
import com.example.photographerbooking.adapter.ServicePackageAdapter;
import com.example.photographerbooking.data.CategoryData;
import com.example.photographerbooking.data.PhotographerData;
import com.example.photographerbooking.data.ServiceData;
import com.example.photographerbooking.model.Category;
import com.example.photographerbooking.model.PhotoService;
import com.example.photographerbooking.model.Photographer;

import java.util.ArrayList;

public class PhotographerDetailsActivity extends AppCompatActivity implements ServicePackageAdapter.ListItemClickListener {
    RecyclerView rvServices, rvAlbum;
    ServicePackageAdapter servicesAdapter;
    AlbumsAdapter albumsAdapter;
    ArrayList<PhotoService> listServices = new ArrayList<>();
    ViewFlipper viewFlipper;
    ImageButton btnNext, btnPrevious,btnBack;
    ImageView ivAvatar;
    TextView fullName, address, ratingNumber1, ratingNumber2;
    RatingBar ratingBar1, ratingBar2;
    PhotographerData dataPG = new PhotographerData();
    ServiceData dataService = new ServiceData();
    int idPG;
    Photographer pg;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photographer_details);

        binding();
        setFunctionForButton();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PhotographerDetailsActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        Intent intent = getIntent();
        idPG = intent.getIntExtra("idPG", 0);
        pg = dataPG.getPG(idPG);
        setUpPGInfo(pg);
        setUpServices(pg);
        setUpAlbum();
    }

    private void binding() {
        rvServices = findViewById(R.id.rvServiceList);
        rvAlbum = findViewById(R.id.rvAlbums);
        viewFlipper = findViewById(R.id.viewFlipper);
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnBack = findViewById(R.id.btnBack);

        ivAvatar = findViewById(R.id.ivAvatar);
        fullName = findViewById(R.id.fullName);
        address = findViewById(R.id.address);
        ratingNumber1 = findViewById(R.id.ratingNumber1);
        ratingNumber2 = findViewById(R.id.ratingNumber2);
        ratingBar1 = findViewById(R.id.averageRatingBar1);
        ratingBar2 = findViewById(R.id.averageRatingBar2);
    }

    private void setUpPGInfo(Photographer pg) {
        ivAvatar.setImageResource(pg.getAvatar());
        fullName.setText(pg.getName());
        address.setText(pg.getLocation());
        ratingNumber1.setText(pg.getRating()+"");
        ratingNumber2.setText(pg.getRating()+"");
    }

    private void setUpServices(Photographer pg) {
        for (int i : pg.getServiceIds()) {
            listServices.add(dataService.getService(i));
        }

        servicesAdapter = new ServicePackageAdapter(listServices, this);
        rvServices.setAdapter(servicesAdapter);
        rvServices.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void setUpAlbum() {
        albumsAdapter = new AlbumsAdapter();
        rvAlbum.setAdapter(albumsAdapter);
        rvAlbum.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
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

    @Override
    public void onServiceCardClick(int clickedItemIndex) {
        Intent intent = new Intent(this, ServiceDetails.class);
        intent.putExtra("idPG", idPG);
        startActivity(intent);
        finish();
    }
}
