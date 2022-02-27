package com.example.photographerbooking.model;

import java.time.LocalDate;

public class ServiceComment {
    private String icon;
    private String username;
    private String content;
    private LocalDate createDate;
    private double ratePoint;


    public ServiceComment() {
    }

    public ServiceComment(String icon, String username, String content, LocalDate createDate, double ratePoint) {
        this.icon = icon;
        this.username = username;
        this.content = content;
        this.createDate = createDate;
        this.ratePoint = ratePoint;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public double getRatePoint() {
        return ratePoint;
    }

    public void setRatePoint(double ratePoint) {
        this.ratePoint = ratePoint;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
