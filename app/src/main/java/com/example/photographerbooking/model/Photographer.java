package com.example.photographerbooking.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Photographer implements Serializable {
    private int id;
    private int avatar;
    private String name;
    private String location;
    private String email;
    private float rating;
    private List<Integer> serviceIds = new ArrayList<>();
    int[] d = {0,5};

    public Photographer(int id, String name, String location, String email, float rating, int pg_avatar, int[] serviceIds) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.email = email;
        this.rating = rating;
        this.avatar = pg_avatar;
        for (int serviceId : serviceIds) {
            this.serviceIds.add(serviceId);
        }
    }

    public Photographer(int id, String amelia_brown, String location, String email, float rating, int pg_avatar) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.email = email;
        this.rating = rating;
        this.avatar = pg_avatar;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public float getRating() {
        return rating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public List<Integer> getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(List<Integer> serviceIds) {
        this.serviceIds = serviceIds;
    }
}