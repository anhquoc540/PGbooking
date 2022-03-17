package com.example.photographerbooking;


import java.io.Serializable;
import java.util.ArrayList;

public class Common implements Serializable {
    public static final String KEY_ENABLE_BUTTON_NEXT = "ENABLE_BUTTON_NEXT";
    public static final String KEY_STEP = "STEP_SAVE";
    public static final String KEY_PLACE = "PLACE_SAVE";
    public static final String KEY_DATE = "DATE_SAVE";
    public static final String KEY_TIME1 = "TIME_SAVE";
    public static final String KEY_TIME2 = "TIME_SAVE";
    public static final String KEY_EMAIL = "EMAIl_SAVE";
    public static final String KEY_DELIVERY_ADDRESS = "DA_SAVE";
    public static final String KEY_CONFIRM = "BOOKING_DONE";
    public static String dataPlace= "";
    public static String date= "";
    public static String dataDeliveryAddress= "";
    public static String email = "";
    public static int step = -1;
    public static final String BOOK_SLOT = "BOOK_SLOT";
    public static ArrayList<String> book_slot = new ArrayList<>();
}
