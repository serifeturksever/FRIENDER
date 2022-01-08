package com.example.authentication;

public class MessageModel {
    String email;
    String message;
    String date;

    public MessageModel(String email,String message,String date) {
        this.email = email;
        this.message = message;
        this.date = date;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate() {
        this.date = date;
    }

}