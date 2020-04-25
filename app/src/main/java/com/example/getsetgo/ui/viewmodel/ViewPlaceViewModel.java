package com.example.getsetgo.ui.viewmodel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.getsetgo.data.network.responses.Place;
import com.example.getsetgo.ui.model.PlaceModel;

public class ViewPlaceViewModel extends ViewModel {

    public String placeName;
    public String placeLocation;
    public String placeDescription;

    private Context context;
    private PlaceModel placeModel;

    public ViewPlaceViewModel(Context context, PlaceModel placeModel) {
        this.context = context;
        this.placeModel = placeModel;
        setPlaceInformations(placeModel);
    }

    private void setPlaceInformations(PlaceModel placeModel){
        placeName = placeModel.getName();
        placeLocation = placeModel.getLocation();
        placeDescription = placeModel.getDescription();
    }

}
