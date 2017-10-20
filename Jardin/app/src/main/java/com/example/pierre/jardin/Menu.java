package com.example.pierre.jardin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pierre.jardin.Client.PageClient;
import com.example.pierre.jardin.Employer.PageEmployer;
import com.example.pierre.jardin.Fourniture.PageFourniture;
import com.example.pierre.jardin.Materiel.PageMateriel;
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

        gButtonEmployer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmployerClick();
            }
        });

        gButtonMateriel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterielClick();
            }
        });


        gButtonFourniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FournitureClick();
            }
        });


    }

    private void ClientClick (){
        Intent myIntent = new Intent(Menu.this, PageClient.class);
        //myIntent.putExtra("key", value); //Optional parameters
        this.startActivity(myIntent);
    }

    private void EmployerClick (){
        Intent myIntent = new Intent(Menu.this, PageEmployer.class);
        //myIntent.putExtra("key", value); //Optional parameters
        this.startActivity(myIntent);
    }

    private void MaterielClick (){
        Intent myIntent = new Intent(Menu.this, PageMateriel.class);
        //myIntent.putExtra("key", value); //Optional parameters
        this.startActivity(myIntent);
    }
    private void FournitureClick (){
        Intent myIntent = new Intent(Menu.this, PageFourniture.class);
        //myIntent.putExtra("key", value); //Optional parameters
        this.startActivity(myIntent);
    }

}
