package com.example.photographerbooking.model;

import java.util.ArrayList;

public class Album {
    private String description;
    private int representativeImg;
    private ArrayList<Integer> photos;

    public Album(String description, int representativeImg) {
        this.description = description;
        this.representativeImg = representativeImg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRepresentativeImg() {
        return representativeImg;
    }

    public void setRepresentativeImg(int representativeImg) {
        this.representativeImg = representativeImg;
    }

    public ArrayList<Integer> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Integer> photos) {
        this.photos = photos;
    }
}
