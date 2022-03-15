package com.example.photographerbooking.data;

import android.util.ArrayMap;

import com.example.photographerbooking.R;
import com.example.photographerbooking.model.Photographer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PhotographerData {
    final private Map<Integer, Photographer> mapPG = new ArrayMap<>();

    public PhotographerData() {
        mapPG.put(0, new Photographer(0, "Shavon Lonnie", "9022 Shawnee Rd Nancy, Kentucky", "asdasd@gmail.com", 4.4F, R.drawable.avt_9,new int[]{0,1,2}, R.drawable.family_2));
        mapPG.put(1, new Photographer(1, "Amelia Brown", "4459 Wyatt Street, United States", "asdasd@gmail.com", 4.6F, R.drawable.avt_1, new int[]{3,4}, R.drawable.tradition));
        mapPG.put(2, new Photographer(2, "Victoria Israel", "Via del Campidoglio, Rome", "asdasd@gmail.com", 4.5F, R.drawable.avt_2, new int[]{5,6}, R.drawable.wedding_2));
        mapPG.put(3, new Photographer(3, "Pauline Matt", "Place Vendome, Paris", "asdasd@gmail.com", 4.2F, R.drawable.avt_3,new int[]{0,2,6}, R.drawable.home_1));
        mapPG.put(4, new Photographer(4, "Ron James", "Kew Gardens, London", "asdasd@gmail.com", 4.8F, R.drawable.avt_4,new int[]{4,5}, R.drawable.life_style_1));
        mapPG.put(5, new Photographer(5, "Claud Bobby", "4291 Green Meadows Dr Enon, Ohio", "asdasd@gmail.com", 4.3F, R.drawable.avt_5,new int[]{0}, R.drawable.fashion_2));
        mapPG.put(6, new Photographer(6, "Mickey Carver", "82 Hastings Ave Croton On Hudson, New York", "asdasd@gmail.com", 4.1F, R.drawable.avt_6,new int[]{0,2,3}, R.drawable.product_1));
        mapPG.put(7, new Photographer(7, "Lauren Tiffani", "79 Lindale Ave Williamstown, New Jerse", "asdasd@gmail.com", 4.3F, R.drawable.avt_7,new int[]{1,3}, R.drawable.portrait_2));
        mapPG.put(8, new Photographer(8, "Tedd Richelle", "603 Van Alstyne, Texas", "asdasd@gmail.com", 4.2F, R.drawable.avt_8,new int[]{0,2,3,5,6}, R.drawable.sport_2));
    }

    public Photographer getPG(int id) {
        return mapPG.get(id);
    }

    public List<Photographer> getAllPG() {
        List<Photographer> rs = new ArrayList<>();
        for (int i = 0; i < mapPG.size(); i++) {
            rs.add(mapPG.get(i));
        }
        return rs;
    }

    public int getMapPGSize() {
        return mapPG.size();
    }

}
