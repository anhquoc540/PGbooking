package com.example.photographerbooking.data;

import android.util.ArrayMap;

import com.example.photographerbooking.R;
import com.example.photographerbooking.model.Photographer;

import java.util.Map;

public class PhotographerData {
    final private Map<Integer, Photographer> mapTopPG = new ArrayMap<>();
    final private Map<Integer, Photographer> mapFollowedPG = new ArrayMap<>();
    final private Map<Integer, Photographer> mapBookedPG = new ArrayMap<>();

    public PhotographerData() {
        mapTopPG.put(1, new Photographer(1, "Amelia Brown", "4459 Wyatt Street, United States", "asdasd@gmail.com", 4.6F, R.drawable.avt_1, new int[]{0,2,3}));
        mapTopPG.put(2, new Photographer(2, "Victoria Israel", "Via del Campidoglio, Rome", "asdasd@gmail.com", 4.5F, R.drawable.avt_2, new int[]{1,4,5}));
        mapTopPG.put(3, new Photographer(3, "Pauline Matt", "Place Vendome, Paris", "asdasd@gmail.com", 4.2F, R.drawable.avt_3,new int[]{0,2,6}));
        mapTopPG.put(4, new Photographer(4, "Ron James", "Kew Gardens, London", "asdasd@gmail.com", 4.5F, R.drawable.avt_4,new int[]{4,5}));
        mapTopPG.put(5, new Photographer(5, "Claud Bobby", "4291 Green Meadows Dr Enon, Ohio", "asdasd@gmail.com", 4.5F, R.drawable.avt_5,new int[]{0}));
        mapTopPG.put(6, new Photographer(6, "Mickey Carver", "82 Hastings Ave Croton On Hudson, New York", "asdasd@gmail.com", 4.5F, R.drawable.avt_6,new int[]{0,2,3}));
        mapTopPG.put(7, new Photographer(7, "Lauren Tiffani", "79 Lindale Ave Williamstown, New Jerse", "asdasd@gmail.com", 4.5F, R.drawable.avt_7,new int[]{1,3}));
        mapTopPG.put(8, new Photographer(8, "Tedd Richelle", "603 Van Alstyne, Texas", "asdasd@gmail.com", 4.5F, R.drawable.avt_8,new int[]{3}));
        mapTopPG.put(9, new Photographer(9, "Shavon Lonnie", "9022 Shawnee Rd Nancy, Kentucky", "asdasd@gmail.com", 4.5F, R.drawable.avt_9,new int[]{1,2,4,5,6}));

        mapFollowedPG.put(1, new Photographer(2, "Victoria Israel", "Via del Campidoglio, Rome", "asdasd@gmail.com", 4.5F, R.drawable.avt_2,new int[]{0,2,3}));
        mapFollowedPG.put(2, new Photographer(4, "Ron James", "Kew Gardens, London", "asdasd@gmail.com", 4.5F, R.drawable.avt_4,new int[]{1,2,4}));
        mapFollowedPG.put(3, new Photographer(6, "Mickey Carver", "82 Hastings Ave Croton On Hudson, New York", "asdasd@gmail.com", 4.5F, R.drawable.avt_6,new int[]{0,3}));
        mapFollowedPG.put(4, new Photographer(9, "Shavon Lonnie", "9022 Shawnee Rd Nancy, Kentucky", "asdasd@gmail.com", 4.5F, R.drawable.avt_9,new int[]{0,2,3,4}));

        mapBookedPG.put(1, new Photographer(5, "Claud Bobby", "4291 Green Meadows Dr Enon, Ohio", "asdasd@gmail.com", 4.5F, R.drawable.avt_5,new int[]{0,2,3,6}));
        mapBookedPG.put(2, new Photographer(7, "Lauren Tiffani", "79 Lindale Ave Williamstown, New Jerse", "asdasd@gmail.com", 4.5F, R.drawable.avt_7,new int[]{1,2,3,5}));
        mapBookedPG.put(3, new Photographer(8, "Tedd Richelle", "603 Van Alstyne, Texas", "asdasd@gmail.com", 4.5F, R.drawable.avt_8,new int[]{0,2,3,5,6}));
    }

    public Photographer getPG(String key, int id) {
        Photographer pg = null;
        switch (key){
            case "top":
                pg = mapTopPG.get(id);
                break;
            case "followed":
                pg = mapFollowedPG.get(id);
                break;
            case "booked":
                pg = mapBookedPG.get(id);
                break;
        }
        return pg;
    }

    public int getMapTopPGSize() {
        return mapTopPG.size();
    }

    public int getMapFollowedPGSize() {
        return mapFollowedPG.size();
    }

    public int getMapBookedPGSize() {
        return mapBookedPG.size();
    }

}
