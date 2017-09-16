package com.example.pierre.jardin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pierre.jardin.Client.PageClient;
import com.parse.Parse;

public class Menu extends AppCompatActivity {


    private Button gButtonClient;
    private Button gButtonEmployer;
    private Button gButtonMateriel;
    private Button gButtonFourniture;
    private Button gButtonFicheChantier;


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


        gButtonClient = (Button) findViewById(R.id.clientButton);
        gButtonEmployer = (Button) findViewById(R.id.employerButton);
        gButtonMateriel = (Button) findViewById(R.id.materielButton);
        gButtonFourniture = (Button) findViewById(R.id.fournitureButton);
        gButtonFicheChantier = (Button) findViewById(R.id.newFicheButton);



        gButtonClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClientClick();
            }
        });




        /*ParseQuery<ParseObject > query = ParseQuery.getQuery("Client");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + scoreList );
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });*/
    }

    private void ClientClick (){
        Intent myIntent = new Intent(Menu.this, PageClient.class);
        //myIntent.putExtra("key", value); //Optional parameters
        this.startActivity(myIntent);
    }




}
