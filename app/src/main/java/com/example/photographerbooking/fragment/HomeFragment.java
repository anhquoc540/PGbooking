package com.example.photographerbooking.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photographerbooking.R;
import com.example.photographerbooking.SpaceItemDecoration;
import com.example.photographerbooking.adapter.CategoryItemsAdapter;
import com.example.photographerbooking.adapter.PhotographerItemsAdapter;
import com.example.photographerbooking.home.PhotographerDetailsActivity;
import com.example.photographerbooking.model.Category;
import com.example.photographerbooking.model.Photographer;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment  implements PhotographerItemsAdapter.ListItemClickListener{
    private RecyclerView mGvCategories,rVTopPG;
    private CategoryItemsAdapter categoryAdapter;
    private RecyclerView.Adapter topPGAdapter;
    private List<Category> listCategory;
    private List<Photographer> listTopPG;
    private Button btn;

    public HomeFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btn = view.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeFragment.this.getActivity(), PhotographerDetailsActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
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
        //super.onViewCreated(view, savedInstanceState);
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
        topPGAdapter = new PhotographerItemsAdapter(getContext(),list,this);
        rVTopPG.addItemDecoration(new SpaceItemDecoration(5));
        rVTopPG.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        rVTopPG.setAdapter(topPGAdapter);
    }

    @Override
    public void onCardListClick(int clickedItemIndex) {
        Intent intent = new Intent(this.getActivity(), PhotographerDetailsActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}