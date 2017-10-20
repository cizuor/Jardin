package com.example.pierre.jardin.api;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;

import java.util.ArrayList;

/**
 * Created by pierre on 04/10/2017.
 */

public class JourChomerAPI {
    public static final String TABLE_JOURCHOMER = "JourChomer";
    public static final String COLUMN_DATEDEBUT = "DateDebut";
    public static final String COLUMN_DATEFIN = "DateFin";
    public static final String COLUMN_ISMALADE = "IsMalade";
    public static final String COLUMN_ISVACANCE = "IsVacance";


    public ArrayList<ParseObject> getJourChomerFromEmployer (ParseObject employer){
        ArrayList<ParseObject> listJour = null;

        ParseRelation relation = employer.getRelation(EmployerAPI.COLUMN_JOUR_CHOMERS);
        ParseQuery query = relation.getQuery();
        try {
            listJour= new ArrayList<ParseObject>( query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listJour;
    }

}
