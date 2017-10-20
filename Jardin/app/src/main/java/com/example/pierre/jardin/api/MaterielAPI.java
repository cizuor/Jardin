package com.example.pierre.jardin.api;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;

/**
 * Created by pierre on 17/10/2017.
 */

public class MaterielAPI {

    public static final String TABLE_MATERIEL = "Materiel";
    public static final String COLUMN_NOM = "Nom";
    public static final String COLUMN_CHANTIERS = "Chantiers";

    public ArrayList<ParseObject> getMateriels (){
        ParseQuery<ParseObject > query = ParseQuery.getQuery(TABLE_MATERIEL);
        ArrayList<ParseObject> listMateriel = new ArrayList<ParseObject>();
        try {
            listMateriel = new ArrayList<>(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listMateriel;
    }



    public ArrayList<ParseObject> getMaterielsByName(String nom){
        ParseQuery<ParseObject > query = ParseQuery.getQuery(TABLE_MATERIEL);
        query.whereMatches(COLUMN_NOM,nom);
        ArrayList<ParseObject> listMateriel = new ArrayList<ParseObject>();
        try {
            listMateriel = new ArrayList<>(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listMateriel;
    }


}
