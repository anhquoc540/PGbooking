package com.example.photographerbooking.model;

public class ServicePackage {
    private int image;
    private String type;
    private String unit;
    private String price;

    public ServicePackage(int image, String type, String unit, String price) {
        this.image = image;
        this.type = type;
        this.unit = unit;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public String getType() {
        return type;
    }

    public String getUnit() {
        return unit;
    }

    public String getPrice() {
        return price;
    }
}
