package com.example.pierre.jardin.Facture;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.example.pierre.jardin.R;
import com.example.pierre.jardin.api.FactureAPI;
import com.parse.ParseObject;

import java.util.ArrayList;

public class PageFacture extends AppCompatActivity {

    private Button gNouveauFacture;
    private android.widget.SearchView gSearchViewFacture;
    private RecyclerView gRecyclerViewFacture;
    private RecyclerView.Adapter gAdapterFacture;
    private RecyclerView.LayoutManager gLayoutManagerFacture;
    private FactureAPI factureAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_facture);
        gNouveauFacture = (Button) findViewById(R.id.newFactureButton);
        gSearchViewFacture = (android.widget.SearchView) findViewById(R.id.SearchFacture);

        factureAPI = new FactureAPI();

        gRecyclerViewFacture = (RecyclerView) findViewById(R.id.recyclerviewFacture);
        gRecyclerViewFacture.setHasFixedSize(true);

        gSearchViewFacture.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                affiche(factureAPI.getFacturesByName(query));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

        gNouveauFacture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickNewFacture();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        affiche(factureAPI.getFactures());

    }

    private void affiche (ArrayList<ParseObject> listFacture){
        gLayoutManagerFacture = new LinearLayoutManager(this);
        gRecyclerViewFacture.setLayoutManager(gLayoutManagerFacture);
        gAdapterFacture = new FactureAdapter(listFacture);
        gRecyclerViewFacture.setAdapter(gAdapterFacture);
    }

    private void onClickNewFacture (){
        Intent myIntent = new Intent(PageFacture.this, NewFacture.class);
        //myIntent.putExtra("key", value); //Optional parameters
        this.startActivity(myIntent);
    }
}
