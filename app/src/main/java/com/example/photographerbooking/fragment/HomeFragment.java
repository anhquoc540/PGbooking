package com.example.photographerbooking.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photographerbooking.R;
import com.example.photographerbooking.adapter.CategoryItemsAdapter;
import com.example.photographerbooking.adapter.PhotographerItemsAdapter;
import com.example.photographerbooking.home.PhotographerDetailsActivity;
import com.example.photographerbooking.model.Category;
import com.example.photographerbooking.model.Photographer;
import com.example.photographerbooking.util.ExpandableHeightGridView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements PhotographerItemsAdapter.ListItemClickListener {
    private RecyclerView rvTopPG, rvBookedPG, rvFollowedPG;
    private ExpandableHeightGridView gvCategory;
    private CategoryItemsAdapter categoryAdapter;
    private PhotographerItemsAdapter topPGAdapter, bookedGPAdapter, followedGPAdapter;
    private List<Category> listCategory = new ArrayList<>();
    private List<Photographer> listTopPG = new ArrayList<>();

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rvTopPG = view.findViewById(R.id.rvTopPG);
        rvBookedPG = view.findViewById(R.id.rvBookedPG);
        rvFollowedPG = view.findViewById(R.id.rvFollowedPG);
        gvCategory = view.findViewById(R.id.gvCategory);

        setUpTopPG();
        setUpBookedPG();
        setUpFollowedPG();
        setUpListCategory();

        categoryAdapter = new CategoryItemsAdapter(this.getContext(), listCategory);
        gvCategory.setAdapter(categoryAdapter);
        gvCategory.setExpanded(true);

        return view;
    }

    private void setUpFollowedPG() {
        listTopPG.add(new Photographer(1, "Amelia Brown", "4459 Wyatt Street, United States", "asdasd@gmail.com", 4.6F, R.drawable.avt_1));
        listTopPG.add(new Photographer(2, "Victoria Israel", "Via del Campidoglio, Rome", "asdasd@gmail.com", 4.5F, R.drawable.avt_2));

        followedGPAdapter = new PhotographerItemsAdapter(listTopPG, this);
        rvFollowedPG.setAdapter(followedGPAdapter);
        rvFollowedPG.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void setUpBookedPG() {
        listTopPG.add(new Photographer(1, "Amelia Brown", "4459 Wyatt Street, United States", "asdasd@gmail.com", 4.6F, R.drawable.avt_1));
        listTopPG.add(new Photographer(2, "Victoria Israel", "Via del Campidoglio, Rome", "asdasd@gmail.com", 4.5F, R.drawable.avt_2));

        bookedGPAdapter = new PhotographerItemsAdapter(listTopPG, this);
        rvBookedPG.setAdapter(bookedGPAdapter);
        rvBookedPG.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void setUpTopPG() {
        listTopPG.add(new Photographer(1, "Amelia Brown", "4459 Wyatt Street, United States", "asdasd@gmail.com", 4.6F, R.drawable.avt_1));
        listTopPG.add(new Photographer(2, "Victoria Israel", "Via del Campidoglio, Rome", "asdasd@gmail.com", 4.5F, R.drawable.avt_2));

        topPGAdapter = new PhotographerItemsAdapter(listTopPG, this);
        rvTopPG.setAdapter(topPGAdapter);
        rvTopPG.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void setUpListCategory() {
        listCategory.add(new Category(1,"Portrait", R.drawable.portrait_1));
        listCategory.add(new Category(2,"Wedding", R.drawable.wedding_1));
        listCategory.add(new Category(3,"Fashion", R.drawable.fashion_1));
        listCategory.add(new Category(4,"Baby/Family", R.drawable.family_1));
        listCategory.add(new Category(5,"Landscape", R.drawable.landscape_1));
        listCategory.add(new Category(6,"Food", R.drawable.food_1));
    }

    @Override
    public void onResume() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        super.onResume();
    }

    @Override
    public void onCardListClick(int clickedItemIndex) {
        Log.d("PG Card", "clicked" + clickedItemIndex);
        Intent intent = new Intent(this.getActivity(), PhotographerDetailsActivity.class);
        startActivity(intent);
        //getActivity().finish();
    }
}