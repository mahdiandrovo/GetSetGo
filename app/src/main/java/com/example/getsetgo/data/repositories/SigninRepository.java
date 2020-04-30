package com.example.getsetgo.data.repositories;


import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.getsetgo.data.db.AppDatabase;
import com.example.getsetgo.data.db.entities.User;
import com.example.getsetgo.data.db.entities.UserDao;
import com.example.getsetgo.data.network.RetrofitClient;


import okhttp3.ResponseBody;
import retrofit2.Call;

public class SigninRepository {

    private AppDatabase appDatabase;
    private UserDao userDao;

    public SigninRepository(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
        userDao = this.appDatabase.userDao();
    }

    //Retrofit Signin API Call
    public Call<ResponseBody> userSignin(String email, String password){
        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .userLogin(email,password);
        return call;
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
