package com.example.getsetgo.data.network.responses;

import java.util.List;

public class PlacesResponse {
    private boolean error;
    private String message;
    private List<Place> places;

    public PlacesResponse(boolean error, String message, List<Place> places) {
        this.error = error;
        this.message = message;
        this.places = places;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public List<Place> getPlaces() {
        return places;
    }
}
