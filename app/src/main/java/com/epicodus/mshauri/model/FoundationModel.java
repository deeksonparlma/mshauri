package com.epicodus.mshauri.model;

public class FoundationModel {
    String mName;
    String mLocation;
    String mContact;
    String mWebsite;
    String mDescription;


    public FoundationModel(String name, String location, String contact, String website , String description) {
        this.mName = name;
        this.mLocation = location;
        this.mContact = contact;
        this.mDescription = description;
        mWebsite = website;
    }

    public String getmName() {
        return mName;
    }

    public String getmLocation() {
        return mLocation;
    }

    public String getmContact() {
        return mContact;
    }

    public String getmWebsite() {
        return mWebsite;
    }

    public String getmDescription() {
        return mDescription;
    }
}
