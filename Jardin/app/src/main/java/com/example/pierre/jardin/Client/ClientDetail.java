package com.example.pierre.jardin.Client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pierre.jardin.R;
import com.example.pierre.jardin.api.ClientAPI;
import com.parse.ParseObject;

public class ClientDetail extends AppCompatActivity {

    private TextView gNom;
    private TextView gNum;
    private TextView gMail;
    private TextView gAdresse;

    private Button gModifier;


    private ParseObject client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_detail);
        client = (ParseObject) getIntent().getExtras().get("Client");

        gNom = (TextView) findViewById(R.id.clientDetailNom);
        gNum = (TextView) findViewById(R.id.clientDetailNum);
        gAdresse = (TextView) findViewById(R.id.clientDetailAdresse);
        gMail = (TextView) findViewById(R.id.clientDetailMail);
        gModifier = (Button) findViewById(R.id.clientDetailModifier);


        gNom.setText(client.getString(ClientAPI.COLUMN_NOM));
        gNum.setText(Integer.toString(client.getInt(ClientAPI.COLUMN_NUM)));
        gAdresse.setText(client.getString(ClientAPI.COLUMN_ADRESSE));
        gMail.setText(client.getString(ClientAPI.COLUMN_MAIL));
        gModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickModifier();
            }
        });




    }

    private void onClickModifier(){
        Intent myIntent = new Intent(ClientDetail.this, NewClient.class);
        myIntent.putExtra("Client", client ); //Optional parameters
        this.startActivity(myIntent);
    }
}
