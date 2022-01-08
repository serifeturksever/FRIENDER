package com.example.authentication;

public class DataModel {
    //DateCreator dateCreator = new DateCreator();
    String header;
    //String date = dateCreator.getCurrentFullDate();

    public DataModel(String header) {
        this.header = header;
        //this.date = date;
    }
    public String getHeader() {
        return header;
    }
    public void setHeader(String header) {
        this.header = header;
    }
    /*public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }*/
}