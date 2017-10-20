package com.example.pierre.jardin.Fourniture;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.example.pierre.jardin.R;
import com.example.pierre.jardin.api.FournitureAPI;
import com.parse.ParseObject;

import java.util.ArrayList;

public class PageFourniture extends AppCompatActivity {

    private Button gNouveauFourniture;
    private android.widget.SearchView gSearchViewFourniture;
    private RecyclerView gRecyclerViewFourniture;
    private RecyclerView.Adapter gAdapterFourniture;
    private RecyclerView.LayoutManager gLayoutManagerFourniture;
    private FournitureAPI fournitureAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_fourniture);
        gNouveauFourniture = (Button) findViewById(R.id.newFournitureButton);
        gSearchViewFourniture = (android.widget.SearchView) findViewById(R.id.SearchFourniture);

        fournitureAPI = new FournitureAPI();

        gRecyclerViewFourniture = (RecyclerView) findViewById(R.id.recyclerviewFourniture);
        gRecyclerViewFourniture.setHasFixedSize(true);

        gSearchViewFourniture.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                affiche(fournitureAPI.getFournituresByName(query));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

        gNouveauFourniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickNewFourniture();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        affiche(fournitureAPI.getFournitures());

    }

    private void affiche (ArrayList<ParseObject> listFourniture){
        gLayoutManagerFourniture = new LinearLayoutManager(this);
        gRecyclerViewFourniture.setLayoutManager(gLayoutManagerFourniture);
        gAdapterFourniture = new FournitureAdapter(listFourniture);
        gRecyclerViewFourniture.setAdapter(gAdapterFourniture);
    }

    private void onClickNewFourniture (){
        Intent myIntent = new Intent(PageFourniture.this, NewFourniture.class);
        //myIntent.putExtra("key", value); //Optional parameters
        this.startActivity(myIntent);
    }
}
