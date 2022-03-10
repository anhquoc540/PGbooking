package com.example.photographerbooking.fragment.stepview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.photographerbooking.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookFragmentStep3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookFragmentStep3 extends Fragment {

    Spinner dropdown1, dropdown2, dropdown3;
    EditText txtDelivery, txtEmail;
    RadioButton rb_digital;
    RadioButton rb_physical;
    RadioGroup rgb_product_type;
    CardView cvEmailDelivery;
    CardView cvAddressDelivery;
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
}