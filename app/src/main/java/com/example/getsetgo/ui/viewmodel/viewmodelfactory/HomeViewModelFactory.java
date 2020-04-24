package com.example.getsetgo.ui.viewmodel.viewmodelfactory;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.getsetgo.data.repositories.HomeRepository;
import com.example.getsetgo.ui.viewmodel.HomeViewModel;

public class HomeViewModelFactory extends ViewModelProvider.NewInstanceFactory{

    private Context context;
    private HomeRepository homeRepository;

    public HomeViewModelFactory(Context context, HomeRepository homeRepository) {
        this.context = context;
        this.homeRepository = homeRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new HomeViewModel(context,homeRepository);
    }
}
