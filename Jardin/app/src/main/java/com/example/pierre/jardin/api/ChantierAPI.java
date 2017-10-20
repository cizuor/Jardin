package com.example.pierre.jardin.api;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;

import java.util.ArrayList;

/**
 * Created by pierre on 22/09/2017.
 */

public class ChantierAPI {

    public static final String TABLE_CHANTIER = "Chantier";
    public static final String COLUMN_DATE = "Date";
    public static final String COLUMN_NBHEURS = "NBHeurs";
    public static final String COLUMN_TRAVAUX = "Travaux";
    public static final String COLUMN_TRAJET = "Trajet";
    public static final String COLUMN_FOURNITURE = "Fourniture";
    public static final String COLUMN_FACTURE = "Facture";
    public static final String COLUMN_EMPLOYER = "Employer";
    public static final String COLUMN_NOM = "Nom";




    public int getNBEmployer(ParseObject chantier){
        ArrayList<ParseObject> listEmployer = new ArrayList<>();
        ParseRelation relation = chantier.getRelation(COLUMN_EMPLOYER);
        ParseQuery query = relation.getQuery();
        try {
            listEmployer= new ArrayList<ParseObject>( query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listEmployer.size();
    }

    public ArrayList<ParseObject> getFourniture(ParseObject chantier){
        ArrayList<ParseObject> listFourniture = new ArrayList<>();
        ParseRelation relation = chantier.getRelation(COLUMN_FOURNITURE);
        ParseQuery query = relation.getQuery();
        try {
            listFourniture= new ArrayList<ParseObject>( query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listFourniture;
    }

    public ArrayList<ParseObject> getChantierFromFacture (ParseObject facture){
        ArrayList<ParseObject> listChantier = null;

        ParseRelation relation = facture.getRelation(FactureAPI.COLUMN_CHANTIERS);
        ParseQuery query = relation.getQuery();
        try {
            listChantier= new ArrayList<ParseObject>( query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listChantier;
    }

    public ArrayList<ParseObject> getChantierFromMateriel (ParseObject materiel){
        ArrayList<ParseObject> listChantier = null;

        ParseRelation relation = materiel.getRelation(MaterielAPI.COLUMN_CHANTIERS);
        ParseQuery query = relation.getQuery();
        try {
            listChantier= new ArrayList<ParseObject>( query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listChantier;
    }

}
