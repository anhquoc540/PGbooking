package com.example.photographerbooking.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Booking {
    private int id;
    private PhotoService bookingService;
    private LocalDate bookingDate;
    private LocalTime startingTime;
    private float price;
    private BookingStatus status;

    public Booking(int id, PhotoService bookingService, LocalDate bookingDate, LocalTime startingTime, float price, BookingStatus status) {
        this.id = id;
        this.bookingService = bookingService;
        this.bookingDate = bookingDate;
        this.startingTime = startingTime;
        this.price = price;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public com.example.photographerbooking.model.PhotoService getBookingService() {
        return bookingService;
    }

    public void setBookingService(com.example.photographerbooking.model.PhotoService bookingService) {
        this.bookingService = bookingService;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(LocalTime startingTime) {
        this.startingTime = startingTime;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public com.example.photographerbooking.model.BookingStatus getStatus() {
        return status;
    }

    public void setStatus(com.example.photographerbooking.model.BookingStatus status) {
        this.status = status;
    }
}
