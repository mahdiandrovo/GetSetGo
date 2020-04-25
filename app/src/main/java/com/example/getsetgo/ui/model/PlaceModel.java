package com.example.getsetgo.ui.model;

public class PlaceModel {

    String name;
    String location;
    String description;

    public PlaceModel(String name, String location, String description) {
        this.name = name;
        this.location = location;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

}
