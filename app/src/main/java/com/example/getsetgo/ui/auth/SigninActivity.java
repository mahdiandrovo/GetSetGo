package com.example.getsetgo.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.getsetgo.R;
import com.example.getsetgo.data.db.AppDatabase;
import com.example.getsetgo.data.db.entities.User;
import com.example.getsetgo.data.repositories.SigninRepository;
import com.example.getsetgo.databinding.ActivitySigninBinding;
import com.example.getsetgo.ui.auth.listener.SigninListener;
import com.example.getsetgo.ui.home.HomeActivity;
import com.example.getsetgo.ui.model.SigninModel;
import com.example.getsetgo.ui.viewmodel.SigninViewModel;
import com.example.getsetgo.ui.viewmodel.viewmodelfactory.SigninViewModelFactory;
import com.google.android.material.snackbar.Snackbar;

public class SigninActivity extends AppCompatActivity implements SigninListener, View.OnClickListener {

    private LinearLayout rootLayout;
    private ProgressBar progress_bar;
    private TextView textView_NeedAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitySigninBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_signin);

        rootLayout = (LinearLayout) findViewById(R.id.root_layout);
        AppDatabase appDatabase = AppDatabase.getDatabase(this);

        SigninRepository signinRepository = new SigninRepository(appDatabase);

        SigninViewModel signinViewModel = ViewModelProviders.of(this, new SigninViewModelFactory(this, new SigninModel(), signinRepository)).get(SigninViewModel.class);
        binding.setViewmodel(signinViewModel);

        //AuthListener interface assign
        signinViewModel.signinListener = this;

        progress_bar = (ProgressBar) findViewById(R.id.progress_bar);
        textView_NeedAccount = (TextView) findViewById(R.id.needAccount_TextView);
        textView_NeedAccount.setOnClickListener(this);

        signinViewModel.getLoggedInUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user!=null){
                    finish();
                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onStarted() {
        progress_bar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess(String message) {
        progress_bar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFailure(String message) {
        progress_bar.setVisibility(View.INVISIBLE);
        Snackbar.make(rootLayout,message,Snackbar.LENGTH_LONG)
                .setAction("Close", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .show();
    }

    @Override
    public void onClick(View view) {
        if (view == textView_NeedAccount){
            finish();
            Intent intent = new Intent(this,SignupActivity.class);
            startActivity(intent);
        }
    }
}
