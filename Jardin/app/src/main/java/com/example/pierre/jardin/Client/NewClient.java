package com.example.pierre.jardin.Client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pierre.jardin.R;
import com.example.pierre.jardin.api.ClientAPI;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class NewClient extends AppCompatActivity {


    private EditText gNomClient;
    private EditText gNumClient;
    private EditText gAdresseClient;
    private EditText gMailClient;
    private Button gValider;

    private ParseObject gclient = null;
    private Boolean isModif=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_client);

        try {
            gclient = (ParseObject) getIntent().getExtras().get("Client");
        }catch (NullPointerException e){

        }
        gNomClient = (EditText) findViewById(R.id.editNomClient);
        gNumClient = (EditText) findViewById(R.id.editNumClient);
        gAdresseClient = (EditText) findViewById(R.id.editAdresseClient);
        gMailClient = (EditText) findViewById(R.id.editMailClient);
        gValider = (Button) findViewById(R.id.validerNewClient);

        if (gclient!= null){
            isModif = true;
            gNomClient.setText(gclient.getString(ClientAPI.COLUMN_NOM));
            gNumClient.setText(Integer.toString(gclient.getInt(ClientAPI.COLUMN_NUM)));
            gAdresseClient.setText(gclient.getString(ClientAPI.COLUMN_ADRESSE));
            gMailClient.setText(gclient.getString(ClientAPI.COLUMN_MAIL));
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
            gclient = new ParseObject(ClientAPI.TABLE_CLIENT);
        }
        try {
            gclient.put(ClientAPI.COLUMN_NOM, gNomClient.getText().toString());
            gclient.put(ClientAPI.COLUMN_NUM, Integer.parseInt(gNumClient.getText().toString()));
            gclient.put(ClientAPI.COLUMN_ADRESSE, gAdresseClient.getText().toString());
            gclient.put(ClientAPI.COLUMN_MAIL, gMailClient.getText().toString());
            gclient.saveInBackground(new SaveCallback() {
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
            toast = Toast.makeText(this, "client sauvegarder", Toast.LENGTH_LONG);
        }else{
            toast = Toast.makeText(this, "Une Erreur est survenue", Toast.LENGTH_LONG);
        }
        toast.show();
    }






}
