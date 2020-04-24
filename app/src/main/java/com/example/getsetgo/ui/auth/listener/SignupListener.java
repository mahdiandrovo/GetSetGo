package com.example.getsetgo.ui.auth.listener;

public interface SignupListener {
    //When Signup Process started
    void onStarted();
    //When Signup Process is success
    void onSuccess(String message);
    //When Signup Process failed...There should an Error Message
    void onFailure(String message);
}
