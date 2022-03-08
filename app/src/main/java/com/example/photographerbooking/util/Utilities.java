package com.example.photographerbooking.util;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.photographerbooking.R;

public class Utilities {
    public static int totalSecondOfMinute = 60;
    public static int totalSecondOfHour = 3600;
    public static int totalSecondOfDay = 3600 * 24;
    public static int totalSecondOfWeek = 3600 * 24 * 7;
    public static int totalSecondOfMonth_28 = totalSecondOfDay * 28;
    public static int totalSecondOfMonth_30 = totalSecondOfDay * 30;


    public String convertToAppropriateAge(long ageInSecond, int month, int year) {
        if (ageInSecond >= 0 && ageInSecond < 60) {
            return ageInSecond + " seconds";
        } else if (ageInSecond >= 60 && ageInSecond < 3600) {
            return Math.floor(ageInSecond / 60) + "minutes";
        } else if (ageInSecond >= 3600 && ageInSecond < totalSecondOfDay) {
            return Math.floor(ageInSecond / totalSecondOfHour) + "hours";
        } else if (ageInSecond >= totalSecondOfDay && ageInSecond < totalSecondOfWeek) {
            return Math.floor(ageInSecond / totalSecondOfDay) + "days";
        } else if (ageInSecond >= totalSecondOfWeek && ageInSecond < totalSecondOfMonth_30) {
            return Math.floor(ageInSecond/totalSecondOfMonth_30) + "months";
        }
        return "";
    }

}
