package com.example.getsetgo.ui.model;

import android.text.TextUtils;
import android.util.Patterns;

public class SignupModel {
    private String name;
    private String email;
    private String password;
    private String confirmPassword;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public boolean isInputedNameValid(){
        if (name != null && !TextUtils.isEmpty(name) && name.length()>3){
            return true;
        }
        return false;
    }

    public boolean isInputedEmailValid(){
        if (email != null && !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return true;
        }
        return false;
    }

    public boolean isInputedPasswordValid(){
        if (password != null && !TextUtils.isEmpty(password) && password.length()>5){
            return true;
        }
        return false;
    }

    public boolean isInputedConfirmPasswordValid(){
        if (confirmPassword != null && !TextUtils.isEmpty(confirmPassword) && confirmPassword.length()>5){
            return true;
        }
        return false;
    }

    public boolean isInputedPasswordAndConfirmPasswordMatches(){
        if (password.equals(confirmPassword)){
            return true;
        }
        return false;
    }
}
