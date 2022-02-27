package com.example.photographerbooking.model;

public class Photographer {
    int id;
    int avatar;
    String name;
    String location;
    String email;
    float rating;

    public Photographer(int id, String name, String location, String email, float rating, int pg_avatar) {
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
}
