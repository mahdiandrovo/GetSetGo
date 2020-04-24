package com.example.getsetgo.ui.viewmodel.viewmodelfactory;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.getsetgo.data.repositories.SigninRepository;
import com.example.getsetgo.ui.auth.SigninActivity;
import com.example.getsetgo.ui.model.SigninModel;
import com.example.getsetgo.ui.viewmodel.SigninViewModel;

public class SigninViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Context context;
    private SigninModel signinModel;
    private SigninRepository signinRepository;

    public SigninViewModelFactory(SigninActivity signinActivity, SigninModel signinModel, SigninRepository signinRepository) {
        this.context = signinActivity;
        this.signinModel = signinModel;
        this.signinRepository = signinRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SigninViewModel(context,signinModel,signinRepository);
    }

}
