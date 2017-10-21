package com.example.pierre.jardin.Client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import com.example.pierre.jardin.R;
import com.example.pierre.jardin.api.ClientAPI;
import com.example.pierre.jardin.api.FactureAPI;
import com.parse.ParseObject;

import java.util.ArrayList;

public class PageClient extends AppCompatActivity {


    private Button gNouveauClient;
    private android.widget.SearchView gSearchViewClient;
    private RecyclerView gRecyclerViewClient;
    private RecyclerView.Adapter gAdapter;
    private RecyclerView.LayoutManager gLayoutManager;
    private EditText geditSearch;
    private Boolean fromFacture;
    private final ClientAPI clientAPI = new ClientAPI();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_client);
        try {
            fromFacture =(Boolean) getIntent().getExtras().get("Facture");
        }catch (NullPointerException e){
            fromFacture = false;
        }


        Log.d("test","test facture = "+fromFacture);

        gNouveauClient = (Button) findViewById(R.id.newClientButton);
        gSearchViewClient = (android.widget.SearchView) findViewById(R.id.SearchClient);
        gRecyclerViewClient = (RecyclerView) findViewById(R.id.recyclerviewclient);
        gRecyclerViewClient.setHasFixedSize(true);


        gSearchViewClient.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
             @Override
             public boolean onQueryTextSubmit(String query) {
                 Log.d("test","test "+query);
                 affiche(clientAPI.getClientsByName(query));
                 return false;
             }

             @Override
             public boolean onQueryTextChange(String newText) {
                 return false;
             }

         });

        gNouveauClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickNewClient();
            }
        });

        affiche(clientAPI.getClients());

    }
    @Override
    protected void onResume() {
        super.onResume();
        affiche(clientAPI.getClients());

    }


    private void affiche (ArrayList<ParseObject> listClient){
        gLayoutManager = new LinearLayoutManager(this);
        gRecyclerViewClient.setLayoutManager(gLayoutManager);
        gAdapter = new ClientAdapter(listClient,fromFacture,this);
        gRecyclerViewClient.setAdapter(gAdapter);
    }


    private void onClickNewClient (){
        Intent myIntent = new Intent(PageClient.this, NewClient.class);
        //myIntent.putExtra("key", value); //Optional parameters
        this.startActivity(myIntent);
    }

}
