package com.example.photographerbooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.photographerbooking.fragment.BookingFragment;
import com.example.photographerbooking.fragment.HomeFragment;
import com.example.photographerbooking.fragment.NotificationFragment;
import com.example.photographerbooking.fragment.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation;
    Toolbar toolbarWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottomNavigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_baseline_calendar_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_baseline_notifications_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(5, R.drawable.ic_baseline_settings_24));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                switch (item.getId()) {
                    case 1:
                        fragment = new HomeFragment();
                        break;
                    case 2:
                        fragment = new BookingFragment();
                        break;
                    case 4:
                        fragment = new NotificationFragment();
                        break;
                    case 5:
                        fragment = new SettingsFragment();
                        break;
                }
                loadFragment(fragment);
            }
        });
        bottomNavigation.show(1, true);
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

            }
        });
        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

            }
        });

        loadToolbar();

    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit();
    }
    private void loadToolbar(){

        toolbarWidget = (Toolbar) findViewById(R.id.myToolBar);

        toolbarWidget.setTitle("Linslus");

        setSupportActionBar(toolbarWidget);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.app_bar_search:
                Intent intent = new Intent(MainActivity.this,service_filter.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}