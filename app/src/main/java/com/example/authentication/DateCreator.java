package com.example.authentication;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateCreator {

    int timestamp;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat simpleDateFormat;
    String milli = String.valueOf(System.currentTimeMillis());
    long etMills = Long.parseLong(milli);

    // NO NEEDED CONSTRUCTOR

    public String getCurrentFullDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy hh.mm a", Locale.getDefault());
        String date = simpleDateFormat.format(etMills);

        return date;
    }

    public String getCurrentHourAndMinute(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh.mm a", Locale.getDefault());
        String date = simpleDateFormat.format(etMills);

        return date;
    }

}