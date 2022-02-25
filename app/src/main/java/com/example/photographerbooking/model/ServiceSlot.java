package com.example.photographerbooking.model;

import android.app.Service;

import java.time.LocalDate;
import java.time.LocalTime;

public class ServiceSlot {
    private LocalTime from;
    private LocalTime to;
    private PhotoService service;
    private int slotOrdinary;

    public ServiceSlot() {
    }

    public ServiceSlot(LocalTime from, LocalTime to, PhotoService service) {
        this.from = from;
        this.to = to;
        this.service = service;
    }

    public ServiceSlot(LocalTime from, LocalTime to, PhotoService service, int slotOrdinary) {
        this.from = from;
        this.to = to;
        this.service = service;
        this.slotOrdinary = slotOrdinary;
    }

    public LocalTime getFrom() {
        return from;
    }

    public void setFrom(LocalTime from) {
        this.from = from;
    }

    public LocalTime getTo() {
        return to;
    }

    public void setTo(LocalTime to) {
        this.to = to;
    }

    public PhotoService getService() {
        return service;
    }

    public void setService(PhotoService service) {
        this.service = service;
    }

    public int getSlotOrdinary() {
        return slotOrdinary;
    }

    public void setSlotOrdinary(int slotOrdinary) {
        this.slotOrdinary = slotOrdinary;
    }
}
