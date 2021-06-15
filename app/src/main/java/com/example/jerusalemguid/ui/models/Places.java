package com.example.jerusalemguid.ui.models;

public class Places {
    String id;
    String title;
    String type ;
    String history;
    String image ;
    String descriptions ;
    Double lat1 ;
    Double lat2;
    public Double getLat1() {
        return lat1;
    }

    public void setLat1(Double lat1) {
        this.lat1 = lat1;
    }

    public Double getLat2() {
        return lat2;
    }

    public void setLat2(Double lat2) {
        this.lat2 = lat2;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Places(String id, String title, String image , String descriptions, String type, String history , Double lat1 , Double lat2 ) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.descriptions = descriptions;
        this.type = type;
        this.history = history;
        this.lat1 =lat1;
        this.lat2=lat2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}