package com.example.pierre.jardin.Facture;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pierre.jardin.Client.NewClient;
import com.example.pierre.jardin.Client.PageClient;
import com.example.pierre.jardin.R;
import com.example.pierre.jardin.api.ClientAPI;
import com.example.pierre.jardin.api.FactureAPI;
import com.example.pierre.jardin.api.NumFactureAPI;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewFacture extends AppCompatActivity {

    private EditText nomFacture;
    private EditText prixFacture;
    private DatePicker dateFacture;
    private CheckBox isNum;
    private CheckBox isPayer;
    private Button clientButton;
    private Button valider;
    private ParseObject facture;
    private boolean isModif;
    private boolean isAlreadyNum;
    private NumFactureAPI numFactureAPI;
    private ParseObject client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_facture);

        try {
            facture = (ParseObject) getIntent().getExtras().get("Facture");
        }catch (NullPointerException e){
        }

        nomFacture = (EditText) findViewById(R.id.editNomNewFacture);
        prixFacture = (EditText) findViewById(R.id.editPrixNewFacture);
        valider= (Button) findViewById(R.id.validerNewFacture);
        dateFacture = (DatePicker) findViewById(R.id.datePickerNewFacture);
        isNum = (CheckBox) findViewById(R.id.checkBoxNumFacture);
        isPayer = (CheckBox) findViewById(R.id.checkBoxIsPayer);
        clientButton = (Button) findViewById(R.id.buttonClientNewFacture) ;

        numFactureAPI = new NumFactureAPI();
        if (facture!= null){
            isModif = true;
            nomFacture.setText(facture.getString(FactureAPI.COLUMN_NOM));
            prixFacture.setText(Integer.toString(facture.getInt(FactureAPI.COLUMN_PRIX)));
            isPayer.setChecked(facture.getBoolean(FactureAPI.COLUMN_PAYER));
            if (facture.getInt(FactureAPI.COLUMN_NUM)!=0){
                isNum.setChecked(true);
                isAlreadyNum = true;
            }
            client = facture.getParseObject(FactureAPI.COLUMN_CLIENT);
            clientButton.setText(client.getString(ClientAPI.COLUMN_NOM));
            SimpleDateFormat year = new SimpleDateFormat("yyyy");
            SimpleDateFormat month = new SimpleDateFormat("MM");
            SimpleDateFormat day = new SimpleDateFormat("dd");
            int y = Integer.parseInt(year.format(facture.getDate(FactureAPI.COLUMN_DATE).getTime()));
            int m = Integer.parseInt(month.format(facture.getDate(FactureAPI.COLUMN_DATE).getTime()))-1;
            int d = Integer.parseInt(day.format(facture.getDate(FactureAPI.COLUMN_DATE).getTime()));
            dateFacture.updateDate(y,m,d);
        }

        clientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickClient();
            }
        });

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickValider();
            }
        });


    }

    void OnClickClient () {
        Intent myIntent = new Intent(NewFacture.this, PageClient.class);
        myIntent.putExtra("Facture", true); //Optional parameters
        this.startActivityForResult(myIntent,1);
    }

    void OnClickValider (){
        if (!isModif) {
            facture = new ParseObject(FactureAPI.TABLE_FACTURE);
        }
        try {
            facture.put(FactureAPI.COLUMN_NOM, nomFacture.getText().toString());
            facture.put(FactureAPI.COLUMN_PRIX,Integer.parseInt(prixFacture.getText().toString()));
            facture.put(FactureAPI.COLUMN_PAYER,isPayer.isChecked());
            facture.put(FactureAPI.COLUMN_CLIENT,client);
            if (isNum.isChecked()) {
                if (isAlreadyNum){
                }else {
                    facture.put(FactureAPI.COLUMN_NUM, numFactureAPI.getNumFacture());
                }
            }else {
                facture.put(FactureAPI.COLUMN_NUM,0);
            }
            Date mydate = new Date(dateFacture.getYear()-1900,dateFacture.getMonth(),dateFacture.getDayOfMonth());
            facture.put(FactureAPI.COLUMN_DATE,mydate);


            facture.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    result(e);
                }
            });
        } catch (IllegalArgumentException e) {
            Toast toast;
            toast = Toast.makeText(this, "données mal remplies", Toast.LENGTH_LONG);
            toast.show();
        }
    }



    private void result(ParseException e) {
        Toast toast;
        if (e==null) {
            if (isNum.isChecked()) {
                if (!isAlreadyNum) {
                    numFactureAPI.incrementeFacture();
                }
            }else{
                if (isAlreadyNum) {
                    toast = Toast.makeText(this, "la facture ne peut pas étre dénumeroter ", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
            toast = Toast.makeText(this, "facture sauvegarder", Toast.LENGTH_LONG);
            this.finish();
        }else{
            toast = Toast.makeText(this, "Une Erreur est survenue", Toast.LENGTH_LONG);
        }
        toast.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==1)
        {
            Bundle message=data.getExtras();
            client = (ParseObject)  message.get("MESSAGE");
            clientButton.setText(client.getString(ClientAPI.COLUMN_NOM));
        }
    }
}
