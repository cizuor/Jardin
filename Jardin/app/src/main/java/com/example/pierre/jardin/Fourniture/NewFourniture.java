package com.example.pierre.jardin.Fourniture;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pierre.jardin.R;
import com.example.pierre.jardin.api.FournitureAPI;
import com.example.pierre.jardin.api.MaterielAPI;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class NewFourniture extends AppCompatActivity {

    private EditText editNom;
    private EditText editPrix;
    private Button valider;
    private ParseObject fourniture;
    private boolean isModif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_fourniture);
        try {
            fourniture = (ParseObject) getIntent().getExtras().get("Fourniture");
        }catch (NullPointerException e){
        }
        editNom = (EditText) findViewById(R.id.editNewFournitureNom);
        editPrix = (EditText) findViewById(R.id.editNewFourniturePrix);
        valider= (Button) findViewById(R.id.buttonvalider);


        if (fourniture!= null){
            isModif = true;
            editNom.setText(fourniture.getString(FournitureAPI.COLUMN_NOM));
            editPrix.setText(Integer.toString(fourniture.getInt(FournitureAPI.COLUMN_PRIX)));
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
            fourniture = new ParseObject(FournitureAPI.TABLE_FOURNITURE);
        }
        try {
            fourniture.put(FournitureAPI.COLUMN_NOM, editNom.getText().toString());
            fourniture.put(FournitureAPI.COLUMN_PRIX,Integer.parseInt(editPrix.getText().toString()));
            fourniture.saveInBackground(new SaveCallback() {
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
            toast = Toast.makeText(this, "fourniture sauvegarder", Toast.LENGTH_LONG);
            this.finish();
        }else{
            toast = Toast.makeText(this, "Une Erreur est survenue", Toast.LENGTH_LONG);
        }
        toast.show();
    }
}
