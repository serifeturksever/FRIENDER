package com.example.authentication;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateCreator { // date format class that we created
    String milli = String.valueOf(System.currentTimeMillis());
    long etMills = Long.parseLong(milli);

    // NO NEEDED CONSTRUCTOR
    public String getCurrentFullDate(){ // get formatted full date from current time millisecond
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy hh.mm a", Locale.getDefault()); // formatting type is day - month - year - hour - minute and AM/PM
        String date = simpleDateFormat.format(etMills); // convert formatted date into string
        return date;
    }

    public String getCurrentHourAndMinute(){ // get only current hour and minute from formatted date (from current time millisecond)
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh.mm a", Locale.getDefault()); // formatting type is hour - minute and AM/PM
        String date = simpleDateFormat.format(etMills); // convert formatted date into string
        return date;
    }

}