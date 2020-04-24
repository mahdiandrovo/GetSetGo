package com.example.getsetgo.ui.viewmodel.viewmodelfactory;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.getsetgo.data.repositories.SignupRepository;
import com.example.getsetgo.ui.model.SignupModel;
import com.example.getsetgo.ui.viewmodel.SignupViewModel;

public class SignupViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Context context;
    private SignupModel signupModel;
    private SignupRepository signupRepository;

    public SignupViewModelFactory(Context context, SignupModel signupModel, SignupRepository signupRepository) {
        this.context = context;
        this.signupModel = signupModel;
        this.signupRepository = signupRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SignupViewModel(context,signupModel,signupRepository);
    }
}
