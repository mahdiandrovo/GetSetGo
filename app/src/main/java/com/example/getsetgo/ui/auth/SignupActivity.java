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
import com.example.getsetgo.data.repositories.SignupRepository;
import com.example.getsetgo.databinding.ActivitySignupBinding;
import com.example.getsetgo.ui.auth.listener.SignupListener;
import com.example.getsetgo.ui.home.HomeActivity;
import com.example.getsetgo.ui.model.SignupModel;
import com.example.getsetgo.ui.viewmodel.SignupViewModel;
import com.example.getsetgo.ui.viewmodel.viewmodelfactory.SignupViewModelFactory;
import com.example.getsetgo.util.ViewUtils;
import com.google.android.material.snackbar.Snackbar;

public class SignupActivity extends AppCompatActivity implements SignupListener, View.OnClickListener {

    private LinearLayout linearLayout_RootLayout;
    private ProgressBar progressBar;
    private TextView textView_HaveAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySignupBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_signup);

        AppDatabase appDatabase = AppDatabase.getDatabase(this);
        SignupRepository signupRepository = new SignupRepository(appDatabase);

        SignupViewModel signupViewModel = ViewModelProviders.of(this, new SignupViewModelFactory(this,new SignupModel(),signupRepository)).get(SignupViewModel.class);
        binding.setSignupviewmodel(signupViewModel);

        signupViewModel.signupListener = this;

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        linearLayout_RootLayout = (LinearLayout) findViewById(R.id.signup_root_layout);
        textView_HaveAccount = (TextView) findViewById(R.id.haveAnAccount_Texview);
        textView_HaveAccount.setOnClickListener(this);

        signupRepository.getLoggedInUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user!=null){
                    finish();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onStarted() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess(String message) {
        progressBar.setVisibility(View.INVISIBLE);
        ViewUtils.toast(message,this);
    }

    @Override
    public void onFailure(String message) {
        progressBar.setVisibility(View.INVISIBLE);
        Snackbar.make(linearLayout_RootLayout,message,Snackbar.LENGTH_LONG)
                .setAction("Close", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .show();
    }

    @Override
    public void onClick(View view) {
        if (view == textView_HaveAccount){
            finish();
            Intent intent = new Intent(this,SigninActivity.class);
            startActivity(intent);
        }
    }
}
