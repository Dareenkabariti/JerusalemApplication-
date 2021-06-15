package com.example.jerusalemguid.ui.models;

public  class Place {
    private String name , description , imageUrl , history ;
    public Place(String name , String description , String imageUrl, String history){
        this.name = name;
        this.description = description ;
        this.imageUrl = imageUrl ;
        this.history = history ;
    }

    public String getName() {
        return name;
    }
    public String gethistory() {
        return history;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
