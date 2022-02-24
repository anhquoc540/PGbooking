package com.example.photographerbooking.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.photographerbooking.R;
import com.example.photographerbooking.SpaceItemDecoration;
import com.example.photographerbooking.adapter.CategoryItemsAdapter;
import com.example.photographerbooking.adapter.PhotographerItemsAdapter;
import com.example.photographerbooking.model.Category;
import com.example.photographerbooking.model.Photographer;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private RecyclerView mGvCategories,rVTopPG;
    private CategoryItemsAdapter categoryAdapter;
    private PhotographerItemsAdapter topPGAdapter;
    private List<Category> listCategory;
    private List<Photographer> listTopPG;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onResume() {
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        super.onResume();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        listCategory =  new ArrayList<>();
        listCategory.add(new Category(1,"Wedding", 1, R.drawable.wedding_photo));
        listCategory.add(new Category(2,"Wedding 2", 2, R.drawable.wedding_photo));
        listCategory.add(new Category(3,"Wedding 3 ", 3, R.drawable.wedding_photo));
        listCategory.add(new Category(3,"Wedding 3 ", 3, R.drawable.wedding_photo));
        listCategory.add(new Category(3,"Wedding 3 ", 3, R.drawable.wedding_photo));

        listTopPG = new ArrayList<>();
        listTopPG.add(new Photographer(1,"Quoc","25 Hai Ba Trung","asdasd@gmail.com",4.5F,R.drawable.avatar_small));
        listTopPG.add(new Photographer(1,"Quoc","25 Hai Ba Trung","asdasd@gmail.com",4.5F,R.drawable.avatar_small));
        loadCategories(listCategory);
        loadTopPG(listTopPG);
        super.onViewCreated(view, savedInstanceState);
    }

    private void loadCategories (List<Category> list){
        mGvCategories = getView().findViewById(R.id.rvCategoryList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        categoryAdapter  =  new CategoryItemsAdapter(getContext(),list);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(categoryAdapter != null){
                    switch (categoryAdapter.getItemViewType(position)){
                        case 1:
                            return 1;

                        case 0 :
                            return 2;
                        default:
                            return -1;
                    }
                }else{
                    return -1;
                }
            }
        });
        mGvCategories.setLayoutManager(gridLayoutManager);
        mGvCategories.addItemDecoration(new SpaceItemDecoration(5));
        mGvCategories.setAdapter(categoryAdapter);
    }

    private void loadTopPG(List<Photographer> list){
        rVTopPG = getView().findViewById(R.id.rvTopPGList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        topPGAdapter = new PhotographerItemsAdapter(getContext(),list);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rVTopPG.setLayoutManager(linearLayoutManager);
        rVTopPG.addItemDecoration(new SpaceItemDecoration(5));
        rVTopPG.setAdapter(topPGAdapter);
    }
}