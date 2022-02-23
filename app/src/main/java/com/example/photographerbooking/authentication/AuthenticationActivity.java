package com.example.photographerbooking.authentication;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.photographerbooking.R;
import com.example.photographerbooking.helper.SoftKeyBoardHelper;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("AUTHEN ACTIVITY", "STOPPED");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("AUTHEN ACTIVITY", "DESTROYED");
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        tabLayout = findViewById(R.id.authentication_tab_layout);
        viewPager = findViewById(R.id.authentication_view_pager);

        String[] tabTitles = {"Login", "Sign Up"};

        prepareViewPager(viewPager);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(tabTitles[position])
        ).attach();

        for(int i=0; i < tabLayout.getTabCount(); i++) {
            View tab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            p.setMargins(0, 0, 100, 0);
            tab.requestLayout();
        }

        findViewById(R.id.authentication_view).setOnTouchListener((view, motionEvent) -> {
            SoftKeyBoardHelper.hideSoftKeyboard(AuthenticationActivity.this);
            return true;
        });
    }

    private void prepareViewPager(ViewPager2 viewPager) {
        fragmentList.add(new SignInFragment());
        fragmentList.add(new SignUpFragment());

        MainAdapter adapter = new MainAdapter(this,fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(fragmentList.size());
    }

    private class MainAdapter extends FragmentStateAdapter {
        List<Fragment> fragmentList;

        public MainAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> fragmentList) {
            super(fragmentActivity);
            this.fragmentList = fragmentList;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getItemCount() {
            return fragmentList.size();
        }
    }
}