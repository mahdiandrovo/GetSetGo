package com.example.getsetgo.data.repositories;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.getsetgo.data.db.AppDatabase;
import com.example.getsetgo.data.db.entities.User;
import com.example.getsetgo.data.db.entities.UserDao;
import com.example.getsetgo.data.network.RetrofitClient;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupRepository {

    private AppDatabase appDatabase;
    private UserDao userDao;

    public SignupRepository(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
        userDao = this.appDatabase.userDao();
    }

    MutableLiveData<String> signupResponse = new MutableLiveData<>();

    public MutableLiveData<String> userSignup(String name,String email, String password){

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .userSignup(name,email,password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        signupResponse.setValue(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    signupResponse.setValue("");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                signupResponse.setValue("");
            }
        });
        return signupResponse;
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
