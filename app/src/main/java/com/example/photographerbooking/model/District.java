package com.example.photographerbooking.model;

import java.util.List;

public class District {
    private int id;
    private String name;
    private List<Ward> wards;

    public District(int id, String name, List<Ward> wards) {
        this.id = id;
        this.name = name;
        this.wards = wards;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ward> getWards() {
        return wards;
    }

    public void setWards(List<Ward> wards) {
        this.wards = wards;
    }
}
