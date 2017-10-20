package com.example.pierre.jardin.Materiel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pierre.jardin.R;
import com.example.pierre.jardin.api.MaterielAPI;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class NewMateriel extends AppCompatActivity {

    private EditText editNom;
    private Button valider;
    private ParseObject materiel;
    private Boolean isModif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_materiel);

        try {
            materiel = (ParseObject) getIntent().getExtras().get("Materiel");
        }catch (NullPointerException e){
        }
        editNom = (EditText) findViewById(R.id.editNewMaterielNom);
        valider= (Button) findViewById(R.id.buttonvalider);


        if (materiel!= null){
            isModif = true;
            editNom.setText(materiel.getString(MaterielAPI.COLUMN_NOM));
        }

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickValider();
            }
        });
    }
    void OnClickValider (){
        if (!isModif) {
            materiel = new ParseObject(MaterielAPI.TABLE_MATERIEL);
        }
        try {
            materiel.put(MaterielAPI.COLUMN_NOM, editNom.getText().toString());
            materiel.saveInBackground(new SaveCallback() {
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

    private void result(ParseException e) {
        Toast toast;
        if (e==null) {
            toast = Toast.makeText(this, "materiel sauvegarder", Toast.LENGTH_LONG);
            this.finish();
        }else{
            toast = Toast.makeText(this, "Une Erreur est survenue", Toast.LENGTH_LONG);
        }
        toast.show();
    }
}
