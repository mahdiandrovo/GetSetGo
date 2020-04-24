package com.example.getsetgo.ui.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;


import com.example.getsetgo.data.db.entities.User;
import com.example.getsetgo.data.repositories.SigninRepository;
import com.example.getsetgo.ui.auth.SignupActivity;
import com.example.getsetgo.ui.auth.listener.SigninListener;
import com.example.getsetgo.ui.model.SigninModel;

import org.json.JSONException;
import org.json.JSONObject;

import static androidx.core.content.ContextCompat.startActivity;


public class SigninViewModel extends ViewModel {

    public String email;
    public String password;

    private Context context;
    private SigninModel signInModel;
    private SigninRepository signinRepository;

    public SigninListener signinListener;


    public SigninViewModel(Context context, SigninModel signInModel, SigninRepository loginRepository) {
        this.context = context;
        this.signInModel = signInModel;
        this.signinRepository = loginRepository;
    }

    //When Signin Button clicked
    public void onSigninButtonClicked(View view){
        //Started
        signinListener.onStarted();

        signInModel.setEmail(email);
        signInModel.setPassword(password);

        if (!signInModel.isInputedEmailValid()){
            //Failed
            signinListener.onFailure("Enter Valid Email Address");
            return;
        }
        else {
            if (!signInModel.isInputedPasswordValid()){
                //Failed
                signinListener.onFailure("Enter Valid Password");
                return;
            }
        }


        //Success
        LiveData<String> loginResponse = signinRepository.userLogin(signInModel.getEmail(),signInModel.getPassword());
        loginResponse.observe((LifecycleOwner) context, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);

                    boolean error = jsonObject.getBoolean("error");
                    String message = jsonObject.getString("message");

                    if (!error){
                        signinListener.onSuccess(message);
                        JSONObject userObj = jsonObject.getJSONObject("user");
                        int id = userObj.getInt("id");
                        String name = userObj.getString("name");
                        String email = userObj.getString("email");
                        User user = new User(id,name,email);
                        signinRepository.saveUser(user);
                    }
                    else {
                        signinListener.onFailure(message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }



    //Get logged in User
    public LiveData<User> getLoggedInUser(){
        return signinRepository.getLoggedInUser();
    }
}
