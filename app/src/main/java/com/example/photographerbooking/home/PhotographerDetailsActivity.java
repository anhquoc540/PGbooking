package com.example.photographerbooking.home;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ViewFlipper;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photographerbooking.MainActivity;
import com.example.photographerbooking.R;
import com.example.photographerbooking.adapter.CategoryItemsAdapter2;
import com.example.photographerbooking.model.Category;

import java.util.ArrayList;

public class PhotographerDetailsActivity extends AppCompatActivity implements CategoryItemsAdapter2.ListItemClickListener{
    RecyclerView serviceCategory;
    CategoryItemsAdapter2 categoryAdapter;
    ArrayList<Category> listCategory = new ArrayList<>();
    ViewFlipper viewFlipper;
    ImageButton btnNext, btnPrevious,btnBack;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photographer_details);

        setServicePackageData();
        binding();
        setUpRecyclerView();
        setFunctionForButton();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PhotographerDetailsActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private void binding() {
        serviceCategory = findViewById(R.id.serviceCategory);
        viewFlipper = findViewById(R.id.viewFlipper);
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnBack = findViewById(R.id.btnBack);
    }

    private void setUpRecyclerView() {
        categoryAdapter = new CategoryItemsAdapter2(listCategory, this);
        serviceCategory.setAdapter(categoryAdapter);
        serviceCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
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
        listCategory.add(new Category(1,"Portrait", R.drawable.img_3));
        listCategory.add(new Category(2,"Wedding", R.drawable.family_1));
        listCategory.add(new Category(3,"Fashion", R.drawable.food_1));
        listCategory.add(new Category(4,"Baby/Family", R.drawable.avt_2));
    }

    @Override
    public void onCardListClick(int clickedItemIndex) {
        Log.d("Image button", "clicked" + clickedItemIndex);
        Intent intent = new Intent(this, ServiceListActivity.class);
        startActivity(intent);
    }
}
