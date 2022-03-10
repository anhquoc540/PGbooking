package com.example.photographerbooking.fragment.stepview;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.photographerbooking.Common;
import com.example.photographerbooking.R;

import java.util.ArrayList;
import java.util.List;

public class BookFragmentStep1 extends Fragment {
    List<String> listOfCity ,listOfTown, listOfWard;
    Spinner dropdown1 ;
    Spinner dropdown2 ;
    Spinner dropdown3 ;
    TextView address;
    Button btnNext;
    String city,ward,town;

    private LocalBroadcastManager localBroadcastManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_booking_step_1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        dropdown1 = getView().findViewById(R.id.spinnerCity);
        dropdown2 = getView().findViewById(R.id.spinnerTown);
         dropdown3 = getView().findViewById(R.id.spinnerWard);
         address = getView().findViewById(R.id.textAddress);
         btnNext = getView().findViewById(R.id.btnNextStep);
//create a list of items for the spinner.
        listOfCity = new ArrayList<>();
        listOfTown = new ArrayList<>();
        listOfWard = new ArrayList<>();
        listOfCity.add("Sai Gon");
        listOfCity.add("Ha Noi");
        listOfCity.add("Da Nang");
        listOfTown.add("District 1");
        listOfTown.add("District 11");
        listOfTown.add("District 10");
        listOfWard.add("Phường Bến Nghé");
        listOfWard.add("Phường Đa Kao");
        listOfWard.add("Phường Bến Thành");
        listOfWard.add("Phường Nguyễn Cư Trinh");
        listOfWard.add(" Phường Cầu Kho");
        listOfWard.add("Phường Nguyễn Thái Bình");
        listOfWard.add("Phường Cầu Ông Lãnh");
        listOfWard.add("Phường Phạm Ngũ Lão");
        listOfWard.add("Phường Cô Giang");
        listOfWard.add("Phường Tân Định");
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapterCity = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, listOfCity);
        ArrayAdapter<String> adapterTown = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, listOfTown);
        ArrayAdapter<String> adapterWard = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, listOfWard);


//set the spinners adapter to the previously created one.
        dropdown1.setAdapter(adapterCity);
        dropdown2.setAdapter(adapterTown);
        dropdown3.setAdapter(adapterWard);
        String city = (String) dropdown1.getSelectedItem();
        String town = (String) dropdown2.getSelectedItem();
        String ward = (String) dropdown3.getSelectedItem();
       address.setOnEditorActionListener(new TextView.OnEditorActionListener() {
           @Override
           public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
               if (i == EditorInfo.IME_ACTION_SEARCH ||
                       i == EditorInfo.IME_ACTION_DONE ||
                       keyEvent != null &&
                               keyEvent.getAction() == KeyEvent.ACTION_DOWN &&
                               keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                   if ( keyEvent == null || ! keyEvent.isShiftPressed()) {
                       // the user is done typing.
                        String addressText = address.getText().toString();
                        if (!TextUtils.isEmpty(addressText)){
                            sendDataforNextStep(addressText);
                        }else{
                            sendNullDataforNextStep();
                        }
                       InputMethodManager in = (InputMethodManager) getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
                       in.hideSoftInputFromWindow(address.getApplicationWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                       return true; // consume.
                   }
               }
               return false; // pass on to other listeners.
           }
       });

    }



    private void sendDataforNextStep(String address){


        localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        Intent intent = new Intent(Common.KEY_ENABLE_BUTTON_NEXT);
        intent.putExtra(address +", "+ city +", "+ town +", "+ ward ,Common.KEY_SERVICE);
        localBroadcastManager.sendBroadcast(intent);

    }
    private void sendNullDataforNextStep(){


        localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        Intent intent = new Intent(Common.KEY_ENABLE_BUTTON_NEXT);
        intent.putExtra("",Common.KEY_SERVICE);
        localBroadcastManager.sendBroadcast(intent);

    }
}
