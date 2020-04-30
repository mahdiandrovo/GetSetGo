package com.example.getsetgo.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "http://192.168.0.101/MyMvvmApi/public/";
    private static RetrofitClient INSTANCE;
    private Retrofit retrofit;

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance(){
        if (INSTANCE == null){
            INSTANCE = new RetrofitClient();
        }
        return INSTANCE;
    }

    public MyApi getApi(){
        return retrofit.create(MyApi.class);
    }

}
