package com.example.photographerbooking.fragment.stepview;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photographerbooking.Common;
import com.example.photographerbooking.R;
import com.example.photographerbooking.adapter.SelectedSlotsAdapter;
import com.example.photographerbooking.adapter.SlotAdapter;
import com.example.photographerbooking.interfaces.ItemOnCheckListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BookFragmentStep2 extends Fragment implements SlotAdapter.onItemClickListener{

    private Button dateButton;
    private SlotAdapter slotAdapter;
    private SelectedSlotsAdapter selectedSlotsAdapter;
    private TextView tvWarning;
    private RecyclerView rvSlot, rvSelectedSlots;
    private ImageButton ibIncrease, ibDecrease;
    private EditText etHourAmount;
    private Button btnChooseSlot;
    private int hourAmount = 1;
    private int startedPos;
    private int endedPos;
    String pickedDate;
    private ArrayList<String> selectedSlotList = new ArrayList<>();
    private DatePickerDialog datePickerDialog;
    private LocalBroadcastManager localBroadcastManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_booking_step_2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding();

        ibIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hourAmount = Integer.parseInt(etHourAmount.getText().toString()) +  1;
                etHourAmount.setText(hourAmount + "");
                slotAdapter.setHourAmount(hourAmount);
            }
        });

        ibDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hourAmount = Integer.parseInt(etHourAmount.getText().toString()) -  1;
                etHourAmount.setText(hourAmount + "");
                slotAdapter.setHourAmount(hourAmount);
            }
        });

        dateButton.setText(getTodaysDate());
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDatePicker(dateButton);
            }
        });

        btnChooseSlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmp = tvWarning.getText().toString();
                if (!tmp.equals("So sorry, this time is unavailable")) {
                    selectedSlotList.add(tmp);
                    selectedSlotsAdapter.notifyDataSetChanged();
                } else if (tmp.equals("")) {
                    tvWarning.setText("Please pick a start time for your slot below");
                    tvWarning.setTextColor(Color.BLACK);
                }
            }
        });

        setUpSlotPicker();
        setUpSelectedSlots();

        super.onViewCreated(view, savedInstanceState);
    }

    private void setUpSelectedSlots() {
        selectedSlotsAdapter = new SelectedSlotsAdapter(getContext(),selectedSlotList);
        selectedSlotsAdapter.setListener(new ItemOnCheckListener<Integer>() {
            @Override
            public void onChecked(Integer id) {

            }

            @Override
            public void onUnchecked(Integer pos) {
                Log.d("pos",pos + "");
                String tmp = selectedSlotList.get(pos);
                selectedSlotList.remove(tmp);
                selectedSlotsAdapter.notifyDataSetChanged();
            }
        });
        rvSelectedSlots.setLayoutManager(new GridLayoutManager(getContext(),1));
        rvSelectedSlots.setAdapter(selectedSlotsAdapter);
    }

    private void binding() {
        dateButton = getView().findViewById(R.id.datePickerButton);
        rvSlot = getView().findViewById(R.id.rvSlot);
        tvWarning = getView().findViewById(R.id.tvWarning);
        etHourAmount = getView().findViewById(R.id.etHourAmount);
        ibIncrease = getView().findViewById(R.id.ibIncrease);
        ibDecrease = getView().findViewById(R.id.ibDecrease);
        rvSelectedSlots = getView().findViewById(R.id.rvSelectedSlots);
        btnChooseSlot = getView().findViewById(R.id.btnChooseSlot);
    }

    private void resetTimeBooking() {
        startedPos = 0;
        etHourAmount.setText("1");
        setUpSlotPicker();
    }

    private void setUpSlotPicker() {
        int hourAmount = Integer.parseInt(etHourAmount.getText().toString());
        slotAdapter = new SlotAdapter(this, hourAmount);
        rvSlot.setAdapter(slotAdapter);
        rvSlot.setLayoutManager(new GridLayoutManager(getContext(), 4));
    }

    private void sendDateTimeDataforNextStep() {

        localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        Intent intent = new Intent(Common.KEY_ENABLE_BUTTON_NEXT);
        intent.putExtra(Common.KEY_STEP, 2);
        intent.putStringArrayListExtra(Common.BOOK_SLOT, selectedSlotList);
        localBroadcastManager.sendBroadcast(intent);
    }

    private String makeSlotsString() {
        return pickedDate + " " + transferSlotToTime(startedPos) + " - " + transferSlotToTime(endedPos+1);
    }

    private String transferSlotToTime(int slotPos) {
        String formattedHour = String.format("%02d", slotPos / 6);
        String formattedMinute = String.format("%02d", slotPos % 6 * 10);
        return formattedHour + ":" + formattedMinute;
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        pickedDate = makeDateString(day, month, year);
        return pickedDate;
    }

    private void initDatePicker(Button dateButton) {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                pickedDate = makeDateString(day, month, year);
                dateButton.setText(pickedDate);
                resetTimeBooking();
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

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }


    @Override
    public void onSlotClick(int pos) {
        tvWarning.setText("");
        startedPos = pos;
        endedPos = startedPos + hourAmount*6 - 1;
        if (startedPos >= 36 && startedPos <= 47 || endedPos >= 36 && endedPos <= 47) {
            tvWarning.setText("So sorry, this time is unavailable");
            tvWarning.setTextColor(Color.RED);
        } else {
            tvWarning.setText(makeSlotsString());
            tvWarning.setTextColor(Color.BLACK);
        }
    }
}
