package com.example.pierre.jardin.Materiel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pierre.jardin.Chantier.ChantierAdapter;
import com.example.pierre.jardin.R;
import com.example.pierre.jardin.api.ChantierAPI;
import com.example.pierre.jardin.api.MaterielAPI;
import com.parse.ParseObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MaterielDetail extends AppCompatActivity {

    private TextView gNom;

    private Button gModifier;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");


    private RecyclerView gRecyclerViewChantier;
    private RecyclerView.Adapter gAdapter;
    private RecyclerView.LayoutManager gLayoutManager;

    private ParseObject materiel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materiel_detail);
        materiel = (ParseObject) getIntent().getExtras().get("Materiel");
        gNom = (TextView) findViewById(R.id.materielDetailNom);
        gModifier = (Button) findViewById(R.id.materielDetailModifier);


        gNom.setText(materiel.getString(MaterielAPI.COLUMN_NOM));

        gModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickModifier();
            }
        });


        ChantierAPI chantierAPI = new ChantierAPI();
        gRecyclerViewChantier = (RecyclerView) findViewById(R.id.recyclerviewchantier);
        gRecyclerViewChantier.setHasFixedSize(true);
        ArrayList<ParseObject> listchantier = new ArrayList<>();
        listchantier = chantierAPI.getChantierFromMateriel(materiel);

        gLayoutManager = new LinearLayoutManager(this);
        gRecyclerViewChantier.setLayoutManager(gLayoutManager);
        gAdapter = new ChantierAdapter(listchantier);
        gRecyclerViewChantier.setAdapter(gAdapter);
    }



    private void onClickModifier(){
        Intent myIntent = new Intent(MaterielDetail.this, NewMateriel.class);
        myIntent.putExtra("Materiel", materiel ); //Optional parameters
        this.startActivity(myIntent);
    }
}
