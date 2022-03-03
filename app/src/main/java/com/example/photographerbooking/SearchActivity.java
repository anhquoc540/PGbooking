package com.example.photographerbooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photographerbooking.adapter.ResultAdapter;
import com.example.photographerbooking.data.PhotographerData;
import com.example.photographerbooking.model.Photographer;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    RecyclerView recyclerView ;
    ResultAdapter resultAdapter;
    List<Photographer> list;
    Toolbar myToolbar;
    PhotographerData dataPG= new PhotographerData();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        myToolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Search");

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        list = new ArrayList<>();
        for (int i = 1; i < dataPG.getMapTopPGSize(); i++) {
            list.add(dataPG.getPG("top", i));
        }

        recyclerView = findViewById(R.id.rvResultList1);
        resultAdapter = new ResultAdapter(this,list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(new SpaceItemDecoration(10));
        recyclerView.addItemDecoration(dividerItemDecoration);
        for (int i = 0; i < recyclerView.getItemDecorationCount(); i++) {
            if (recyclerView.getItemDecorationAt(i) instanceof DividerItemDecoration)
                recyclerView.removeItemDecorationAt(i);
        }
        recyclerView.setAdapter(resultAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        MenuItem item = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query)
            {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                resultAdapter.getFilter().filter(newText.toString());
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }
}
