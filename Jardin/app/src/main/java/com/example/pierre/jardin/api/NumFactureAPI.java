package com.example.pierre.jardin.api;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;

/**
 * Created by pierre on 20/10/2017.
 */

public class NumFactureAPI {
    public static final String TABLE_NUM_FACTURE = "NumFacture";
    public static final String COLUMN_NUM = "Num";


    public int getNumFacture (){
        ParseQuery<ParseObject > query = ParseQuery.getQuery(TABLE_NUM_FACTURE);
        ArrayList<ParseObject> listNum = new ArrayList<ParseObject>();
        try {
            listNum = new ArrayList<>(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listNum.get(0).getInt(COLUMN_NUM);
    }



    public void incrementeFacture (){

        ParseQuery<ParseObject > query = ParseQuery.getQuery(TABLE_NUM_FACTURE);
        ArrayList<ParseObject> listNum = new ArrayList<ParseObject>();
        try {
            listNum = new ArrayList<>(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        listNum.get(0).increment(COLUMN_NUM);
        listNum.get(0).saveInBackground();
    }


}
