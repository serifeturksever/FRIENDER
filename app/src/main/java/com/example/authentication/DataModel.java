package com.example.authentication;

public class DataModel { // room object model
    String header; // room name
    String date;
    String category;
    String creator;

    public DataModel(String header,String date,String category,String creator) {
        this.header = header;
        this.date = date;
        this.category = category;
        this.creator = creator;
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
    public String getCategory() { return category; } // set room name
    public String getCreator() { return creator; } // set room name
}