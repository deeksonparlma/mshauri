package com.epicodus.mshauri.model;

public class chat {
    String sender;
    String message;
    String time;


    public chat(String sender, String message, String time) {
        this.sender = sender;
        this.message = message;
        this.time = time;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }
}
