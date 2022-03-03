package com.example.photographerbooking.data;

import android.util.ArrayMap;

import com.example.photographerbooking.R;
import com.example.photographerbooking.model.Category;

import java.util.Map;

public class CategoryData {
    final private Map<Integer, Category> mapCategory = new ArrayMap<>();

    public CategoryData() {
        mapCategory.put(0, new Category(0,"Food", R.drawable.food_1));
        mapCategory.put(1, new Category(1,"Portrait", R.drawable.portrait_1));
        mapCategory.put(2, new Category(2,"Wedding", R.drawable.wedding_1));
        mapCategory.put(3, new Category(3,"Fashion", R.drawable.fashion_1));
        mapCategory.put(4, new Category(4,"Baby/Family", R.drawable.family_1));
        mapCategory.put(5, new Category(5,"Landscape", R.drawable.landscape_1));
    }

    public  int getMapCategorySize() {
        return mapCategory.size();
    }

    public Category getCategory(int id) {
        return mapCategory.get(id);
    }
}
