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
import com.example.photographerbooking.adapter.CategoryItemsAdapter2;
import com.example.photographerbooking.data.CategoryData;
import com.example.photographerbooking.data.PhotographerData;
import com.example.photographerbooking.data.ServiceData;
import com.example.photographerbooking.model.Category;
import com.example.photographerbooking.model.Photographer;

import java.util.ArrayList;

public class PhotographerDetailsActivity extends AppCompatActivity implements CategoryItemsAdapter2.ListItemClickListener {
    RecyclerView serviceCategory;
    CategoryItemsAdapter2 categoryAdapter;
    ArrayList<Category> listCategory = new ArrayList<>();
    ViewFlipper viewFlipper;
    ImageButton btnNext, btnPrevious,btnBack;

    ImageView ivAvatar;
    TextView fullName, address, followerNumber, ratingNumber1, ratingNumber2;
    RatingBar ratingBar1, ratingBar2;
    PhotographerData dataPG = new PhotographerData();
    ServiceData dataService = new ServiceData();
    CategoryData dataCategory = new CategoryData();
    int idPG;
    String keyPG;
    Photographer pg;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photographer_details);

        //setServicePackageData();
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
        keyPG = intent.getStringExtra("keyPG");

        pg = dataPG.getPG(keyPG, idPG);
        setUpPGInfo(pg);
        setUpCategoryList(pg);
        setUpRecyclerView();

    }

    private void binding() {
        serviceCategory = findViewById(R.id.serviceCategory);
        viewFlipper = findViewById(R.id.viewFlipper);
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnBack = findViewById(R.id.btnBack);

        ivAvatar = findViewById(R.id.ivAvatar);
        fullName = findViewById(R.id.fullName);
        address = findViewById(R.id.address);
        followerNumber = findViewById(R.id.followerNumber);
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

    private void setUpCategoryList(Photographer pg) {
        for (int i : pg.getServiceIds()) {
            int idCategory = dataService.getService(i).getIdCategory();
            Category category = dataCategory.getCategory(idCategory);
            if (!listCategory.contains(category)) {
                listCategory.add(category);
            }
        }
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
        Intent intent = new Intent(PhotographerDetailsActivity.this, ServiceListActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtra("idPG", pg.getId());
        intent.putExtra("idCategory", listCategory.get(clickedItemIndex).getId());
        ArrayList<Integer> serviceIds = (ArrayList<Integer>) pg.getServiceIds();
        intent.putExtra("serviceIds", serviceIds);
        startActivity(intent);
    }
}
