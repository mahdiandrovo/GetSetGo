package com.example.getsetgo.util;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class ViewUtils {
    static public void toast(String message, Context context){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
    static public void snackbar(View view,String message){
        Snackbar.make(view,message,Snackbar.LENGTH_LONG)
                .setAction("Close", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .show();
    }

}
