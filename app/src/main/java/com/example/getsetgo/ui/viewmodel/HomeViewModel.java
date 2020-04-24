package com.example.getsetgo.ui.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.getsetgo.data.network.responses.Place;
import com.example.getsetgo.data.repositories.HomeRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {
    private Context context;
    private HomeRepository homeRepository;

    public HomeViewModel(Context context, HomeRepository homeRepository) {
        this.context = context;
        this.homeRepository = homeRepository;
    }

    public LiveData<List<Place>> allPlaces(){
        return homeRepository.getAllPlaces();
    }

}
