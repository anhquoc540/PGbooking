package com.example.photographerbooking.util;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.photographerbooking.R;
import com.example.photographerbooking.model.PhotoService;

import java.util.ArrayList;
import java.util.List;

public class Utilities {
    public static int totalSecondOfMinute = 60;
    public static int totalSecondOfHour = 3600;
    public static int totalSecondOfDay = 3600 * 24;
    public static int totalSecondOfWeek = 3600 * 24 * 7;
    public static int totalSecondOfMonth_28 = totalSecondOfDay * 28;
    public static int totalSecondOfMonth_30 = totalSecondOfDay * 30;
    public static List<String> serviceBanner = new ArrayList<>();
    public static List<PhotoService> photoServices = new ArrayList<>();


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

    public static List<String> getServiceBannerUrl(){
        if(serviceBanner.size() == 0){
            serviceBanner.add("https://media.istockphoto.com/photos/happy-wedding-photography-of-bride-and-groom-at-wedding-ceremony-picture-id1190043570?k=20&m=1190043570&s=612x612&w=0&h=4ucKegbD9AHd99kH5uEqrtm5zovyZ6IsYa33hR2pV-k=");
            serviceBanner.add("https://img.freepik.com/free-photo/beautiful-couple-having-their-wedding-beach_23-2149043941.jpg?size=626&ext=jpg");
            serviceBanner.add("https://static01.nyt.com/images/2014/07/14/world/WEDDING/WEDDING-articleLarge.jpg?quality=75&auto=webp&disable=upscale");
            serviceBanner.add("https://c.ndtvimg.com/2022-02/nrsjstq_kyiv-couple-marry-amid-air-raid-sirensfacebook_625x300_25_February_22.jpg");
        }
        return serviceBanner;
    }

    public static List<PhotoService> getPhotoServices(){
        if(photoServices.size() == 0){
            photoServices.add(new PhotoService(1l,"Wedding Photo Service", "Hot", 89.99f,4.5f,0, getServiceBannerUrl(),1));
            photoServices.add(new PhotoService(2l,"Marketing Photo Service", "Hot", 89.99f,4.5f,0, getServiceBannerUrl(),1));
            photoServices.add(new PhotoService(3l,"Newspaper Photo Service", "Hot", 89.99f,4.5f,0, getServiceBannerUrl(),1));
        }
        return photoServices;
    }
}
