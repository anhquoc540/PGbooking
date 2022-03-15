package com.example.photographerbooking.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photographerbooking.R;
import com.example.photographerbooking.adapter.CategoryFilterAdapter;
import com.example.photographerbooking.adapter.CategoryItemsAdapter;
import com.example.photographerbooking.adapter.HotDealAdapter;
import com.example.photographerbooking.adapter.PhotographerItemsAdapter;
import com.example.photographerbooking.adapter.ServicePackageAdapter;
import com.example.photographerbooking.adapter.ServicePackageAdapter2;
import com.example.photographerbooking.data.PhotographerData;
import com.example.photographerbooking.data.ServiceData;
import com.example.photographerbooking.home.PhotographerDetailsActivity;
import com.example.photographerbooking.home.ServiceDetails;
import com.example.photographerbooking.model.PhotoService;
import com.example.photographerbooking.model.Photographer;
import com.example.photographerbooking.util.ExpandableHeightGridView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements ServicePackageAdapter.ListItemClickListener,
                ServicePackageAdapter2.ListItemClickListener, HotDealAdapter.ListItemClickListener,
                PhotographerItemsAdapter.ListItemClickListener {
    private RecyclerView rvCategoryFilter, rvTopServices, rvTopPG, rvHotDeals, rvRecommendServices;
    private ExpandableHeightGridView gvMoreServices;;
    private CategoryFilterAdapter categoryFilterAdapter;
    private ServicePackageAdapter topServicesAdapter, recommendServicesAdapter;
    private HotDealAdapter hotDealsAdapter;
    private PhotographerItemsAdapter topPGAdapter;
    private ServicePackageAdapter2 moreServicesAdapter;
    private List<PhotoService> listTopServices = new ArrayList<>();
    private List<Photographer> listTopPG = new ArrayList<>();
    private List<PhotoService> listHotDeals = new ArrayList<>();
    private List<PhotoService> listRecommendServices = new ArrayList<>();
    private List<PhotoService> listMoreServices = new ArrayList<>();
    private ServiceData dataService = new ServiceData();
    private PhotographerData dataPG = new PhotographerData();

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rvCategoryFilter = view.findViewById(R.id.rvCategoryFilter);
        rvTopServices = view.findViewById(R.id.rvTopServices);
        rvHotDeals = view.findViewById(R.id.rvHotDeals);
        rvRecommendServices = view.findViewById(R.id.rvRecommendServices);
        rvTopPG = view.findViewById(R.id.rvTopPG);
        gvMoreServices = view.findViewById(R.id.gvMoreServices);

        setUpCategoryFilter();
        setUpTopServices();
        setUpTopPG();
        setUpRecommendServices();
        setUpHotDeals();
        setUpMoreServices();

        return view;
    }

    private void setUpTopPG() {
        listTopPG = dataPG.getAllPG();
        topPGAdapter = new PhotographerItemsAdapter(listTopPG, this);
        rvTopPG.setAdapter(topPGAdapter);
        rvTopPG.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void setUpCategoryFilter() {
        categoryFilterAdapter = new CategoryFilterAdapter();
        rvCategoryFilter.setAdapter(categoryFilterAdapter);
        rvCategoryFilter.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void setUpTopServices() {
        listTopServices.add(dataService.getService(0));
        listTopServices.add(dataService.getService(1));
        listTopServices.add(dataService.getService(2));

        topServicesAdapter = new ServicePackageAdapter(listTopServices, this);
        rvTopServices.setAdapter(topServicesAdapter);
        rvTopServices.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void setUpHotDeals() {
        listHotDeals.add(dataService.getService(3));
        listHotDeals.add(dataService.getService(4));
        listHotDeals.add(dataService.getService(5));
        listHotDeals.add(dataService.getService(6));

        hotDealsAdapter = new HotDealAdapter(listHotDeals, this);
        rvHotDeals.setAdapter(hotDealsAdapter);
        rvHotDeals.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void setUpRecommendServices() {
        listRecommendServices.add(dataService.getService(10));
        listRecommendServices.add(dataService.getService(8));
        listRecommendServices.add(dataService.getService(7));
        listRecommendServices.add(dataService.getService(9));

        recommendServicesAdapter = new ServicePackageAdapter(listRecommendServices, this);
        rvRecommendServices.setAdapter(recommendServicesAdapter);
        rvRecommendServices.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void setUpMoreServices() {
        listMoreServices = dataService.getAllService();

        moreServicesAdapter = new ServicePackageAdapter2(getContext(), listMoreServices, this);
        gvMoreServices.setAdapter(moreServicesAdapter);
        gvMoreServices.setExpanded(true);
    }

    @Override
    public void onResume() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        super.onResume();
    }

    @Override
    public void onServiceCardClick(int clickedItemIndex) {
        Intent intent = new Intent(this.getActivity(), ServiceDetails.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onHotDealsClick(int clickedItemIndex) {
        Intent intent = new Intent(this.getActivity(), ServiceDetails.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onMoreServiceClick(int clickedItemIndex) {
        Intent intent = new Intent(this.getActivity(), ServiceDetails.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onPGCardClick(int clickedItemIndex) {
        Intent intent = new Intent(this.getActivity(), PhotographerDetailsActivity.class);
        intent.putExtra("idPG", clickedItemIndex);
        startActivity(intent);
        getActivity().finish();
    }
}