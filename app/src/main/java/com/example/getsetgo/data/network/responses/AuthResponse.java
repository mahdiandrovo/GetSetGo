package com.example.getsetgo.data.network.responses;

public class AuthResponse {
    private String error;
    private String message;
    //My API will send User when the login is Successful
    private User user;

    public AuthResponse(String error, String message, User user) {
        this.error = error;
        this.message = message;
        this.user = user;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

}
