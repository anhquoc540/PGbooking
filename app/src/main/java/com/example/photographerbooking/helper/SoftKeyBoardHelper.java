package com.example.photographerbooking.helper;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;

public class SoftKeyBoardHelper {

    public static void hideSoftKeyboard(@NonNull Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isAcceptingText()) {
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(),
                    0
            );
        }
    }
}
