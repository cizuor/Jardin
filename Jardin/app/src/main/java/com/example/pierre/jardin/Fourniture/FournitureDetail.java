package com.example.pierre.jardin.Fourniture;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pierre.jardin.R;
import com.example.pierre.jardin.api.FournitureAPI;
import com.example.pierre.jardin.api.NBFournitureAPI;
import com.parse.ParseException;
import com.parse.ParseObject;

public class FournitureDetail extends AppCompatActivity {

    private TextView nomFourniture;
    private TextView nombreFournitureVendu;
    private TextView prixFourniture;
    private Button modifier;
    private ParseObject fourniture;
    private NBFournitureAPI nbFournitureAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourniture_detail);
        fourniture = (ParseObject) getIntent().getExtras().get("Fourniture");
        nomFourniture = (TextView) findViewById(R.id.fournitureDetailNom);
        prixFourniture = (TextView) findViewById(R.id.fournitureDetailprix) ;
        nombreFournitureVendu= (TextView) findViewById(R.id.fournitureDetailNombre);
        modifier = (Button) findViewById(R.id.fournitureDetailModifier) ;
        nbFournitureAPI = new NBFournitureAPI();

        nomFourniture.setText(fourniture.getString(FournitureAPI.COLUMN_NOM));
        prixFourniture.setText(Integer.toString(fourniture.getInt(FournitureAPI.COLUMN_PRIX)));
        nombreFournitureVendu.setText(Integer.toString(nbFournitureAPI.getNBFournitureVendu(fourniture)));

        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickModifier();
            }
        });
    }
    void OnClickModifier(){
        Intent myIntent = new Intent(FournitureDetail.this, NewFourniture.class);
        myIntent.putExtra("Fourniture", fourniture ); //Optional parameters
        this.startActivity(myIntent);
    }






}
