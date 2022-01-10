package com.example.authentication;

public class DataModel { // room object model
    String header; // room name

    public DataModel(String header) {
        this.header = header;
    }
    public String getHeader() {
        return header;
    } // get room name
    public void setHeader(String header) {
        this.header = header;
    } // set room name
}