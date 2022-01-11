package com.example.authentication;

public class DataModel { // room object model
    String header; // room name
    String date;

    public DataModel(String header,String date) {
        this.header = header;
        this.date = date;
    }
    public String getHeader() {
        return header;
    } // get room name
    public void setHeader(String header) {
        this.header = header;
    } // set room name
    public String getDate() {
        return date;
    } // get room name
    public void setDate(String date) {
        this.date = date;
    } // set room name
}