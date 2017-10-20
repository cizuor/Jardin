package com.example.pierre.jardin.Materiel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.example.pierre.jardin.R;
import com.example.pierre.jardin.api.MaterielAPI;
import com.parse.ParseObject;

import java.util.ArrayList;

public class PageMateriel extends AppCompatActivity {


    private Button gNouveauMateriel;
    private android.widget.SearchView gSearchViewMateriel;
    private RecyclerView gRecyclerViewMateriel;
    private RecyclerView.Adapter gAdapterMateriel;
    private RecyclerView.LayoutManager gLayoutManagerMateriel;
    private MaterielAPI materielAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_materiel);

        materielAPI = new MaterielAPI();
        gNouveauMateriel = (Button) findViewById(R.id.newMaterielButton);
        gSearchViewMateriel = (android.widget.SearchView) findViewById(R.id.SearchMateriel);



        gRecyclerViewMateriel = (RecyclerView) findViewById(R.id.recyclerviewMateriel);
        gRecyclerViewMateriel.setHasFixedSize(true);

        gSearchViewMateriel.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                affiche(materielAPI.getMaterielsByName(query));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

        gNouveauMateriel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickNewMateriel();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        affiche(materielAPI.getMateriels());

    }

    private void affiche (ArrayList<ParseObject> listMateriel){
        gLayoutManagerMateriel = new LinearLayoutManager(this);
        gRecyclerViewMateriel.setLayoutManager(gLayoutManagerMateriel);
        gAdapterMateriel = new MaterielAdapter(listMateriel);
        gRecyclerViewMateriel.setAdapter(gAdapterMateriel);
    }

    private void onClickNewMateriel (){
        Intent myIntent = new Intent(PageMateriel.this, NewMateriel.class);
        //myIntent.putExtra("key", value); //Optional parameters
        this.startActivity(myIntent);
    }
}
