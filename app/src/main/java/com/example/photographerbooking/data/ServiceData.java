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

        mapService.put(0, new PhotoService(0L, "Classic Photo & Video Wedding", 129f, 4.6f, R.drawable.wedding_1, bannerUrls,0,0));
        mapService.put(1, new PhotoService(1L, "Exclusive Lifestyle & Work", 250f, 4.8f, R.drawable.vienna_3, bannerUrls,0,0));
        mapService.put(2, new PhotoService(2L, "Commercial Food Photo", 180f, 4.4f, R.drawable.food_1, bannerUrls,0,0));
        mapService.put(3, new PhotoService(3L, "Expressions Photo & Video Wedding", 230f, 3.5f, R.drawable.wedding_2, bannerUrls,1,1));
        mapService.put(4, new PhotoService(4L, "Exclusive Portrait Photo", 100f, 4.6f, R.drawable.portrait_1, bannerUrls,1,1));
        mapService.put(5, new PhotoService(5L, "Family Photo & Video", 150f, 4.5f, R.drawable.family_1, bannerUrls,2,2));
        mapService.put(6, new PhotoService(6L, "Business Fashion Photo & Video", 290f, 4.2f, R.drawable.fashion_1, bannerUrls,3,2));
        mapService.put(7, new PhotoService(7L, "Exclusive Lifestyle & Work", 250f, 4.8f, R.drawable.life_style_2, bannerUrls,0,0));
        mapService.put(8, new PhotoService(8L, "Commercial Food Photo", 180f, 4.4f, R.drawable.food_2, bannerUrls,0,0));
        mapService.put(9, new PhotoService(9L, "Romance Love Photo", 250f, 4.8f, R.drawable.vienna_2, bannerUrls,0,0));
        mapService.put(10, new PhotoService(10L, "Exclusive Photo & Video Wedding", 200.0f, 3.5f, R.drawable.wedding_3, bannerUrls,1,1));
        mapService.put(11, new PhotoService(11L, "Expressions Portrait Photo", 129f, 4.6f, R.drawable.vienna_1, bannerUrls,0,0));
    }

    public  int getMapServiceSize() {
        return mapService.size();
    }

    public PhotoService getService(int id) {
        return mapService.get(id);
    }

    public List<PhotoService> getAllService() {
        List<PhotoService> rs = new ArrayList<>();
        for (int i = mapService.size()-1; i >= 0; i--) {
            rs.add(mapService.get(i));
        }
        return rs;
    }
}
