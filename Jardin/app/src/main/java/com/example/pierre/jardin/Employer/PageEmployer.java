package com.example.pierre.jardin.Employer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.example.pierre.jardin.R;
import com.example.pierre.jardin.api.EmployerAPI;
import com.parse.ParseObject;

import java.util.ArrayList;

public class PageEmployer extends AppCompatActivity {

    private Button gNouveauEmployer;
    private android.widget.SearchView gSearchViewEmployer;
    private RecyclerView gRecyclerViewEmployer;
    private RecyclerView.Adapter gAdapterEmployer;
    private RecyclerView.LayoutManager gLayoutManagerEmployer;
    private EmployerAPI employerAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_employer);

        employerAPI = new EmployerAPI();

        gNouveauEmployer = (Button) findViewById(R.id.newEmployerButton);
        gSearchViewEmployer = (android.widget.SearchView) findViewById(R.id.SearchEmployer);



        gRecyclerViewEmployer = (RecyclerView) findViewById(R.id.recyclerviewEmployer);
        gRecyclerViewEmployer.setHasFixedSize(true);

        gSearchViewEmployer.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("test","test "+query);
                affiche(employerAPI.getEmployersByName(query));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

        gNouveauEmployer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickNewEmployer();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        affiche(employerAPI.getEmployers());

    }

    private void affiche (ArrayList<ParseObject> listClient){
        gLayoutManagerEmployer = new LinearLayoutManager(this);
        gRecyclerViewEmployer.setLayoutManager(gLayoutManagerEmployer);
        gAdapterEmployer = new EmployerAdapter(listClient);
        gRecyclerViewEmployer.setAdapter(gAdapterEmployer);
    }

    private void onClickNewEmployer (){
        Intent myIntent = new Intent(PageEmployer.this, NewEmployer.class);
        //myIntent.putExtra("key", value); //Optional parameters
        this.startActivity(myIntent);
    }
}
