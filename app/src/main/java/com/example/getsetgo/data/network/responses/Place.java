package com.example.getsetgo.data.network.responses;

public class Place {
    private int id;
    private String name;
    private String location;
    private String description;
    private double latitude;
    private double longitude;

    public Place(int id, String name, String location, String description, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
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

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
