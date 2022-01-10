package com.example.authentication;

public class MessageModel {
    String email;
    String message;
    String date;

    public MessageModel(String email,String message,String date) { // message object model
        this.email = email;
        this.message = message;
        this.date = date;
    }
    public String getEmail() {
        return email;
    } // get email

    public void setEmail(String email) {
        this.email = email;
    } // set email

    public String getMessage() {
        return message;
    } // get message

    public void setMessage(String message) {
        this.message = message;
    } // set message

    public String getDate() {
        return date;
    } // get date

    public void setDate() {
        this.date = date;
    } // set date

}