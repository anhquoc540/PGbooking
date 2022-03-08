package com.example.photographerbooking.fragment;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.photographerbooking.data.CategoryData;
import com.example.photographerbooking.data.PhotographerData;
import com.example.photographerbooking.home.PhotographerDetailsActivity;
import com.example.photographerbooking.home.ServiceListActivity;
import com.example.photographerbooking.model.Category;
import com.example.photographerbooking.model.Photographer;
import com.example.photographerbooking.util.ExpandableHeightGridView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements PhotographerItemsAdapter.ListItemClickListener, CategoryItemsAdapter.GridItemClickListener {
    private RecyclerView rvTopPG, rvBookedPG, rvFollowedPG;
    private ExpandableHeightGridView gvCategory;
    private CategoryItemsAdapter categoryAdapter;
    private PhotographerItemsAdapter topPGAdapter, bookedGPAdapter, followedGPAdapter;
    private List<Category> listCategory = new ArrayList<>();
    private List<Photographer> listTopPG = new ArrayList<>();
    private List<Photographer> listFollowedPG = new ArrayList<>();
    private List<Photographer> listBookedPG = new ArrayList<>();
    private PhotographerData dataPG = new PhotographerData();
    private CategoryData dataCategory = new CategoryData();

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
        setUpGridCategory();

        return view;
    }

    private void setUpFollowedPG() {
        for (int i = 1; i < dataPG.getMapFollowedPGSize() + 1; i++) {
            listFollowedPG.add(dataPG.getPG("followed",i));
        }

        followedGPAdapter = new PhotographerItemsAdapter(listFollowedPG, this, "followed");
        rvFollowedPG.setAdapter(followedGPAdapter);
        rvFollowedPG.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void setUpBookedPG() {
        for (int i = 1; i < dataPG.getMapBookedPGSize() + 1; i++) {
            listBookedPG.add(dataPG.getPG("booked",i));
        }

        bookedGPAdapter = new PhotographerItemsAdapter(listBookedPG, this, "booked");
        rvBookedPG.setAdapter(bookedGPAdapter);
        rvBookedPG.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void setUpTopPG() {
        for (int i = 1; i < dataPG.getMapTopPGSize() + 1; i++) {
            listTopPG.add(dataPG.getPG("top",i));
        }

        topPGAdapter = new PhotographerItemsAdapter(listTopPG, this, "top");
        rvTopPG.setAdapter(topPGAdapter);
        rvTopPG.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void setUpGridCategory() {
        for (int i = 0; i < dataCategory.getMapCategorySize(); i++) {
            listCategory.add(dataCategory.getCategory(i));
        }

        categoryAdapter = new CategoryItemsAdapter(this.getContext(), listCategory, this);
        gvCategory.setAdapter(categoryAdapter);
        gvCategory.setExpanded(true);
    }

    @Override
    public void onResume() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        super.onResume();
    }

    @Override
    public void onCardListClick(int clickedItemIndex, String key) {
        Intent intent = new Intent(this.getActivity(), PhotographerDetailsActivity.class);
        intent.putExtra("idPG", clickedItemIndex + 1);
        intent.putExtra("keyPG", key);
        startActivity(intent);
        //getActivity().finish();
    }

    @Override
    public void onCardGridClick(int clickedItemIndex) {
        Intent intent = new Intent(this.getActivity(), ServiceListActivity.class);
        intent.putExtra("idCategory", listCategory.get(clickedItemIndex).getId());
        startActivity(intent);
    }
}