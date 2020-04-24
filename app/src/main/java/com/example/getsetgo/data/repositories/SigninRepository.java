package com.example.getsetgo.data.repositories;


import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.getsetgo.data.db.AppDatabase;
import com.example.getsetgo.data.db.entities.User;
import com.example.getsetgo.data.db.entities.UserDao;
import com.example.getsetgo.data.network.RetrofitClient;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninRepository {

    private AppDatabase appDatabase;
    private UserDao userDao;

    public SigninRepository(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
        userDao = this.appDatabase.userDao();
    }

    //Retrofit Signin API Call
    MutableLiveData<String> loginResponse = new MutableLiveData<>();

    public MutableLiveData<String> userLogin(String email, String password){

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .userLogin(email,password);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    //No App Level error
                    try {
                        loginResponse.setValue(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    //App Level error
                    loginResponse.setValue("");
                    /*
                    try {
                        loginResponse.setValue(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    */
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //Failed to receive HTTP call
                loginResponse.setValue("");
                /*
                loginResponse.setValue(t.getMessage());
                */
            }
        });

        return loginResponse;
    }

    //Save User in ROOM Database
    public void saveUser(User user){
        new InsertAsyncTask(userDao).execute(user);
    }

    private class InsertAsyncTask extends AsyncTask<User,Void,Void> {

        private UserDao userDao;

        public InsertAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.upsert(users[0]);
            return null;
        }
    }

    //Return saved User List
    public LiveData<User> getLoggedInUser(){
        return appDatabase.userDao().getUser();
    }


}
