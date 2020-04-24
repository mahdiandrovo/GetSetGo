package com.example.getsetgo.ui.model;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Pattern;

public class SigninModel {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isInputedEmailValid(){
        if (email != null && !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return true;
        }
        return false;
    }

    public boolean isInputedPasswordValid(){
        if (password != null && !TextUtils.isEmpty(password)){
            return true;
        }
        return false;
    }

}
