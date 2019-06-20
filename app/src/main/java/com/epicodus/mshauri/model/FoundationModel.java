package com.epicodus.mshauri.model;

public class FoundationModel {
    String posts;
    String stars;
    String Location;
    String Number;

    public FoundationModel(String posts, String stars, String location, String number) {
        this.posts = posts;
        this.stars = stars;
        Location = location;
        Number = number;
    }

    public String getPosts() {
        return posts;
    }

    public String getStars() {
        return stars;
    }

    public String getLocation() {
        return Location;
    }

    public String getNumber() {
        return Number;
    }
}
