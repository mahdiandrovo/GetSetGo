package com.example.getsetgo.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

import com.example.getsetgo.R;
import com.example.getsetgo.databinding.ActivityViewPlaceBinding;
import com.example.getsetgo.ui.home.HomeActivity;
import com.example.getsetgo.ui.model.PlaceModel;
import com.example.getsetgo.ui.viewmodel.ViewPlaceViewModel;
import com.example.getsetgo.ui.viewmodel.viewmodelfactory.ViewPlaceViewModelFactory;

public class ViewPlaceActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private GestureDetector gestureDetector;
    private float x1;
    private float x2;
    private float y1;
    private float y2;
    private static int MIN_DISTANCE = 150;

    private ViewFlipper viewFlipper;

    public static final String EXTRA_PLACE_NAME = "com.example.getsetgo.EXTRA_PLACE_NAME";
    public static final String EXTRA_PLACE_LOCARION = "com.example.getsetgo.EXTRA_PLACE_LOCARION";
    public static final String EXTRA_PLACE_DESCRIPTION = "com.example.getsetgo.EXTRA_PLACE_DESCRIPTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_place);
        ActivityViewPlaceBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_view_place);

        Intent data = getIntent();
        String name = data.getStringExtra(EXTRA_PLACE_NAME);
        String location = data.getStringExtra(EXTRA_PLACE_LOCARION);
        String description = data.getStringExtra(EXTRA_PLACE_DESCRIPTION);
        PlaceModel placeModel = new PlaceModel(name,location,description);

        ViewPlaceViewModel viewPlaceViewModel = ViewModelProviders.of(this,new ViewPlaceViewModelFactory(this,placeModel)).get(ViewPlaceViewModel.class);
        binding.setViewplaceviewmodel(viewPlaceViewModel);

        gestureDetector = new GestureDetector(ViewPlaceActivity.this,this);

        viewFlipper = (ViewFlipper) findViewById(R.id.placeDetails_ViewFlipper_ViewPlace);



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ViewPlaceActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        gestureDetector.onTouchEvent(event);

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();

                float valueX = x2-x1;
                float valueY = y2-y1;

                if (Math.abs(valueX) > MIN_DISTANCE){
                    //Left to Right Swipe
                    if (x2>x1){
                        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
                        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);
                        viewFlipper.showNext();
                    }
                    //Right to Left Swipe
                    if (x1>x2){


                        viewFlipper.setInAnimation(this,R.anim.slide_in_right);
                        viewFlipper.setOutAnimation(this,R.anim.slide_out_left);
                        viewFlipper.showPrevious();
                    }
                }

                if (Math.abs(valueY) > MIN_DISTANCE){
                    //Top to Bottom Swipe
                    if (y2>y1){

                    }
                    //Bottom to Top Swipe
                    if (y1>y2){

                    }
                }

        }

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

}
