package com.example.photographerbooking.model;


import java.util.List;
import java.util.Set;

public class PhotoService {
    private Long id;
    private String name;
    private String description;
    private double price;
    private List<String> bannerUrls;

    public PhotoService() {
    }

    public PhotoService(Long id, String name, String description, double price, List<String> bannerUrls) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.bannerUrls = bannerUrls;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getBanners() {
        return bannerUrls;
    }

    public void setBanners(List<String> banners) {
        this.bannerUrls = banners;
    }
}
