package com.example.getsetgo.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.getsetgo.R;
import com.example.getsetgo.data.network.responses.Place;
import com.example.getsetgo.data.repositories.HomeRepository;
import com.example.getsetgo.databinding.ActivityHomeBinding;
import com.example.getsetgo.ui.activity.ViewPlaceActivity;
import com.example.getsetgo.ui.adapter.PlaceViewAdapter;
import com.example.getsetgo.ui.viewmodel.HomeViewModel;
import com.example.getsetgo.ui.viewmodel.viewmodelfactory.HomeViewModelFactory;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.List;

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
                adapter.setOnItemClickListener(new PlaceViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Place place) {
                        if (isGooglePlayServicesOk()){
                            Intent intent = new Intent(HomeActivity.this, ViewPlaceActivity.class);
                            intent.putExtra(ViewPlaceActivity.EXTRA_PLACE_NAME,place.getName());
                            intent.putExtra(ViewPlaceActivity.EXTRA_PLACE_LOCARION,place.getLocation());
                            intent.putExtra(ViewPlaceActivity.EXTRA_PLACE_DESCRIPTION,place.getDescription());
                            startActivity(intent);
                        }
                    }
                });
            }
        });

    }

    private boolean isGooglePlayServicesOk(){
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int result = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if (result == ConnectionResult.SUCCESS){
            return true;
        } else if (googleApiAvailability.isUserResolvableError(result)){
            Toast.makeText(this,"Please Update Your Google Play Service",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this,"Google Play Service Required For This Application",Toast.LENGTH_LONG).show();
        }
        return false;
    }

}
