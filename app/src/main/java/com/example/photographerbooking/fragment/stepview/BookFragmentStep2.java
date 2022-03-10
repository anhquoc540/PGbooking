package com.example.photographerbooking.fragment.stepview;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.photographerbooking.Common;
import com.example.photographerbooking.R;

import java.util.Calendar;
import java.util.Locale;

public class BookFragmentStep2 extends Fragment {

    Button dateButton;
    Button timeButton;
    private DatePickerDialog datePickerDialog;
    int hour, minute ;

    private LocalBroadcastManager localBroadcastManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_booking_step_2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            dateButton = getView().findViewById(R.id.datePickerButton);
            timeButton = getView().findViewById(R.id.timeButton);

            dateButton.setText(getTodaysDate());
            dateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    initDatePicker();
                }
            });
            timeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popTimePicker(view);
                }
            });

        super.onViewCreated(view, savedInstanceState);
    }

    private void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                String time = String.format(Locale.getDefault(), "%02d:%02d", hour, minute);
                timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
                String date = dateButton.getText().toString();
                sendDateTimeDataforNextStep(date,time);
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), /*style,*/ onTimeSetListener, hour, minute, true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
    private void sendDateTimeDataforNextStep(String date, String time){

        localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        Intent intent = new Intent(Common.KEY_ENABLE_BUTTON_NEXT);
        intent.putExtra(Common.KEY_STEP, 2);
        intent.putExtra(Common.KEY_TIME,time +"   "+ date);
        localBroadcastManager.sendBroadcast(intent);
    }

        private String getTodaysDate()
        {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            month = month + 1;
            int day = cal.get(Calendar.DAY_OF_MONTH);
            return makeDateString(day, month, year);
        }

        private void initDatePicker()
        {
            DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
            {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day)
                {
                    month = month + 1;
                    String date = makeDateString(day, month, year);
                    String time = timeButton.getText().toString();
                    dateButton.setText(date);
                    sendDateTimeDataforNextStep(date,time);
                }
            };

            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            int style = AlertDialog.THEME_HOLO_LIGHT;

            datePickerDialog = new DatePickerDialog(getContext(), style, dateSetListener, year, month, day);
            datePickerDialog.show();


        }

        private String makeDateString(int day, int month, int year)
        {
            return getMonthFormat(month) + " " + day + " " + year;
        }

        private String getMonthFormat(int month)
        {
            if(month == 1)
                return "JAN";
            if(month == 2)
                return "FEB";
            if(month == 3)
                return "MAR";
            if(month == 4)
                return "APR";
            if(month == 5)
                return "MAY";
            if(month == 6)
                return "JUN";
            if(month == 7)
                return "JUL";
            if(month == 8)
                return "AUG";
            if(month == 9)
                return "SEP";
            if(month == 10)
                return "OCT";
            if(month == 11)
                return "NOV";
            if(month == 12)
                return "DEC";

            //default should never happen
            return "JAN";
        }


}
