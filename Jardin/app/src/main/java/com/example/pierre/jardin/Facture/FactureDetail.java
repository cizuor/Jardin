package com.example.pierre.jardin.Facture;

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
import com.example.pierre.jardin.api.ClientAPI;
import com.example.pierre.jardin.api.FactureAPI;
import com.parse.ParseObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FactureDetail extends AppCompatActivity {


    private TextView gNom;
    private TextView gNum;
    private TextView gPayer;
    private TextView gPrix;
    private TextView gDate;
    private TextView gClient;

    private Button gModifier;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");


    private RecyclerView gRecyclerViewChantier;
    private RecyclerView.Adapter gAdapter;
    private RecyclerView.LayoutManager gLayoutManager;

    private ParseObject facture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facture_detail);
        facture = (ParseObject) getIntent().getExtras().get("Facture");



        gNom = (TextView) findViewById(R.id.factureDetailNom);
        gNum = (TextView) findViewById(R.id.factureDetailNum);
        gPayer = (TextView) findViewById(R.id.factureDetailPayer);
        gDate = (TextView) findViewById(R.id.factureDetailDate);
        gPrix = (TextView) findViewById(R.id.factureDetailPrix);
        gClient = (TextView) findViewById(R.id.factureDetailClient);
        gModifier = (Button) findViewById(R.id.factureDetailModifier);




        gNom.setText(facture.getString(FactureAPI.COLUMN_NOM));
        gNum.setText(Integer.toString(facture.getInt(FactureAPI.COLUMN_NUM)));
        gClient.setText(facture.getParseObject(FactureAPI.COLUMN_CLIENT).getString(ClientAPI.COLUMN_NOM));
        try {
            gDate.setText(sdf.format(facture.getDate(FactureAPI.COLUMN_DATE)));
        }catch (NullPointerException e){

        }
        gPrix.setText(Integer.toString(facture.getInt(FactureAPI.COLUMN_PRIX))+"€");

        if (facture.getBoolean(FactureAPI.COLUMN_PAYER)){
            gPayer.setText("payer");
        }else{
            gPayer.setText("impayer");
        }

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
        listchantier = chantierAPI.getChantierFromFacture(facture);

        gLayoutManager = new LinearLayoutManager(this);
        gRecyclerViewChantier.setLayoutManager(gLayoutManager);
        gAdapter = new ChantierAdapter(listchantier);
        gRecyclerViewChantier.setAdapter(gAdapter);
    }




    private void onClickModifier(){
        Intent myIntent = new Intent(FactureDetail.this, NewFacture.class);
        myIntent.putExtra("Facture", facture ); //Optional parameters
        this.startActivity(myIntent);
    }
}
