package com.example.getsetgo.data.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.getsetgo.data.db.AppDatabase;
import com.example.getsetgo.data.network.RetrofitClient;
import com.example.getsetgo.data.network.responses.AuthResponse;
import com.example.getsetgo.data.network.responses.Place;
import com.example.getsetgo.data.network.responses.PlacesResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {

    MutableLiveData<List<Place>> places = new MutableLiveData<>();
    public LiveData<List<Place>> getAllPlaces(){
        Call<PlacesResponse> call = RetrofitClient.getInstance().getApi().getPlaces();
        call.enqueue(new Callback<PlacesResponse>() {
            @Override
            public void onResponse(Call<PlacesResponse> call, Response<PlacesResponse> response) {
                places.setValue(response.body().getPlaces());
            }

            @Override
            public void onFailure(Call<PlacesResponse> call, Throwable t) {

            }
        });
        return places;
    }

}
