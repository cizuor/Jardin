package com.example.pierre.jardin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("BElwMUaVRZMjafSeI9TSssvPfX2Ql6NMhH9SrKyb")
                .server("https://parseapi.back4app.com/")
                .clientKey("OG8MvGq3wYfHq9F0oWVYnYH5iBzY8dDHxokF5XBH")
                .build()
        );


        ParseQuery<ParseObject > query = ParseQuery.getQuery("Client");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + scoreList );
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });


    }
}
