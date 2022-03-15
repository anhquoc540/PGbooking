package com.example.photographerbooking.model;


import java.util.List;

public class PhotoService {
    private Long id;
    private String name;
    private float price;
    private float rating;
    private int representativeImg;
    private List<String> bannerUrls;
    private int idCategory;
    private int idPG;

    public PhotoService() {
    }

    public PhotoService(Long id, String name, float price, float rating, int representativeImg, List<String> bannerUrls, int idCategory, int idPG) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.representativeImg = representativeImg;
        this.bannerUrls = bannerUrls;
        this.idCategory = idCategory;
        this.idPG = idPG;
    }

    public int getIdPG() {
        return idPG;
    }

    public void setIdPG(int idPG) {
        this.idPG = idPG;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getRepresentativeImg() {
        return representativeImg;
    }

    public void setRepresentativeImg(int representativeImg) {
        this.representativeImg = representativeImg;
    }

    public List<String> getBannerUrls() {
        return bannerUrls;
    }

    public void setBannerUrls(List<String> bannerUrls) {
        this.bannerUrls = bannerUrls;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
}
