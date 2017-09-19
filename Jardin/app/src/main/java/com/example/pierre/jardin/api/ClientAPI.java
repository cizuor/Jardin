package com.example.pierre.jardin.api;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierre on 16/09/2017.
 */

public class ClientAPI {
    public static final String TABLE_CLIENT = "Client";
    public static final String COLUMN_NOM = "Nom";
    public static final String COLUMN_NUM = "Num";
    public static final String COLUMN_ADRESSE = "Adresse";
    public static final String COLUMN_MAIL = "Mail";
    public static final String COLUMN_FACTURES= "Factures";




    public ArrayList<ParseObject> getClients (){
        ParseQuery<ParseObject > query = ParseQuery.getQuery("Client");
        ArrayList<ParseObject> listClients = new ArrayList<ParseObject>();
        try {
            listClients = new ArrayList<>(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listClients;
    }



    public ArrayList<ParseObject> getClientsByName(String nom){
        ParseQuery<ParseObject > query = ParseQuery.getQuery("Client");
        query.whereMatches(COLUMN_NOM,nom);
        ArrayList<ParseObject> listClients = new ArrayList<ParseObject>();
        try {
            listClients = new ArrayList<>(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listClients;
    }
}
