package com.example.pierre.jardin.api;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;

import java.util.ArrayList;

/**
 * Created by pierre on 01/10/2017.
 */

public class FournitureAPI {
    public static final String TABLE_FOURNITURE = "Fourniture";
    public static final String COLUMN_NOM = "Nom";
    public static final String COLUMN_PRIX = "PrixAchat";

    public ArrayList<ParseObject> getFournitures (){
        ParseQuery<ParseObject > query = ParseQuery.getQuery(TABLE_FOURNITURE);
        ArrayList<ParseObject> listFournitures = new ArrayList<ParseObject>();
        try {
            listFournitures = new ArrayList<>(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listFournitures;
    }

    public ArrayList<ParseObject> getFournituresByName (String nom){
        ParseQuery<ParseObject > query = ParseQuery.getQuery(TABLE_FOURNITURE);
        query.whereMatches(COLUMN_NOM,nom);
        ArrayList<ParseObject> listFournitures = new ArrayList<ParseObject>();
        try {
            listFournitures = new ArrayList<>(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listFournitures;
    }
}
