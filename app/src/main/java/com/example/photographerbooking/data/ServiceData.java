package com.example.photographerbooking.data;

import android.util.ArrayMap;

import com.example.photographerbooking.R;
import com.example.photographerbooking.model.Category;
import com.example.photographerbooking.model.PhotoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceData {
    final private Map<Integer, PhotoService> mapService = new ArrayMap<>();
    List<String> bannerUrls = new ArrayList<>();;

    public ServiceData() {
        bannerUrls.add("https://cdn.luxstay.com/home/apartment/apartment_1_1578970876.jpg");
        bannerUrls.add("https://cdn.luxstay.com/home/apartment/apartment_2_1578970932.jpg");
        bannerUrls.add("https://cdn.luxstay.com/home/apartment/apartment_3_1578970988.jpg");
        bannerUrls.add("https://cdn.luxstay.com/home/apartment/apartment_4_1578971024.jpg");

        mapService.put(0, new PhotoService(0L, "Classic Photo & Video", "2 Hour Bundle", 1299f, 3.5f, R.drawable.wedding_1, bannerUrls,0));
        mapService.put(1, new PhotoService(1L, "Classic Photo & Video", "4 Hour Bundle", 2500f, 3.5f, R.drawable.wedding_1, bannerUrls,1));
        mapService.put(2, new PhotoService(2L, "Expressions Photo & Video", "6 Hour Bundle", 2500f, 3.5f, R.drawable.food_1, bannerUrls,0));
        mapService.put(3, new PhotoService(3L, "Expressions Photo & Video", "8 Hour Bundle",2500f, 3.5f, R.drawable.food_1, bannerUrls,0));
        mapService.put(4, new PhotoService(4L, "Exclusive Photo & Video", "8 Hour Bundle", 2900, 3.5f, R.drawable.family_1, bannerUrls,3));
        mapService.put(5, new PhotoService(5L, "Exclusive Photo & Video", "10 Hour Bundle", 2900, 3.5f, R.drawable.family_1, bannerUrls,4));
        mapService.put(6, new PhotoService(6L, "Exclusive Photo & Video", "6 Hour Bundle", 2900, 3.5f, R.drawable.family_1, bannerUrls,4));
    }

    public  int getMapServiceSize() {
        return mapService.size();
    }

    public PhotoService getService(int id) {
        return mapService.get(id);
    }
}
