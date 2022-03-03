package com.example.photographerbooking.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.photographerbooking.R;
import com.example.photographerbooking.adapter.ServiceBannerAdapter;
import com.example.photographerbooking.adapter.ServiceCommentAdapter;
import com.example.photographerbooking.adapter.ServiceSlotAdapter;
import com.example.photographerbooking.interfaces.ItemOnClickListener;
import com.example.photographerbooking.model.PhotoService;
import com.example.photographerbooking.model.ServiceComment;
import com.example.photographerbooking.model.ServiceSlot;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class ServiceDetails extends AppCompatActivity {
    RecyclerView rvServiceBanner, rvServiceSlot,rvServiceComment;
    LinearLayoutManager linearLayoutManager,commentLayoutManager;
    GridLayoutManager gridLayoutManager;
    PhotoService photoService;
    List<String> bannerUrls;
    List<ServiceSlot> serviceSlotList;
    List<ServiceComment> serviceCommentList;
    ServiceSlotAdapter slotAdapter;
    ServiceBannerAdapter bannerAdapter;
    ServiceCommentAdapter commentAdapter;
    Intent intent;

    Calendar calendar;
    MaterialDatePicker datePicker;
    MaterialDatePicker.Builder builder;
    Timer timer;
    TimerTask task;
    MaterialButton btnDateChoose;
    private int position;


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
        rvServiceBanner = findViewById(R.id.rvServiceBanner);
//        rvServiceSlot = findViewById(R.id.rvServiceSlot);

        linearLayoutManager = new LinearLayoutManager(this);
        commentLayoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this,3);

        bannerUrls = new ArrayList<>();
        serviceSlotList = new ArrayList<>();
        serviceCommentList = new ArrayList<>();

        calendar = getClearedUtc();
        builder = MaterialDatePicker.Builder.datePicker();
//        btnDateChoose = findViewById(R.id.btnDateChoose);
        photoService = new PhotoService();
        rvServiceComment = findViewById(R.id.rvServiceComment);
        config();
    }


    @Override
    protected void onResume() {
        super.onResume();
        runAutoScroll();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopAutoScrollBanner();
    }

    void runAutoScroll(){
        if(timer == null && task == null){
            timer = new Timer();
            task = new TimerTask() {
                @Override
                public void run() {
                    if (position == bannerUrls.size()) {
                        position = 0;
                        rvServiceBanner.smoothScrollToPosition(position);
                        rvServiceBanner.smoothScrollBy(5, 0);
                    } else {
                        rvServiceBanner.smoothScrollToPosition(position);
                        position = position + 1;
                    }
                }
            };
            timer.schedule(task,100,1000);
        }
    }

    private void stopAutoScrollBanner() {
        if (timer != null && task != null) {
            task.cancel();
            timer.cancel();
            timer = null;
            task = null;
            position = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
        }
    }

    void config(){
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvServiceBanner.setLayoutManager(linearLayoutManager);
        rvServiceBanner.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == 1) {
                    stopAutoScrollBanner();
                } else if (newState == 0) {
                    position = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                    runAutoScroll();
                }
            }
        });
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rvServiceBanner);
        rvServiceBanner.smoothScrollBy(5, 0);
//        rvServiceSlot.setLayoutManager(gridLayoutManager);
        photoService.setBannerUrls(bannerUrls);
        photoService.setId(1l);
        photoService.setName("Wedding Service Event Photo");
        photoService.setPrice(100.0f);
        photoService.setBundleLabel("Professional Photo Service for Wedding Service");

        bannerUrls.add("https://cdn.luxstay.com/home/apartment/apartment_1_1578970876.jpg");
        bannerUrls.add("https://cdn.luxstay.com/home/apartment/apartment_2_1578970932.jpg");
        bannerUrls.add("https://cdn.luxstay.com/home/apartment/apartment_3_1578970988.jpg");
        bannerUrls.add("https://cdn.luxstay.com/home/apartment/apartment_4_1578971024.jpg");

        LocalTime slotOneFrom = LocalTime.of(7, 30);
        LocalTime slotOneTo = LocalTime.of(9,30);
        LocalTime slotSecondFrom = LocalTime.of(10,30);
        LocalTime slotSecondTo = LocalTime.of(12,30);
        LocalTime slotThirdFrom =LocalTime.of(13,30);
        LocalTime slotThirdTo =LocalTime.of(15,30);
        LocalTime slotFourthFrom =LocalTime.of(17,30);
        LocalTime slotFourthTo =LocalTime.of(18,30);
        LocalTime slotFifthFrom =LocalTime.of(20,30);
        LocalTime slotFifthTo= LocalTime.of(22,30);
        serviceSlotList.add(new ServiceSlot(slotOneFrom,slotOneTo,photoService,1));
        serviceSlotList.add(new ServiceSlot(slotSecondFrom,slotSecondTo,photoService,2));
        serviceSlotList.add(new ServiceSlot(slotThirdFrom,slotThirdTo,photoService,3));
        serviceSlotList.add(new ServiceSlot(slotFourthFrom,slotFourthTo,photoService,4));
        serviceSlotList.add(new ServiceSlot(slotFifthFrom,slotFifthTo,photoService,5));

        serviceCommentList.add(new ServiceComment("", "Quoc Anh", "Anh dep chat luong", LocalDate.of(2022,1,11),4.5));
        serviceCommentList.add(new ServiceComment("", "Thanh Phong", "Anh mau qua dep", LocalDate.of(2022,2,11),4.5));
        serviceCommentList.add(new ServiceComment("", "Chi Huy", "Anh phoi mau tot", LocalDate.of(2021,3,11),4.4));
        serviceCommentList.add(new ServiceComment("", "Kim Phung", "Anh qua net, qua dep", LocalDate.of(2020,1,11),4.2));

        ItemOnClickListener itemOnClickListener = new ItemOnClickListener() {
            @Override
            public void OnItemClicked(String id) {
                intent.putExtra("selected_slot",id);
                Log.i("select slot", id);
            }
        };
        slotAdapter = new ServiceSlotAdapter(this,serviceSlotList,itemOnClickListener);
        bannerAdapter = new ServiceBannerAdapter(this,bannerUrls);
        commentAdapter = new ServiceCommentAdapter(this,serviceCommentList);

        rvServiceBanner.setAdapter(bannerAdapter);
//        rvServiceSlot.setAdapter(slotAdapter);
        rvServiceComment.setAdapter(commentAdapter);
        rvServiceComment.setNestedScrollingEnabled(false);
        commentLayoutManager.setSmoothScrollbarEnabled(false);
        rvServiceComment.setLayoutManager(commentLayoutManager);



        builder.setTitleText("Select Date");
        CalendarConstraints.Builder constraintBuilder = new CalendarConstraints.Builder();
        constraintBuilder.setStart(MaterialDatePicker.todayInUtcMilliseconds());
        calendar.set(Calendar.MONTH,Calendar.DECEMBER);
        builder.setCalendarConstraints(constraintBuilder.build());
        datePicker = builder.build();
//        datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
//            @Override
//            public void onPositiveButtonClick(Object selection) {
//                btnDateChoose.setText(datePicker.getHeaderText());
//
//            }
//        });
//        btnDateChoose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                datePicker.show(getSupportFragmentManager(),"MATERIAL_DATE_PICKER");
//            }
//        });
    }
}