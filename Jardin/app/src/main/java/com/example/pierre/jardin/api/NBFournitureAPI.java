package com.example.pierre.jardin.api;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;

import java.util.ArrayList;

/**
 * Created by pierre on 01/10/2017.
 */

public class NBFournitureAPI {
    public static final String TABLE_NBFOURNITURE = "NBFourniture";
    public static final String COLUMN_NOMBRE = "Nombre";
    public static final String COLUMN_FOURNITURE = "Fourniture";

    public ArrayList<ParseObject> getFournitureFromChantier(ParseObject chantier){
        ArrayList<ParseObject> listFourniture = new ArrayList<>();
        ParseRelation relation = chantier.getRelation(ChantierAPI.COLUMN_FOURNITURE);
        ParseQuery query = relation.getQuery();
        query.include(NBFournitureAPI.COLUMN_FOURNITURE);
        try {
            listFourniture= new ArrayList<ParseObject>( query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listFourniture;
    }



    public int getNBFournitureVendu(ParseObject fourniture){
        int nb = 0;

        ParseQuery<ParseObject > query = ParseQuery.getQuery(TABLE_NBFOURNITURE);
        query.whereEqualTo(COLUMN_FOURNITURE, fourniture);
        ArrayList<ParseObject> listNBFourniture = new ArrayList<ParseObject>();
        try {
            listNBFourniture = new ArrayList<>(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < listNBFourniture.size() ; i++) {
            nb = nb+listNBFourniture.get(i).getInt(COLUMN_NOMBRE);
        }
        return nb;
    }
}
