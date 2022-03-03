package com.example.photographerbooking.model;

import com.example.photographerbooking.R;

public class Category {
    int id;
    private String label;
    private int image;

    public Category(int id, String label, int image) {
        this.id = id;
        this.label = label;
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public int getImage() {
        return image;
    }
}
