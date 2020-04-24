package com.example.getsetgo.ui.auth.listener;

import androidx.lifecycle.LiveData;

public interface SigninListener {
    //When Signin Process started
    void onStarted();
    //When Signin Process is success
    void onSuccess(String message);
    //When Signin Process failed...There should an Error Message
    void onFailure(String message);

}
