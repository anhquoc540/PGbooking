package com.example.photographerbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

public class booking extends AppCompatActivity {
    MaterialButton button;
   TextInputEditText outlinedTextFieldStartTime;
   TextInputEditText outlinedTextFieldEndTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        button = findViewById(R.id.btnCreateBook);
        outlinedTextFieldEndTime = findViewById(R.id.edtEndTime);
        outlinedTextFieldStartTime = findViewById(R.id.edtStartTime);

        outlinedTextFieldStartTime.setOnClickListener((view) ->{
            MaterialTimePicker select_appointment_time = new MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_12H)
                    .setHour(12)
                    .setMinute(10)
                    .setTitleText("Select time to begin").setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK).build();
            select_appointment_time.addOnPositiveButtonClickListener(view1 -> {
                int hour = select_appointment_time.getHour();
                int minute = select_appointment_time.getMinute();
                outlinedTextFieldStartTime.setText(hour + ":" + minute + "");
            });
            select_appointment_time.show(getSupportFragmentManager(),"");
        });

        outlinedTextFieldEndTime.setOnClickListener((view) ->{
            MaterialTimePicker select_appointment_time = new MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_12H)
                    .setHour(12)
                    .setMinute(10)
                    .setTitleText("Select time on finish").setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK).build();
            select_appointment_time.addOnPositiveButtonClickListener(view1 -> {
                int hour = select_appointment_time.getHour();
                int minute = select_appointment_time.getMinute();
                outlinedTextFieldEndTime.setText(hour + ":" + minute + "");
            });
            select_appointment_time.show(getSupportFragmentManager(),"");
        });



        button.setOnClickListener((view -> {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
        }));
    }
}