package com.example.photographerbooking.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.photographerbooking.ActivityStepViewBooking;
import com.example.photographerbooking.MainActivity;
import com.example.photographerbooking.R;
import com.example.photographerbooking.adapter.CategorySelectionAdapter;
import com.example.photographerbooking.adapter.ServiceCommentAdapter;
import com.example.photographerbooking.adapter.SelectedCategoryAdapter;
import com.example.photographerbooking.interfaces.ItemOnClickListener;
import com.example.photographerbooking.model.PhotoService;
import com.example.photographerbooking.model.ServiceComment;
import com.example.photographerbooking.model.ServiceSlot;
import com.example.photographerbooking.util.Utilities;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class ServiceDetails extends AppCompatActivity {
    RecyclerView rvServiceComment;
    CarouselView rvServiceBanner;
    LinearLayoutManager commentLayoutManager;
    PhotoService photoService;
    List<String> bannerUrls;
    List<ServiceComment> serviceCommentList;
    SelectedCategoryAdapter slotAdapter;
    ServiceCommentAdapter commentAdapter;
    Intent intent;
    TextView tvOriginalPrice, tvDiscountPrice;
    MaterialButton btnCreateBook;
    Toolbar toolbar;
    ImageButton btnBack;


    private static Calendar getClearedUtc() {
        Calendar utc = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        utc.clear();
        return utc;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);
        binding();
    }

    protected void binding(){
        intent = getIntent();
        btnBack = findViewById(R.id.btn_back);
        rvServiceBanner = findViewById(R.id.rvServiceBanner);
        toolbar = findViewById(R.id.myToolBar);
        commentLayoutManager = new LinearLayoutManager(this);
        bannerUrls = new ArrayList<>();
        serviceCommentList = new ArrayList<>();
        photoService = new PhotoService();
        rvServiceComment = findViewById(R.id.rvServiceComment);
        tvDiscountPrice = findViewById(R.id.tvDiscountPrice);
        tvOriginalPrice = findViewById(R.id.tvOriginalPrice);
        SpannableString string = new SpannableString("80");
        string.setSpan(new StrikethroughSpan(),0,string.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvOriginalPrice.setText(string);
        config();
        btnCreateBook = findViewById(R.id.btnCreateBook);

        btnBack.setOnClickListener((view -> {
            Intent intent = new Intent(this.getBaseContext(), MainActivity.class);
            startActivity(intent);
        }));

        btnCreateBook.setOnClickListener((view -> {
            Intent intent = new Intent(this.getBaseContext(), ActivityStepViewBooking.class);
            startActivity(intent);
        }));
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    void config(){
        photoService.setBannerUrls(bannerUrls);
        photoService.setId(1l);
        photoService.setName("Wedding Service Event Photo");
        photoService.setPrice(100.0f);

        bannerUrls = Utilities.getServiceBannerUrl();

        serviceCommentList.add(new ServiceComment("", "Quoc Anh", "Anh dep chat luong", LocalDate.of(2022,1,11),4.5));
        serviceCommentList.add(new ServiceComment("", "Thanh Phong", "Anh mau qua dep", LocalDate.of(2022,2,11),4.5));
        serviceCommentList.add(new ServiceComment("", "Chi Huy", "Anh phoi mau tot", LocalDate.of(2021,3,11),4.4));
        serviceCommentList.add(new ServiceComment("", "Kim Phung", "Anh qua net, qua dep", LocalDate.of(2020,1,11),4.2));
        commentAdapter = new ServiceCommentAdapter(this,serviceCommentList);
        rvServiceComment.setAdapter(commentAdapter);
        rvServiceComment.setNestedScrollingEnabled(false);
        commentLayoutManager.setSmoothScrollbarEnabled(false);
        rvServiceComment.setLayoutManager(commentLayoutManager);

        rvServiceBanner.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                Glide.with(getBaseContext()).load(bannerUrls.get(position)).centerCrop().into(imageView);
            }
        });
       rvServiceBanner.setPageCount(bannerUrls.size());
    }
}