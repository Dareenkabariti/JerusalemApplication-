package com.example.jerusalemguid.ui.models;

public class Articale {
    private String author , title , description , publishedAt , imageUrl ;
    public Articale(String author , String title , String description , String publishedAt , String imageUrl){
        this.author = author ;
        this.title = title ;
        this.description = description ;
        this.publishedAt = publishedAt ;
        this.imageUrl = imageUrl ;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
