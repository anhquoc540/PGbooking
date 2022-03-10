package com.example.photographerbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.example.photographerbooking.adapter.CategoryFilterAdapter;
import com.example.photographerbooking.adapter.CategorySelectionAdapter;
import com.example.photographerbooking.adapter.SelectedCategoryAdapter;
import com.example.photographerbooking.interfaces.ItemOnCheckListener;
import com.example.photographerbooking.interfaces.ItemOnClickListener;
import com.example.photographerbooking.model.Category;
import com.example.photographerbooking.util.Utilities;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.RangeSlider;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class service_filter extends AppCompatActivity {
    private List<Category> categoryList;
    TextInputEditText etCaptureLocation;
    TextInputEditText etMinPrice, etMaxPrice;
    RangeSlider priceSlider;
    RecyclerView rvCategoryFilter;
    List<Category> selectedCategoryIds;
    MaterialButton btnCategoryDialog,btnSearch;
    SelectedCategoryAdapter selectedCategoryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_filter);
        prepare();
        config();
    }

    private void openPopup(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.popup_category);
        RecyclerView rvCategoryFilter = dialog.findViewById(R.id.rvCategoryFilter);
        MaterialButton btnClose = dialog.findViewById(R.id.btnClose);
        rvCategoryFilter.setLayoutManager(new LinearLayoutManager(this));
        CategorySelectionAdapter adapter = new CategorySelectionAdapter(this,categoryList);
        adapter.setListener(new ItemOnCheckListener<Integer>() {
            @Override
            public void onChecked(Integer id) {
                for (int i = 0; i < categoryList.size(); i++) {
                    Category category = categoryList.get(i);
                    if(category.getId() == id){
                        selectedCategoryIds.add(category);
                        selectedCategoryAdapter.notifyDataSetChanged();
                        break;
                    }
                }
            }

            @Override
            public void onUnchecked(Integer id) {
                for (int i = 0; i < categoryList.size(); i++) {
                    Category category = categoryList.get(i);
                    if(category.getId() == id){
                        selectedCategoryIds.remove(category);
                        selectedCategoryAdapter.notifyDataSetChanged();
                        break;
                    }
                }
            }
        });
        rvCategoryFilter.setAdapter(adapter);
        btnClose.setOnClickListener((view -> {
            dialog.dismiss();
        }));
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }

    private void prepare(){
        selectedCategoryIds = new ArrayList<>();
        categoryList = Utilities.getServiceCategory();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,3);
        etCaptureLocation = findViewById(R.id.etCaptureLocation);
        etMinPrice = findViewById(R.id.etMinPrice);
        etMaxPrice = findViewById(R.id.etMaxPrice);
        priceSlider = findViewById(R.id.priceSlider);

        etMinPrice.setText(priceSlider.getValues().get(0)+"");
        etMaxPrice.setText(priceSlider.getValues().get(1)+"");
        btnCategoryDialog = findViewById(R.id.btnCategoryDialog);
        btnCategoryDialog.setOnClickListener((view -> {
            openPopup();
        }));
        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(view -> {
            Intent intent = new Intent(this,SearchActivity.class);
            startActivity(intent);
        });
        selectedCategoryAdapter = new SelectedCategoryAdapter(this,selectedCategoryIds);
        selectedCategoryAdapter.setListener(new ItemOnCheckListener<Integer>() {
            @Override
            public void onChecked(Integer id) {

            }

            @Override
            public void onUnchecked(Integer id) {
                for (int i = 0; i < categoryList.size(); i++) {
                    Category category = categoryList.get(i);
                    if(category.getId() == id){
                        selectedCategoryIds.remove(category);
                        selectedCategoryAdapter.notifyDataSetChanged();
                        break;
                    }
                }
            }
        });
        rvCategoryFilter = findViewById(R.id.rvSelectedCategory);
        rvCategoryFilter.setLayoutManager(layoutManager);
        rvCategoryFilter.setAdapter(selectedCategoryAdapter);
    }

    private void config(){
        priceSlider.addOnChangeListener((slider, value, fromUser) -> {
            etMaxPrice.setText(priceSlider.getValues().get(1)+"");
            etMinPrice.setText(priceSlider.getValues().get(0)+"");
            Log.w("Range Slide", priceSlider.getValueTo() +"");
        });
    }
}