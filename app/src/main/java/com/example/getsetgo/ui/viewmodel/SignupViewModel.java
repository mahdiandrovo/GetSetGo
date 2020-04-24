package com.example.getsetgo.ui.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.getsetgo.data.db.entities.User;
import com.example.getsetgo.data.repositories.SignupRepository;
import com.example.getsetgo.ui.auth.listener.SignupListener;
import com.example.getsetgo.ui.model.SignupModel;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupViewModel extends ViewModel {
    public String name;
    public String email;
    public String password;
    public String confirmPassword;

    private Context context;
    private SignupModel signupModel;
    private SignupRepository signupRepository;

    public SignupListener signupListener;

    public SignupViewModel(Context context,SignupModel signupModel,SignupRepository signupRepository){
        this.context = context;
        this.signupModel = signupModel;
        this.signupRepository = signupRepository;
    }

    //When Signin Button clicked
    public void onSignupButtonClicked(View view){
        //Started
        signupListener.onStarted();

        signupModel.setName(name);
        signupModel.setEmail(email);
        signupModel.setPassword(password);
        signupModel.setConfirmPassword(confirmPassword);


        if (!signupModel.isInputedNameValid()){
            //Failed
            signupListener.onFailure("Enter Valid Name");
            return;
        } else {
            if (!signupModel.isInputedEmailValid()){
                //Failed
                signupListener.onFailure("Enter Valid Email");
                return;
            } else {
                if (!signupModel.isInputedPasswordValid()){
                    //Failed
                    signupListener.onFailure("Enter Valid Password");
                    return;
                } else {
                    if (!signupModel.isInputedConfirmPasswordValid()){
                        //Failed
                        signupListener.onFailure("Enter Valid Confirm Password");
                        return;
                    } else {
                        if (!signupModel.isInputedPasswordAndConfirmPasswordMatches()){
                            //Failed
                            signupListener.onFailure("Password and Confirm Password Didn't Matched");
                            return;
                        }
                    }
                }
            }
        }

        final LiveData<String> signupResponse = signupRepository.userSignup(signupModel.getName(),signupModel.getEmail(),signupModel.getPassword());
        signupResponse.observe((LifecycleOwner) context, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    boolean error = jsonObject.getBoolean("error");
                    String message = jsonObject.getString("message");
                    if (!error){
                        signupListener.onSuccess(message);
                        //Saving user to ROOM Database
                        User user = new User(0,signupModel.getName(),signupModel.getEmail());
                        signupRepository.saveUser(user);
                    } else {
                        signupListener.onFailure(message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    //Get logged in User
    public LiveData<User> getLoggedInUser(){
        return signupRepository.getLoggedInUser();
    }

}
