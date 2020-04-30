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

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        //Get Response from Server
        Call<ResponseBody> call = signinRepository.userSignin(signInModel.getEmail(),signInModel.getPassword());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    //No App Level error

                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.body().string());
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

                        } else {
                            //Do not need this
                            signinListener.onFailure(message);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    //App Level error
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.errorBody().string());
                        boolean error = jsonObject.getBoolean("error");
                        String message = jsonObject.getString("message");
                        if (error){
                            signinListener.onFailure(message);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //Server connection failed
                signinListener.onFailure("Some Error Occured! Please Try Again!");
            }
        });

    }

    //Get logged in User
    public LiveData<User> getLoggedInUser(){
        return signinRepository.getLoggedInUser();
    }
}
