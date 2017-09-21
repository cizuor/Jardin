package com.example.pierre.jardin.Client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pierre.jardin.Facture.FactureAdapter;
import com.example.pierre.jardin.R;
import com.example.pierre.jardin.api.ClientAPI;
import com.example.pierre.jardin.api.FactureAPI;
import com.parse.ParseObject;

import java.util.ArrayList;

public class ClientDetail extends AppCompatActivity {

    private TextView gNom;
    private TextView gNum;
    private TextView gMail;
    private TextView gAdresse;

    private Button gModifier;


    private RecyclerView gRecyclerViewFacture;
    private RecyclerView.Adapter gAdapter;
    private RecyclerView.LayoutManager gLayoutManager;

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

        gRecyclerViewFacture = (RecyclerView) findViewById(R.id.recyclerviewfacture);
        gRecyclerViewFacture.setHasFixedSize(true);



        FactureAPI factureAPI = new FactureAPI();

        ArrayList<ParseObject> listFacture = new ArrayList<>();
        listFacture = factureAPI.getFactureFromClient(client);

        gLayoutManager = new LinearLayoutManager(this);
        gRecyclerViewFacture.setLayoutManager(gLayoutManager);
        gAdapter = new FactureAdapter(listFacture);
        gRecyclerViewFacture.setAdapter(gAdapter);

    }

    private void onClickModifier(){
        Intent myIntent = new Intent(ClientDetail.this, NewClient.class);
        myIntent.putExtra("Client", client ); //Optional parameters
        this.startActivity(myIntent);
    }
}
