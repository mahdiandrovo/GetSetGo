package com.example.getsetgo.ui.viewmodel.viewmodelfactory;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.getsetgo.ui.model.PlaceModel;
import com.example.getsetgo.ui.viewmodel.ViewPlaceViewModel;

public class ViewPlaceViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Context context;
    private PlaceModel placeModel;

    public ViewPlaceViewModelFactory(Context context, PlaceModel placeModel) {
        this.context = context;
        this.placeModel = placeModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ViewPlaceViewModel(context,placeModel);
    }
}
