package com.example.photographerbooking.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.photographerbooking.fragment.stepview.BookFragmentStep1;
import com.example.photographerbooking.fragment.stepview.BookFragmentStep2;
import com.example.photographerbooking.fragment.stepview.BookFragmentStep3;
import com.example.photographerbooking.fragment.stepview.BookFragmentStep4;

public class StepViewPageAdapter extends FragmentPagerAdapter {


    public StepViewPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 1:
                return new BookFragmentStep2();
            case 2:
                return new BookFragmentStep3();
            case 3:
                return new BookFragmentStep4();
            default:
                return new BookFragmentStep1();
        }
    }

    @Override
    public int getCount() {
        return 4 ;
    }
}
