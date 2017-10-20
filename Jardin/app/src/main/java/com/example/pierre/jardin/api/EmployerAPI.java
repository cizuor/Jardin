package com.example.pierre.jardin.api;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;

import java.util.ArrayList;

/**
 * Created by pierre on 01/10/2017.
 */

public class EmployerAPI {

    public static final String TABLE_EMPLOYER = "Employer";
    public static final String COLUMN_NOM = "Nom";
    public static final String COLUMN_NUM = "Num";
    public static final String COLUMN_ADRESSE = "Adresse";
    public static final String COLUMN_MAIL = "Mail";
    public static final String COLUMN_NUM_SECU = "NumSecu";
    public static final String COLUMN_IBAN = "Iban";
    public static final String COLUMN_JOUR_CHOMERS = "JourChomers";



    public ArrayList<ParseObject> getEmployers (){
        ParseQuery<ParseObject > query = ParseQuery.getQuery(TABLE_EMPLOYER);
        ArrayList<ParseObject> listEmployers = new ArrayList<ParseObject>();
        try {
            listEmployers = new ArrayList<>(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listEmployers;
    }


    public ArrayList<ParseObject> getEmployersByName(String nom){
        ParseQuery<ParseObject > query = ParseQuery.getQuery(TABLE_EMPLOYER);
        query.whereMatches(COLUMN_NOM,nom);
        ArrayList<ParseObject> listClients = new ArrayList<ParseObject>();
        try {
            listClients = new ArrayList<>(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listClients;
    }

    public ArrayList<ParseObject> getEmployerFromChantier (ParseObject chantier){
        ArrayList<ParseObject> listEmployer = null;

        ParseRelation relation = chantier.getRelation(ChantierAPI.COLUMN_EMPLOYER);
        ParseQuery query = relation.getQuery();
        try {
            listEmployer= new ArrayList<ParseObject>( query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listEmployer;
    }

}
