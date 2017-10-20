package com.example.pierre.jardin.Employer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pierre.jardin.JourChomer.JourChomerAdapter;
import com.example.pierre.jardin.R;
import com.example.pierre.jardin.api.EmployerAPI;
import com.example.pierre.jardin.api.JourChomerAPI;
import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.ArrayList;

public class EmployerDetail extends AppCompatActivity {


    private TextView gNom;
    private TextView gNum;
    private TextView gMail;
    private TextView gAdresse;
    private TextView gSecu;
    private TextView gIBan;

    private Button gModifier;


    private RecyclerView gRecyclerViewJourChomer;
    private RecyclerView.Adapter gAdapter;
    private RecyclerView.LayoutManager gLayoutManager;


    private ParseObject employer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_detail);
        employer = (ParseObject) getIntent().getExtras().get("Employer");


        gNom = (TextView) findViewById(R.id.employerDetailNom);
        gNum = (TextView) findViewById(R.id.employerDetailNum);
        gAdresse = (TextView) findViewById(R.id.employerDetailAdresse);
        gMail = (TextView) findViewById(R.id.employerDetailMail);
        gIBan = (TextView) findViewById(R.id.employerDetailIBan);
        gSecu = (TextView) findViewById(R.id.employerDetailSecu);
        gModifier = (Button) findViewById(R.id.employerDetailModifier);






    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            employer.fetch();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        gNom.setText(employer.getString(EmployerAPI.COLUMN_NOM));
        gNum.setText(Integer.toString(employer.getInt(EmployerAPI.COLUMN_NUM)));
        gAdresse.setText(employer.getString(EmployerAPI.COLUMN_ADRESSE));
        gMail.setText(employer.getString(EmployerAPI.COLUMN_MAIL));
        gSecu.setText(Integer.toString(employer.getInt(EmployerAPI.COLUMN_NUM_SECU)));
        gIBan.setText(employer.getString(EmployerAPI.COLUMN_IBAN));
        gModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickModifier();
            }
        });

        gRecyclerViewJourChomer = (RecyclerView) findViewById(R.id.recyclerviewJourChomer);
        gRecyclerViewJourChomer.setHasFixedSize(true);


        JourChomerAPI jourChomerAPI = new JourChomerAPI();

        ArrayList<ParseObject> listJourChomer = new ArrayList<>();
        listJourChomer = jourChomerAPI.getJourChomerFromEmployer(employer);

        gLayoutManager = new LinearLayoutManager(this);
        gRecyclerViewJourChomer.setLayoutManager(gLayoutManager);
        gAdapter = new JourChomerAdapter(listJourChomer);
        gRecyclerViewJourChomer.setAdapter(gAdapter);

    }

    private void onClickModifier(){
        Intent myIntent = new Intent(EmployerDetail.this, NewEmployer.class);
        myIntent.putExtra("Employer", employer ); //Optional parameters
        this.startActivity(myIntent);
    }
}
