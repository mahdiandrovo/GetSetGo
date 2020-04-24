package com.example.getsetgo.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.getsetgo.R;
import com.example.getsetgo.data.network.RetrofitClient;
import com.example.getsetgo.data.network.responses.Place;
import com.example.getsetgo.data.network.responses.PlacesResponse;
import com.example.getsetgo.data.repositories.HomeRepository;
import com.example.getsetgo.databinding.ActivityHomeBinding;
import com.example.getsetgo.ui.adapter.PlaceViewAdapter;
import com.example.getsetgo.ui.viewmodel.HomeViewModel;
import com.example.getsetgo.ui.viewmodel.SigninViewModel;
import com.example.getsetgo.ui.viewmodel.viewmodelfactory.HomeViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView_Places;
    private PlaceViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ActivityHomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        HomeRepository homeRepository = new HomeRepository();

        HomeViewModel homeViewModel = ViewModelProviders.of(this, new HomeViewModelFactory(this, homeRepository)).get(HomeViewModel.class);
        binding.setHomeviewmodel(homeViewModel);

        recyclerView_Places = (RecyclerView) findViewById(R.id.places_RecyclerView_Home);
        recyclerView_Places.setLayoutManager(new LinearLayoutManager(this));

        homeViewModel.allPlaces().observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> places) {
                adapter = new PlaceViewAdapter(places,getApplicationContext());
                recyclerView_Places.setAdapter(adapter);
            }
        });

    }
}
