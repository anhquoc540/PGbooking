package com.example.photographerbooking.interfaces;

public interface ItemOnCheckListener<T> {
    void onChecked(T id);
    void onUnchecked(T id);
}
