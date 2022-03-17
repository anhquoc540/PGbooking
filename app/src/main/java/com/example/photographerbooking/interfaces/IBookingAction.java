package com.example.photographerbooking.interfaces;

public interface IBookingAction {
    void onDetailed(Object id);
    void onAccepted(Object id);
    void onRejected(Object id);
    void onCompleted(Object id);
}
