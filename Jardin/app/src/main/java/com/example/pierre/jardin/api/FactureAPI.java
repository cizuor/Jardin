package com.example.pierre.jardin.api;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;

import java.util.ArrayList;

/**
 * Created by pierre on 21/09/2017.
 */

public class FactureAPI {

    public static final String TABLE_FACTURE = "Facture";
    public static final String COLUMN_NOM = "Nom";
    public static final String COLUMN_NUM = "Num";
    public static final String COLUMN_DATE = "Date";
    public static final String COLUMN_PAYER = "Payer";
    public static final String COLUMN_PRIX = "Prix";
    public static final String COLUMN_CHANTIERS = "Chantiers";
    public static final String COLUMN_CLIENT = "Client";




    public ArrayList<ParseObject> getFactureFromClient (ParseObject Client){
        ArrayList<ParseObject> listFact = null;

        ParseRelation relation = Client.getRelation(ClientAPI.COLUMN_FACTURES);
        ParseQuery query = relation.getQuery();
        try {
            listFact= new ArrayList<ParseObject>( query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return listFact;
    }

    public ArrayList<ParseObject> getFactures (){
        ParseQuery<ParseObject > query = ParseQuery.getQuery(TABLE_FACTURE);
        ArrayList<ParseObject> listFacture = new ArrayList<ParseObject>();
        try {
            listFacture = new ArrayList<>(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listFacture;
    }



    public ArrayList<ParseObject> getFacturesByName(String nom){
        ParseQuery<ParseObject > query = ParseQuery.getQuery(TABLE_FACTURE);
        query.whereMatches(COLUMN_NOM,nom);
        ArrayList<ParseObject> listFacture = new ArrayList<ParseObject>();
        try {
            listFacture = new ArrayList<>(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listFacture;
    }



}
