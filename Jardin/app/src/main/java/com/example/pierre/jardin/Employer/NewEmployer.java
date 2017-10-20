package com.example.pierre.jardin.Employer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pierre.jardin.R;
import com.example.pierre.jardin.api.EmployerAPI;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class NewEmployer extends AppCompatActivity {


    private EditText gNomEmployer;
    private EditText gNumEmployer;
    private EditText gAdresseEmployer;
    private EditText gMailEmployer;
    private EditText gNumSecuEmployer;
    private EditText gIBanEmployer;
    private Button gValider;

    private ParseObject gEmployer = null;
    private Boolean isModif=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_employer);

        try {
            gEmployer = (ParseObject) getIntent().getExtras().get("Employer");
        }catch (NullPointerException e){
        }


        gNomEmployer = (EditText) findViewById(R.id.editNomNewEmployer);
        gNumEmployer = (EditText) findViewById(R.id.editNumNewEmployer);
        gAdresseEmployer = (EditText) findViewById(R.id.editAdresseNewEmployer);
        gMailEmployer = (EditText) findViewById(R.id.editMailNewEmployer);
        gNumSecuEmployer = (EditText) findViewById(R.id.editSecuNewEmployer);
        gIBanEmployer = (EditText) findViewById(R.id.editIbanNewEmployer);
        gValider = (Button) findViewById(R.id.validerNewEmployer);

        if (gEmployer!= null){
            isModif = true;


            gNomEmployer.setText(gEmployer.getString(EmployerAPI.COLUMN_NOM));
            gNumEmployer.setText(Integer.toString(gEmployer.getInt(EmployerAPI.COLUMN_NUM)));
            gAdresseEmployer.setText(gEmployer.getString(EmployerAPI.COLUMN_ADRESSE));
            gMailEmployer.setText(gEmployer.getString(EmployerAPI.COLUMN_ADRESSE));
            gNumSecuEmployer.setText(Integer.toString(gEmployer.getInt(EmployerAPI.COLUMN_NUM_SECU)));
            gIBanEmployer.setText(gEmployer.getString(EmployerAPI.COLUMN_IBAN));
        }
        gValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickValider();
            }
        });

    }


    private void onClickValider(){
        if (!isModif) {
            gEmployer = new ParseObject(EmployerAPI.TABLE_EMPLOYER);
        }
        try {
            gEmployer.put(EmployerAPI.COLUMN_NOM, gNomEmployer.getText().toString());
            gEmployer.put(EmployerAPI.COLUMN_NUM, Integer.parseInt(gNumEmployer.getText().toString()));
            gEmployer.put(EmployerAPI.COLUMN_ADRESSE, gAdresseEmployer.getText().toString());
            gEmployer.put(EmployerAPI.COLUMN_MAIL, gMailEmployer.getText().toString());
            gEmployer.put(EmployerAPI.COLUMN_NUM_SECU, Integer.parseInt(gNumSecuEmployer.getText().toString()));
            gEmployer.put(EmployerAPI.COLUMN_IBAN, gIBanEmployer.getText().toString());
            gEmployer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    result(e);
                }
            });
        } catch (NumberFormatException e) {
            Toast toast;
            toast = Toast.makeText(this, "données mal remplies", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private void result (ParseException e){
        Toast toast;
        if (e==null) {
            toast = Toast.makeText(this, "Employer sauvegarder", Toast.LENGTH_LONG);
            this.finish();
        }else{
            toast = Toast.makeText(this, "Une Erreur est survenue", Toast.LENGTH_LONG);
        }
        toast.show();
    }
}
