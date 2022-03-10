package com.example.photographerbooking.fragment.stepview;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.photographerbooking.Common;
import com.example.photographerbooking.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookFragmentStep3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookFragmentStep3 extends Fragment {
    List<String> listOfCity ,listOfTown, listOfWard;
    Spinner dropdown1, dropdown2, dropdown3;
    EditText txtDelivery, txtEmail;
    RadioButton rb_digital;
    RadioButton rb_physical;
    RadioGroup rgb_product_type;
    CardView cvEmailDelivery;
    CardView cvAddressDelivery;
    String city,ward,town;
    private LocalBroadcastManager localBroadcastManager;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BookFragmentStep3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookFragmentStep3_1.
     */
    // TODO: Rename and change types and number of parameters
    public static BookFragmentStep3 newInstance(String param1, String param2) {
        BookFragmentStep3 fragment = new BookFragmentStep3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_booking_step_3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindings();
        rgb_product_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                doDeliveryChanged(rgb_product_type,i);
            }
        });

        cvAddressDelivery.setVisibility(View.INVISIBLE);
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
        dropdown1.setAdapter(adapterCity);
        dropdown2.setAdapter(adapterTown);
        dropdown3.setAdapter(adapterWard);

        city = (String) dropdown1.getSelectedItem();
        town = (String) dropdown2.getSelectedItem();
        ward = (String) dropdown3.getSelectedItem();
        txtDelivery.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH ||
                        i == EditorInfo.IME_ACTION_DONE ||
                        keyEvent != null &&
                                keyEvent.getAction() == KeyEvent.ACTION_DOWN &&
                                keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if ( keyEvent == null || ! keyEvent.isShiftPressed()) {
                        // the user is done typing.
                        String addressText = txtDelivery.getText().toString();
                        if (!TextUtils.isEmpty(addressText)){
                            sendDataforNextStep(addressText);
                        }else{
                            sendNullDataforNextStep();
                        }
                        InputMethodManager in = (InputMethodManager) getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
                        in.hideSoftInputFromWindow(txtDelivery.getApplicationWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                        return true; // consume.
                    }
                }
                return false; // pass on to other listeners.
            }
        });
        txtEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH ||
                        i == EditorInfo.IME_ACTION_DONE ||
                        keyEvent != null &&
                                keyEvent.getAction() == KeyEvent.ACTION_DOWN &&
                                keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if ( keyEvent == null || ! keyEvent.isShiftPressed()) {
                        // the user is done typing.
                        String emailText = txtEmail.getText().toString();
                        if (!TextUtils.isEmpty(emailText) && Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
                            sendEmailforNextStep(emailText);
                        }else{
                            sendNullDataforNextStep();
                        }
                        InputMethodManager in = (InputMethodManager) getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
                        in.hideSoftInputFromWindow(txtEmail.getApplicationWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                        return true; // consume.
                    }
                }
                return false; // pass on to other listeners.
            }
        });

    }

    private void sendNullDataforNextStep(){
            localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
            Intent intent = new Intent(Common.KEY_ENABLE_BUTTON_NEXT);
            intent.putExtra(Common.KEY_DELIVERY_ADDRESS, "");
            localBroadcastManager.sendBroadcast(intent);
    }

    private void bindings(){
        rb_digital = getView().findViewById(R.id.rb_digital);
        rb_physical = getView().findViewById(R.id.rb_physical);
        rgb_product_type = getView().findViewById(R.id.rbg_product_type);
        cvAddressDelivery  = getView().findViewById(R.id.cardViewAddress_Delivery);
        cvEmailDelivery = getView().findViewById(R.id.cardViewEmail_Delivery);
        dropdown1 = getView().findViewById(R.id.spinnerCity_Delivery);
        dropdown2 = getView().findViewById(R.id.spinnerTown_Delivery);
        dropdown3 = getView().findViewById(R.id.spinnerWard_Delivery);
        txtEmail = getView().findViewById(R.id.edEmailDelivery);
        txtDelivery = getView().findViewById(R.id.edtAddress);
    }
    private void doDeliveryChanged(RadioGroup group, int checkedId) {
        int checkedRadioId = group.getCheckedRadioButtonId();
        if(checkedRadioId == R.id.rb_digital) {
                cvEmailDelivery.setVisibility(View.VISIBLE);
                cvAddressDelivery.setVisibility(View.INVISIBLE);
        } else if(checkedRadioId== R.id.rb_physical ) {
            cvAddressDelivery.setVisibility(View.VISIBLE);
            cvEmailDelivery.setVisibility(View.INVISIBLE);
        }
    }
    private void sendDataforNextStep(String address){


        localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        Intent intent = new Intent(Common.KEY_ENABLE_BUTTON_NEXT);
        intent.putExtra(Common.KEY_STEP, 3);
        intent.putExtra(Common.KEY_DELIVERY_ADDRESS, address + "," + ward + ", " +town+ ", " + city);
        localBroadcastManager.sendBroadcast(intent);

    }
    private void sendEmailforNextStep(String email){


        localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        Intent intent = new Intent(Common.KEY_ENABLE_BUTTON_NEXT);
        intent.putExtra(Common.KEY_STEP, 3);
        intent.putExtra(Common.KEY_EMAIL, email);
        localBroadcastManager.sendBroadcast(intent);

    }
}