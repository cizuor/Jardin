package com.example.pierre.jardin.Chantier;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.example.pierre.jardin.Employer.EmployerAdapter;
import com.example.pierre.jardin.Fourniture.FournitureChantierAdapter;
import com.example.pierre.jardin.R;
import com.example.pierre.jardin.api.ChantierAPI;
import com.example.pierre.jardin.api.EmployerAPI;
import com.example.pierre.jardin.api.NBFournitureAPI;
import com.parse.ParseObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ChantierDetail extends AppCompatActivity {



    private TextView gNom;
    private TextView gNBHeurs;
    private TextView gTempsTrajet;
    private TextView gTravaux;
    private TextView gDate;

    private Button gModifier;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");

    private RecyclerView gRecyclerViewFourniture;
    private RecyclerView.Adapter gAdapterFourniture;
    private RecyclerView.LayoutManager gLayoutManagerFourniture;

    private RecyclerView gRecyclerViewEmployer;
    private RecyclerView.Adapter gAdapterEmployer;
    private RecyclerView.LayoutManager gLayoutManagerEmployer;


    private EmployerAPI employerAPI;
    private NBFournitureAPI nbFournitureAPI;
    ParseObject chantier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chantier_detail);
        employerAPI = new EmployerAPI();
        nbFournitureAPI = new NBFournitureAPI();
        chantier = (ParseObject) getIntent().getExtras().get("Chantier");

        gNom = (TextView) findViewById(R.id.chantierDetailNom);
        gNBHeurs = (TextView) findViewById(R.id.chantierDetailNBHeurs);
        gTempsTrajet = (TextView) findViewById(R.id.chantierDetailTempsTrajet);
        gDate = (TextView) findViewById(R.id.chantierDetailDate);
        gTravaux = (TextView) findViewById(R.id.chantierDetailTravaux);
        gModifier = (Button) findViewById(R.id.chantierDetailModifier);


        gNom.setText(chantier.getString(ChantierAPI.COLUMN_NOM));
        gNBHeurs.setText(Integer.toString(chantier.getInt(ChantierAPI.COLUMN_NBHEURS)));
        gTempsTrajet.setText(Integer.toString(chantier.getInt(ChantierAPI.COLUMN_TRAJET)));
        gDate.setText(sdf.format(chantier.getDate(ChantierAPI.COLUMN_DATE)));
        gTravaux.setText(chantier.getString(ChantierAPI.COLUMN_TRAVAUX));



        //Employer
        gRecyclerViewEmployer = (RecyclerView) findViewById(R.id.recyclerviewEmployer);
        gRecyclerViewEmployer.setHasFixedSize(true);
        ArrayList<ParseObject> listEmployer = new ArrayList<>();
        listEmployer = employerAPI.getEmployerFromChantier(chantier);

        gLayoutManagerEmployer = new LinearLayoutManager(this);
        gRecyclerViewEmployer.setLayoutManager(gLayoutManagerEmployer);
        gAdapterEmployer = new EmployerAdapter(listEmployer);
        gRecyclerViewEmployer.setAdapter(gAdapterEmployer);




        //Fourniture
        gRecyclerViewFourniture = (RecyclerView) findViewById(R.id.recyclerviewFourniture);
        gRecyclerViewFourniture.setHasFixedSize(true);
        ArrayList<ParseObject> listFourniture = new ArrayList<>();
        listFourniture = nbFournitureAPI.getFournitureFromChantier(chantier);

        gLayoutManagerFourniture = new LinearLayoutManager(this);
        gRecyclerViewFourniture.setLayoutManager(gLayoutManagerFourniture);
        gAdapterFourniture = new FournitureChantierAdapter(listFourniture);
        gRecyclerViewFourniture.setAdapter(gAdapterFourniture);

    }
}
