package com.example.photographerbooking.util;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.photographerbooking.R;
import com.example.photographerbooking.model.Booking;
import com.example.photographerbooking.model.BookingStatus;
import com.example.photographerbooking.model.Category;
import com.example.photographerbooking.model.PhotoService;

import java.time.LocalDate;
import java.time.LocalTime;
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
    public static List<Category> categories = new ArrayList<>();
    public static List<Booking> bookingList = new ArrayList<>();

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
            photoServices.add(new PhotoService(1l,"Wedding Photo Service", "Hot", 89.99f,4.5f,R.drawable.wedding_1, getServiceBannerUrl(),1));
            photoServices.add(new PhotoService(2l,"Event Photo Service", "Hot", 89.99f,4.5f,R.drawable.event_photo_al_2, getServiceBannerUrl(),1));
            photoServices.add(new PhotoService(3l,"Family Photo", "Hot", 89.99f,4.5f,R.drawable.family_photo_2, getServiceBannerUrl(),1));
        }
        return photoServices;
    }

    public static List<Category> getServiceCategory(){
        if(categories.isEmpty()){
            categories.add(new Category(1, "Product Marketing",R.drawable.business_marketing_category_2));
            categories.add(new Category(2, "Event",R.drawable.event_photo_al_2));
            categories.add(new Category(3, "Food", R.drawable.food_photo_1));
            categories.add(new Category(4,"Landscape", R.drawable.landscape_al_3));
        }
        return categories;
    }

    public static List<Booking> getBookingList(){
        if(bookingList.isEmpty()){
            bookingList.add(new Booking("book0001", getPhotoServices().get(0), LocalDate.of(2022,4,3), LocalTime.of(8,30),80, BookingStatus.ACCEPTED));
            bookingList.add(new Booking("book0002", getPhotoServices().get(1), LocalDate.of(2022,4,5), LocalTime.of(8,40),80, BookingStatus.REJECTED));
            bookingList.add(new Booking("book0003", getPhotoServices().get(2), LocalDate.of(2022,4,7), LocalTime.of(8,50),80, BookingStatus.COMPLETE));
            bookingList.add(new Booking("book0004", getPhotoServices().get(1), LocalDate.of(2022,4,10), LocalTime.of(8,55),80, BookingStatus.FINISH));
            bookingList.add(new Booking("book0005", getPhotoServices().get(2), LocalDate.of(2022,4,12), LocalTime.of(8,00),80, BookingStatus.CANCELED));
        }
            return bookingList;
    }
}
