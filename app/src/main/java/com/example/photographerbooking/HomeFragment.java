package com.example.photographerbooking;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.photographerbooking.home.PhotographerDetailsActivity;

public class HomeFragment extends Fragment implements View.OnClickListener {
    View view;
    Button btnDetail;

    public HomeFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        btnDetail = view.findViewById(R.id.btnDetail);
        btnDetail.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        final int btnDetail = R.id.btnDetail;
        if (view.getId() == btnDetail) {
            Intent intent = new Intent(this.getActivity(), PhotographerDetailsActivity.class);
            //intent.putExtra("username",etUsername.getText().toString());
            startActivity(intent);
            this.getActivity().finish();
        }
    }
}