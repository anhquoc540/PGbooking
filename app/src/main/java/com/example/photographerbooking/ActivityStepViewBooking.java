package com.example.photographerbooking;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import com.example.photographerbooking.adapter.StepViewPageAdapter;
import com.example.photographerbooking.home.ServiceDetails;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;
import java.util.List;

public class ActivityStepViewBooking extends AppCompatActivity {
    private Button btnNextStep,btnPrevStep;
    private ImageButton btnBack;
    private ViewPager view_step;
    private StepView stepView;
    private StepViewPageAdapter adapter;
    private LocalBroadcastManager localBroadcastManager;
    private BroadcastReceiver buttonNextReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int step = intent.getIntExtra( Common.KEY_STEP, 0);
            if(step == 1){
                Common.dataPlace = intent.getStringExtra(Common.KEY_PLACE);

            }else if(step == 2){
                Common.book_slot = intent.getStringArrayListExtra(Common.BOOK_SLOT);
            }else if (step == 3){
                Common.dataDeliveryAddress = intent.getStringExtra(Common.KEY_DELIVERY_ADDRESS);
                Common.email = intent.getStringExtra(Common.KEY_EMAIL);

            }
            btnNextStep.setEnabled(true);
            setColorButton();
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_bbooking_process_stepview);
        super.onCreate(savedInstanceState);
        bindings();
        setupListStep();
        adapter =  new StepViewPageAdapter(getSupportFragmentManager());
        view_step.setOffscreenPageLimit(4);
        view_step.setAdapter(adapter);
        view_step.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                stepView.go(position,true);
                if(position == 0){
                    btnPrevStep.setEnabled(false);

                }else{
                    btnPrevStep.setEnabled(true);
                }

                if(position == 3){
                    Intent intent = new Intent(Common.KEY_CONFIRM);
                    localBroadcastManager.sendBroadcast(intent);
                    btnNextStep.setText("Done");
                    btnNextStep.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(ActivityStepViewBooking.this, MainActivity.class);
                            intent.putExtra("user_booked","Booking Successful");
                            startActivity(intent);
                            Common.step = 0;
                            finish();
                        }
                    });

                }else{
                    btnNextStep.setText("Next");
                    btnNextStep.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(Common.step < 3 || Common.step == 0){
                                Common.step++;
                                view_step.setCurrentItem(Common.step);
                            }
                        }
                    });

                }
                setColorButton();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
        btnPrevStep.setEnabled(false);
        btnNextStep.setEnabled(false);
        setColorButton();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityStepViewBooking.this, ServiceDetails.class);
                startActivity(intent);
                finish();
            }
        });
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(buttonNextReceiver, new IntentFilter(Common.KEY_ENABLE_BUTTON_NEXT));
        btnNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Common.step < 3 || Common.step == 0){
                    Common.step++;
                    view_step.setCurrentItem(Common.step);
                }
            }
        });
        btnPrevStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Common.step == 3 || Common.step > 0){
                    Common.step--;
                    view_step.setCurrentItem(Common.step);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        localBroadcastManager.unregisterReceiver(buttonNextReceiver);
        super.onDestroy();
    }

    private void setupListStep(){
        List<String> list = new ArrayList<>();
        list.add("Location");
        list.add("Time");
        list.add("Delivery");
        list.add("Finally");
        stepView.setSteps(list);
    }
    private void bindings (){
        btnNextStep = findViewById(R.id.btnNextStep);
        btnPrevStep = findViewById(R.id.btnPrevStep);
        view_step = findViewById(R.id.view_step);
        stepView = findViewById(R.id.stepview_bar);
        btnBack = findViewById(R.id.btn_back);
    }
    private void setColorButton(){
        if(btnPrevStep.isEnabled()){
            btnPrevStep.setBackgroundResource(R.color.pink_2);
        }else{
            btnPrevStep.setBackgroundResource(R.color.separate_line);
        }
        if(btnNextStep.isEnabled()){
            btnNextStep.setBackgroundResource(R.color.pink_2);
        }else{
            btnNextStep.setBackgroundResource(R.color.separate_line);
        }

    }

}

